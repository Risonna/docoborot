package com.example.demo.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;

@FacesValidator("validateSurname")
public class SurnameValidator implements Validator<String>{
    private static final int MINIMUM_LENGTH = 3;
    private static final int MAXIMUM_LENGTH = 20;
    private static final String PATTERN = "[а-яА-Я]*";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

        if (value == null || value.length() < MINIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Фамилия не может быть меньше 3 символов"));
        }

        if (value.length() > MAXIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Фамилия не может быть больше 20 символов"));
        }

        if (!value.matches(PATTERN)) {
            throw new ValidatorException(new FacesMessage("Фамилия должна содержать только буквы кириллицы"));
        }

    }
}
