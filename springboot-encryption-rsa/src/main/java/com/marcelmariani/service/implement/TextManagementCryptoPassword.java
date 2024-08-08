package com.marcelmariani.service.implement;

import org.jasypt.util.text.StrongTextEncryptor;

/**
 * The {@code CryptoPassword} class provides static methods for encrypting and
 * decrypting passwords using the Jasypt library's {@link StrongTextEncryptor}
 * with a password obtained from environment variables. This class uses a static
 * instance of {@code StrongTextEncryptor} to perform encryption and decryption.
 * It relies on an environment variable named {@code APP_KEY} to set the
 * encryption/decryption key.
 */
public class TextManagementCryptoPassword {

	// Static instance of StrongTextEncryptor for encryption and decryption
	private static StrongTextEncryptor encryptor;
	// Can be moved to Password Key Manager
	private static String keyPassword = "com.marcelmariani";

	/**
	 * Encrypts the provided password using the {@code StrongTextEncryptor}.
	 * 
	 * @param privateKeyPassword the password to be encrypted
	 * @return the encrypted password
	 */
	public static String encrypt(String privateKeyPassword) {
		encryptor = new StrongTextEncryptor();
		encryptor.setPassword(keyPassword);
		return encryptor.encrypt(privateKeyPassword);
	}

	/**
	 * Decrypts the provided encrypted password using the
	 * {@code StrongTextEncryptor}.
	 * 
	 * @param encryptedPassword the encrypted password to be decrypted
	 * @return the decrypted password
	 */
	public static String decrypt(String privateKeyPassword) {
		encryptor = new StrongTextEncryptor();
		encryptor.setPassword(keyPassword);
		return encryptor.decrypt(privateKeyPassword);
	}
}