package com.test.proyectocvd;

import java.math.BigInteger;
import java.security.MessageDigest;

public class md5 {
    private String input;
    public md5(String input){
        this.input=input;
    }
    public String getEncryption(){
        try {
            MessageDigest md =MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number =new BigInteger(1, messageDigest);
            String hashtext  = number.toString(16);
            while(hashtext.length() <32){
                hashtext = "0"+ hashtext;
            }
            return hashtext;
        }catch (Exception e){

        }
        return input;
    }
}
