package com.taxi.web.security;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncoder {
	private static PasswordEncoder passwordEncoder = null;
	private SecretKeyFactory f = null;

	private PasswordEncoder() {
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static PasswordEncoder getInstance() {
		if(passwordEncoder == null) {
			passwordEncoder = new PasswordEncoder();
		}
		return passwordEncoder;
	}
	

	public String encode(String password, byte[] salt) throws InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 128, 128);
		byte[] hash = f.generateSecret(spec).getEncoded();
		
		return new String(hash, StandardCharsets.UTF_8);
	}
}