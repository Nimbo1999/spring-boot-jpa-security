package io.github.nimbo1999.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.github.nimbo1999.validation.contraintValidation.NotInvalidPhoneNumber;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotInvalidPhoneNumber.class)
public @interface NotInValidPhoneNumber {
    String message() default "Phone number invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
