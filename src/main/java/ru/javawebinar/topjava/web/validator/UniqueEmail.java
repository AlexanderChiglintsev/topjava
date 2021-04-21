package ru.javawebinar.topjava.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "User with such email already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
