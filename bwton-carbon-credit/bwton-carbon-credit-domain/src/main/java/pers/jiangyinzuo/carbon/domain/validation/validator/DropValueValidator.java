package pers.jiangyinzuo.carbon.domain.validation.validator;

import pers.jiangyinzuo.carbon.domain.validation.annotation.DropValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jiang Yinzuo
 */
public class DropValueValidator implements ConstraintValidator<DropValue, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^\\d+\\.\\d+$");
    }
}
