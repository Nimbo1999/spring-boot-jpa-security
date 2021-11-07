package io.github.nimbo1999.validation.contraintValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.nimbo1999.utils.StringUtils;
import io.github.nimbo1999.validation.NotInValidPhoneNumber;

public class NotInvalidPhoneNumber implements ConstraintValidator<NotInValidPhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        String formattedValue = StringUtils.removeNonDigits(value);
        return formattedValue.length() > 10 && formattedValue.length() < 12;
    }
    
    @Override
    public void initialize(NotInValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
