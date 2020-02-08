package pers.jiangyinzuo.carbon.domain.validation.validator;

import pers.jiangyinzuo.carbon.domain.validation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @author Jiang Yinzuo
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String regex = "^(?=^[\\w~`!@#$%^&*()\\-+={}\\[\\]|\\\\;:'\"<>?/.,]+$)(?=.*\\d.*)(?=.*[a-z].*)(?=.*[A-Z].*).{6,15}$";
        return value != null && value.matches(regex);
    }
}
