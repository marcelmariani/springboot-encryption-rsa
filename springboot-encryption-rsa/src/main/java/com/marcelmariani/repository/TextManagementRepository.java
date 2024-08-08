package com.marcelmariani.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.marcelmariani.model.TextManagement;
/**
 * Repository interface for managing {@link TextManagement} documents in MongoDB.
 * 
 * @see MongoRepository
 * @see TextManagement
 */
public interface TextManagementRepository extends MongoRepository<TextManagement, String> {

	@Query("{}")
	public List<TextManagement> getTextManagementById(String uuid);
}
