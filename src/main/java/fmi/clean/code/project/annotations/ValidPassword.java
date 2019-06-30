package fmi.clean.code.project.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;


import static fmi.clean.code.project.helpers.Constants.INVALID_PASSWORD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Retention(RUNTIME)
@NotNull
public @interface ValidPassword {

  String message() default INVALID_PASSWORD;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
