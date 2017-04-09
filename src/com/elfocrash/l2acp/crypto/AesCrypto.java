package com.elfocrash.l2acp.crypto;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCrypto {
	static byte[] keyBytes = new byte[]{
			(byte)239,
			(byte)106,
			(byte)149,
			(byte)170,
			(byte)121,
			(byte)167,
			(byte)255,
			(byte)168,
			(byte)219,
			(byte)184,
			(byte)9,
			(byte)37,
			(byte)151,
			(byte)81,
			(byte)208,
			(byte)101,
			(byte)165,
			(byte)43,
			(byte)15,
			(byte)83,
			(byte)207,
			(byte)32,
			(byte)108,
			(byte)62,
			(byte)71,
			(byte)230,
			(byte)121,
			(byte)149,
			(byte)170,
			(byte)177,
			(byte)102,
			(byte) 165
	};
	
	static byte[] iv = new byte[]{
			(byte) 228,
			(byte) 243,
			(byte)46,
			(byte)250,
			(byte)186,
			(byte)189,
			(byte)28,
			(byte)193,
			(byte)69,
			(byte)230,
			(byte)101,
			(byte)200,
			(byte)197,
			(byte) 188,
			(byte)135,
			(byte)199
	};
	
	
		public static String decryptRequest(String requestString) throws Exception
		{
			SecretKey secret = new SecretKeySpec(keyBytes, "AES");
			byte[] decoded = Base64.getDecoder().decode(requestString);
			Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher1.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
			String plaintext = new String(cipher1.doFinal(decoded), "UTF-8");
			return plaintext;
		}
		
		public static String encryptRequest(String requestString) throws Exception
		{
			SecretKey secret = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
			String ciphertext = new String(Base64.getEncoder().encode(cipher.doFinal(requestString.getBytes("UTF-8"))), "UTF-8");
			return ciphertext;
		}
}
