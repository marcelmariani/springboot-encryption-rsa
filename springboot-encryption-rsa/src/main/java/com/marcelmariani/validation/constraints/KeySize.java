package com.marcelmariani.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.marcelmariani.validation.KeySizeValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = KeySizeValidation.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface KeySize {

	String message() default "Key Size must contain 1024, 2048 or 4096";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
