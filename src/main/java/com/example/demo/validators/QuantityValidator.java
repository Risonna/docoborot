package com.example.demo.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validateQuantity")
public class QuantityValidator implements Validator<Integer> {
    @Override
    public void validate(FacesContext context, UIComponent component, Integer value) throws ValidatorException {
        if(value.intValue() <= 0){
            throw new ValidatorException(new FacesMessage("Нельзя заказать 0 или меньше яиц!"));
        }
    }
}
