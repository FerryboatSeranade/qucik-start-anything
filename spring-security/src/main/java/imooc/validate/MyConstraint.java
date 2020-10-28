package imooc.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ShuShen
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)//绑定约束规则
public @interface MyConstraint {
    //validation注解必须要有的三个东西
    String message() default "{javax.validation.constraints.Past.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
