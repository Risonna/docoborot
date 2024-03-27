package com.example.demo.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validateInitials")
public class InitialsValidator implements Validator<String> {
    private static final int MINIMUM_LENGTH = 4;
    private static final int MAXIMUM_LENGTH = 4;
    private static final String PATTERN = "[А-Я]\\.[А-Я]\\.";
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

        if (value == null || value.length() < MINIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Инициалы не могут быть меньше 4 символов"));
        }

        if (value.length() > MAXIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Инициалы не могут быть больше 4 символов"));
        }

        if (!value.matches(PATTERN)) {
            throw new ValidatorException(new FacesMessage("Инициалы должны содержать только буквы кириллицы и точки и иметь следующий вид: В.В."));
        }
    }
}
