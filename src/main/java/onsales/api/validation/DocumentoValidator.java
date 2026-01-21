package onsales.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DocumentoValidator implements ConstraintValidator<DocumentoValido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return false;
        }

        String doc = value.replaceAll("\\D", "");

        return isCpf(doc) || isCnpj(doc);
    }

    private boolean isCpf(String doc) {
        return doc.matches("\\d{11}");
    }

    private boolean isCnpj(String doc) {
        return doc.matches("\\d{14}");
    }
}