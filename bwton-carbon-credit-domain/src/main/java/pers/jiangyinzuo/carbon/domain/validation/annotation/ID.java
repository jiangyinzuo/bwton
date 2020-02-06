package pers.jiangyinzuo.carbon.domain.validation.annotation;

import pers.jiangyinzuo.carbon.domain.validation.validator.IDValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jiang Yinzuo
 */
@Documented
@Constraint(validatedBy = IDValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ID {
    String message() default "ID错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
