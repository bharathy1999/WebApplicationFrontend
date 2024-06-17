package com.signProfile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signProfile.DAO.GetProfile;
import com.signProfile.model.EncryptModel;

public class SignIn extends HttpServlet {

	private String mfaOtp;
// this method is used for decrypt email
	public static String decrypt(String encryptedText, SecretKey secretKey) {
		String email = null;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			email = new String(decryptedBytes);
		} 
		// In case there is no support for given algorithm and gives a invalid key for decryption
		catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
// this method gives secret key
	
	public static SecretKey stringToKey(String keyString) {
		byte[] decodedKey = Base64.getDecoder().decode(keyString);
		return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	}

	public static String hashing(String input) {
		StringBuilder hexString = new StringBuilder();

		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = sha256.digest(input.getBytes());
			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				hexString.append(hex);
			}
			// In case there is no support for given algorithm
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String otp = request.getParameter("otp");
		PrintWriter out = response.getWriter();
// when a user initially log in this block gets executed after this otp is sent to user 
		if (otp == null) {
			GetProfile getProfile = new GetProfile();
			try {
				EncryptModel encryptModel = getProfile.retrieveProfile(name, hashing(password));
				System.out.println(encryptModel);
				String mail = decrypt(encryptModel.getMail(), stringToKey(encryptModel.getKey()));
				System.out.println();
				MultiFactorAuthentication multiFactorAuthentication = new MultiFactorAuthentication();
				mfaOtp = multiFactorAuthentication.sendOTP(mail);
				out.write("SUCCESS");
			} catch (Exception ne) {
				out.write("FAILURE");
			}

		}
// otp is not null this block gets executed
		else {
			System.out.println(mfaOtp);
			System.out.println(otp);
			if (otp.equals(mfaOtp)) {
				out.write("SUCCESS");
			} else {
				out.write("FAILURE");
			}

		}

	}

}
