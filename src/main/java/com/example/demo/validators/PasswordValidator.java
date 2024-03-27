package com.example.demo.validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@FacesValidator("validatePassword")
public class PasswordValidator implements Validator<String> {
    private static final int MINIMUM_LENGTH = 6;
    private static final int MAXIMUM_LENGTH = 20;
    private static final String PATTERN = "[a-zA-Z0-9]*";
    private static final String PASSWORD_FILE = "C:/programs/glassfish/passwords.txt";


    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        List<String> validPasswords = readPasswordsFromFile();

        if (value == null || value.length() < MINIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Пароль не может быть меньше 6 символов"));
        }

        if (value.length() > MAXIMUM_LENGTH) {
            throw new ValidatorException(new FacesMessage("Пароль не может быть больше 20 символов"));
        }

        if (!value.matches(PATTERN)) {
            throw new ValidatorException(new FacesMessage("Пароль должен содержать только латинские буквы или цифры"));
        }
        if (!validPasswords.contains(value)) {
            FacesMessage msg = new FacesMessage("Неверный пароль", "Введенный пароль не найден в списке разрешенных паролей.");
            throw new ValidatorException(msg);
        }
    }

    private List<String> readPasswordsFromFile() {
        List<String> passwords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                passwords.add(line.trim());
                System.out.println("another password is: " + line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return passwords;
    }
}
