package pers.jiangyinzuo.carbon.controller.validation.validator;

import pers.jiangyinzuo.carbon.controller.validation.annotation.ID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jiang Yinzuo
 */
public class IDValidator implements ConstraintValidator<ID, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && value >= 1 && value <= 4294967295L;
    }
}
