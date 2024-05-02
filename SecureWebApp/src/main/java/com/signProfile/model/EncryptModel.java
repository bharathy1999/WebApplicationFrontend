package com.signProfile.model;

public class EncryptModel {
  private String email;
  private String key;
  public EncryptModel(String email,String key){
	   this.email=email;
	   this.key=key;
   }
  
  public String getMail() {
	  return this.email;
  }
  
  public String getKey() {
	  return this.key;
  }
  
  
  
}
