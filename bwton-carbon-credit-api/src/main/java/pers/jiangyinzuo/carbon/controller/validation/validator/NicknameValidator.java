package pers.jiangyinzuo.carbon.controller.validation.validator;

import pers.jiangyinzuo.carbon.controller.validation.annotation.Nickname;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jiang Yinzuo
 */
public class NicknameValidator implements ConstraintValidator<Nickname, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^[^~`\\-_+={}!@#$%^&*()\\[\\]|\\\\:;'\"<>?/.,]{1,20}$");
    }
}
