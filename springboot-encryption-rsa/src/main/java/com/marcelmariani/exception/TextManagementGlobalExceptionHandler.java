package com.marcelmariani.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TextManagementGlobalExceptionHandler {

	@ExceptionHandler(TextManagementInvalidIdException.class)
	public ResponseEntity<String> handleInvalidIdException(TextManagementInvalidIdException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
