package fmi.clean.code.project.annotations;


import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;


import static fmi.clean.code.project.helpers.Constants.PASS_DIGIT_NUM;
import static fmi.clean.code.project.helpers.Constants.PASS_LOWERCASE_NUM;
import static fmi.clean.code.project.helpers.Constants.PASS_MAX_LENGTH;
import static fmi.clean.code.project.helpers.Constants.PASS_MIN_LENGTH;
import static fmi.clean.code.project.helpers.Constants.PASS_UPPERCASE_NUM;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public void initialize(ValidPassword validPassword) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
    if (password == null) {
      return false;
    }
    PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
        new LengthRule(PASS_MIN_LENGTH, PASS_MAX_LENGTH),
        new CharacterRule(EnglishCharacterData.UpperCase, PASS_UPPERCASE_NUM),
        new CharacterRule(EnglishCharacterData.Digit, PASS_DIGIT_NUM),
        new CharacterRule(EnglishCharacterData.LowerCase, PASS_LOWERCASE_NUM),
        new WhitespaceRule()
    ));
    return passwordValidator.validate(new PasswordData(password)).isValid();
  }
}