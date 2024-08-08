package com.marcelmariani.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.marcelmariani.dto.TextManagementDTO;
import com.marcelmariani.model.TextManagement;
import com.marcelmariani.service.TextManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * Controller for managing text data with encryption capabilities.
 * 
 * Provides endpoints for saving and retrieving text management records. The
 * records can be encrypted or decrypted based on the provided parameters.
 * 
 * @see TextManagementService
 * @see TextManagementCryptoPassword
 */
@RestController
@RequestMapping("/v1/text-management")
@Validated
public class TextManagementController {

	@Autowired
	private TextManagementService textManagementService;

	/**
	 * Saves a text management record.
	 * 
	 * If encryption is enabled for the record, the private key password is
	 * encrypted before saving. The response contains the UUID of the saved record
	 * and, if encryption was used, the private key.
	 * 
	 * @param textManagement the text management record to be saved
	 * @return ResponseEntity containing the UUID and optionally the private key
	 */
	@PostMapping
	@Operation(summary = "Record Text Data", description = "The data can be encrypted or not.\r\n"
			+ "If encrypted, its use RSA Algorithm.\r\n"
			+ "The data will bee storage in a Mongo Database.", tags = { "Text Data" }, responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TextManagementDTO.class))) }),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	@JsonView(TextManagementDTO.ViewPOST.class)
	public ResponseEntity<Map<String, Object>> saveTextManagement(@RequestBody TextManagement textManagement) {

		try {
			textManagementService.saveTextManagement(textManagement);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("uuid", textManagement.getUuid());
		response.put("privateKey", textManagement.getPrivateKey());

		return ResponseEntity.ok(response);
	}

	/**
	 * Retrieves a text management record by its UUID.
	 * 
	 * If the record is encrypted, it validates the provided private key password
	 * and private key. If validation fails, an error response is returned. If
	 * validation succeeds, the text data is decrypted before being included in the
	 * response.
	 * 
	 * @param uuid               the UUID of the text management record to retrieve
	 * @param privateKeyPassword the private key password for decryption (optional)
	 * @param privateKey         the private key for decryption (optional)
	 * @return ResponseEntity containing the UUID and decrypted text data if
	 *         successful, or an error message if the record is not found or
	 *         validation fails
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping("/{uuid}")
	@Operation(summary = "Get Text Data", description = "Return the textData, using uuid to search.\r\n"
			+ "If the textData is encrypted, the privateKeyPassword and the privateKey are required.", tags = {
					"Text Data" }, responses = {
							@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TextManagementDTO.class))),
							@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
							@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
							@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
							@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
							@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })

	@JsonView(TextManagementDTO.ViewGET.class)
	public ResponseEntity<Map<String, Object>> getByIdTextManagement(@PathVariable @Valid String uuid,
			@RequestParam(required = false) String privateKeyPassword,
			@RequestParam(required = false) String privateKey) throws UnsupportedEncodingException {

		TextManagement textManagement = this.textManagementService.getByIdTextManagement(uuid, privateKeyPassword,
				privateKey);

		Map<String, Object> response = new HashMap<>();
		response.put("uuid", textManagement.getUuid());
		response.put("textData", textManagement.getTextData());

		return ResponseEntity.ok(response);
	}

}