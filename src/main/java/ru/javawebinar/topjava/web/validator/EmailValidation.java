package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailValidation implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.getByEmail(value) == null;
    }
}
