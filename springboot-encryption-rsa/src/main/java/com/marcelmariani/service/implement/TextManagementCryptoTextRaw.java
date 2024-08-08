package com.marcelmariani.service.implement;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class TextManagementCryptoTextRaw {
	
	static PublicKey publicKey;
	static PrivateKey privateKey;
	
	/**
	 * Generates a new RSA key pair with the specified key size.
	 *
	 * @param keySize the size of the RSA key (e.g., 2048 or 4096 bits)
	 * @return the private key of the generated key pair
	 * @throws Exception if an error occurs during key pair generation
	 */
	static PrivateKey generateKeyPair(Integer keySize) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
		return privateKey;
	}

	/**
	 * Encrypts the given plain text using RSA encryption.
	 *
	 * @param plainText the plain text to be encrypted
	 * @return the encrypted text encoded in Base64
	 * @throws Exception if an error occurs during encryption
	 */
	public static String encrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	/**
	 * Decrypts the given encrypted text using RSA decryption.
	 *
	 * @param encryptedText the encrypted text encoded in Base64
	 * @param privateKeyStr the private key encoded in Base64 used for decryption
	 * @return the decrypted plain text
	 * @throws Exception if an error occurs during decryption
	 */
	public static String decrypt(String encryptedText, String privateKeyStr) throws Exception {

		byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes);
	}
}
