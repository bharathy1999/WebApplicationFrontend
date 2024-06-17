package com.signProfile.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.signProfile.DAO.NewProfileUpdate;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp extends HttpServlet {
	// this class is for store securely data in database when a user sign up


	private static SecretKey generateSecretKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// it generates a key size of 128 bits
		keyGenerator.init(128);
		return keyGenerator.generateKey();
	}
	
	
	// use base64 to the key is converted into string 
		
		private static String secretKeyToString(SecretKey secretKey) {
			
			byte[] keyBytes = secretKey.getEncoded();
			return Base64.getEncoder().encodeToString(keyBytes);
		}
		
	// this method is used for encrypt email
	public static String encrypt(String input, SecretKey secretKey) {
		String key = null;
		try {
			// AES refers Advanced Encryption Standard
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] inputBytes = input.getBytes();
			byte[] encryptedBytes = cipher.doFinal(inputBytes);
			// it gives encrypted bytes so it is converted into STring using base64
			key = Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (NoSuchAlgorithmException  | InvalidKeyException e) {
			e.printStackTrace();
		}
		
		catch(Exception e){
		e.printStackTrace();
		}
		return key;
	}
// this method provides hashing for password storage 
	public static String hashing(String input) {
	
		StringBuilder hexString = new StringBuilder();

		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = sha256.digest(input.getBytes());
			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				hexString.append(hex);
			}

		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		System.out.println(name + " " + password + " " + email);

		NewProfileUpdate newProfileUpdate = new NewProfileUpdate();

		SecretKey secretKey = generateSecretKey();

		newProfileUpdate.updateNewProfile(name, hashing(password), encrypt(email, secretKey),
				secretKeyToString(secretKey));
		System.out.println("Success");

		response.getWriter().write("SUCCESS");

	}

}
