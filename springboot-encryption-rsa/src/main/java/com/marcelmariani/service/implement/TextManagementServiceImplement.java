
package com.marcelmariani.service.implement;

import java.security.PrivateKey;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelmariani.model.TextManagement;
import com.marcelmariani.repository.TextManagementRepository;
import com.marcelmariani.service.TextManagementService;
import com.marcelmariani.utils.TextManagementUtils;

/**
 * Implementation of the TextManagementService interface. This service provides
 * functionality to manage TextManagement entities, including encryption and
 * decryption of text data using RSA encryption.
 */
@Service
public class TextManagementServiceImplement implements TextManagementService {

	@Autowired
	private TextManagementRepository textManagementRepository;
	
	private Logger logger = LoggerFactory.getLogger(TextManagementServiceImplement.class);
	private String LogInfo;

	/**
	 * Retrieves a TextManagement entity by its unique identifier (UUID).
	 *
	 * @param uuid the unique identifier of the TextManagement entity
	 * @return the TextManagement entity if found
	 * @throws IllegalArgumentException if the TextManagement entity does not exist
	 */
	@Override
	public TextManagement getByIdTextManagement(String uuid, String privateKeyPassword, String privateKey) {
		
		TextManagement textManagement = this.textManagementRepository.findById(uuid)
				.orElseThrow(() -> new IllegalArgumentException("Text Management not exists!"));

			privateKey = TextManagementUtils.replaceSpacesWithPlus(privateKey);
			
			// Decrypt the text data
			try {
				String decryptedTextData = null;
				try {
					decryptedTextData = TextManagementCryptoTextRaw.decrypt(textManagement.getTextData(), privateKey);
				} catch (Exception e) {
					e.printStackTrace();
				}
				textManagement.setTextData(decryptedTextData);
			} catch (Exception e) {
				LogInfo = "Failed to decrypt text data.";
				logger.error(LogInfo);
				throw new IllegalArgumentException(LogInfo, e);
			}

		return textManagement;
	}

	/**
	 * Saves a TextManagement entity. If encryption is enabled, generates a new RSA
	 * key pair and encrypts the text data.
	 *
	 * @param textManagement the TextManagement entity to be saved
	 * @return the saved TextManagement entity
	 * @throws Exception if an error occurs during key generation or encryption
	 */
	public TextManagement saveTextManagement(TextManagement textManagement) throws Exception {

			PrivateKey privateKey = TextManagementCryptoTextRaw.generateKeyPair(textManagement.getKeySize());
			textManagement.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
			textManagement.setTextData(TextManagementCryptoTextRaw.encrypt(textManagement.getTextData()));

		return this.textManagementRepository.save(textManagement);
	}

}
