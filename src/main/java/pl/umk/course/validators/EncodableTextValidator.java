package pl.umk.course.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EncodableTextValidator implements ConstraintValidator<EncodableText, String> {

    @Override
    public void initialize(EncodableText paramA) {
    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext ctx) {
        return text.matches("[a-z]*");
    }
}