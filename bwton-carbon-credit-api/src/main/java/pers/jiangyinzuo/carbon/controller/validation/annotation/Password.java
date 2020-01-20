package pers.jiangyinzuo.carbon.controller.validation.annotation;

import pers.jiangyinzuo.carbon.controller.validation.validator.PasswordValidator;
import pers.jiangyinzuo.carbon.controller.validation.validator.TelephoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jiang Yinzuo
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface Password {
    String message() default "密码必须包括大写字母、小写字母、数字，且长度在6~15之间";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
