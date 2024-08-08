package com.marcelmariani.service.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.marcelmariani.model.TextManagement;
import com.marcelmariani.repository.TextManagementRepository;

public class TextManagementServiceImplementTest {

	@InjectMocks
	private TextManagementServiceImplement textManagementService;

	@Mock
	private TextManagementRepository textManagementRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		keyPair.getPublic();
		keyPair.getPrivate();
	}

	@Test
	@DisplayName("Encrypted True - Should record the Password Encrypted and PrivateKeyPassword and PrivateKey not null")
	void testSaveTextManagementWithEncryptionPassword() throws Exception {

		TextManagement textManagement = new TextManagement();
		textManagement.setTextData("Text Data");
		textManagement.setKeySize(1024);
		textManagement.setPrivateKey("PrivateKey");

		// Mock the repository save method
		when(textManagementRepository.save(any(TextManagement.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		TextManagement result = textManagementService.saveTextManagement(textManagement);
		assertNotNull(result.getPrivateKey());
	}

	@Test
	@DisplayName("Encrypted True - Should record the Text Data Encrypted and PrivateKeyPassword and PrivateKey not null")
	void testSaveTextManagementWithEncryptionTextRaw() throws Exception {

		TextManagement textManagement = new TextManagement();
		textManagement.setTextData("Text Data");
		textManagement.setKeySize(1024);

		// Mock the repository save method
		when(textManagementRepository.save(any(TextManagement.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		TextManagement result = textManagementService.saveTextManagement(textManagement);
		String textDataEncrypted = result.getTextData();
		String textDataDecrypted = textManagement.getTextData();
		assertEquals(textDataEncrypted, textDataDecrypted); // Password text should not be the same
		assertNotNull(result.getPrivateKey());
	}

	@Test
	@DisplayName("Encrypted True - Should Get the Text Data Encrypted and PrivateKeyPassword and PrivateKey not null")
	void testGetTextManagementWithEncryptionTextRaw() throws Exception {

		String uuid = "test-uuid";

		TextManagement textManagement = new TextManagement();
		textManagement.setTextData("Text Data");
		textManagement.setKeySize(1024);

		// Mock the repository save method
		when(textManagementRepository.save(any(TextManagement.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		TextManagement saveResult = textManagementService.saveTextManagement(textManagement);

		String privateKeyPassword = "Password"; //saveResult.getPrivateKeyPassword();
		String privateKey = saveResult.getPrivateKey();

		when(textManagementRepository.findById(uuid)).thenReturn(Optional.of(textManagement));

		TextManagement getResult = textManagementService.getByIdTextManagement(uuid, privateKeyPassword, privateKey);

		assertEquals(getResult.getTextData(), textManagement.getTextData()); // Text Data Encrypted should be the same that Get
		assertEquals(getResult.getPrivateKey(), textManagement.getPrivateKey()); // Password text should not be the same
		assertNotNull(getResult.getPrivateKey());
	}
}
