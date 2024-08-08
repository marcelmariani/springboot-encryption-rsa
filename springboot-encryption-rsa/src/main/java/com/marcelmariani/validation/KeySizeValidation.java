package com.marcelmariani.validation;

import java.util.Arrays;
import java.util.List;

import com.marcelmariani.validation.constraints.KeySize;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * Validator class for checking the validity of key sizes.
 * This class implements the {@link ConstraintValidator} interface to validate if an integer key size
 * is among the predefined valid key sizes.
 * The valid key sizes are: 1024, 2048, and 4096.
 * @see KeySize
 */
public class KeySizeValidation implements ConstraintValidator<KeySize, Integer> {

	// List of valid key sizes
	private static final List<Integer> VALID_KEY_SIZES = Arrays.asList(1024, 2048, 4096);

    /**
     * Initializes the validator.
     * This method can be used to set up any necessary configuration for the validator.
     * Currently, it does not perform any operations.
     */
	@Override
	public void initialize(KeySize constraintAnnotation) {
		// Initialization code if needed
	}
    /**
     * Validates if the provided key size is among the valid key sizes.
     */
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // or return false if null should be invalid
		}
		return VALID_KEY_SIZES.contains(value);
	}
}