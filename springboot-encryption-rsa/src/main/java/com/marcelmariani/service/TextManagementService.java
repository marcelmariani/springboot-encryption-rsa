package com.marcelmariani.service;

import java.util.Optional;

import com.marcelmariani.model.TextManagement;

/**
 * Service interface for managing {@link TextManagement} entities. Provides
 * methods to retrieve, save, and decrypt text management information.
 */
public interface TextManagementService {
	/**
	 * Retrieves a {@link TextManagement} entity by its unique identifier.
	 */
	public TextManagement getByIdTextManagement(String uuid, String privateKeyPassword, String privateKey);

	/**
	 * Saves a {@link TextManagement} entity to the database.
	 */
	public TextManagement saveTextManagement(TextManagement textManagement) throws Exception;

}
