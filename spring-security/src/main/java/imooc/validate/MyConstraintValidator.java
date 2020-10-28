package imooc.validate;

import imooc.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * MyConstraintValidator
 *
 * @title: MyConstraintValidator
 * @Author shu.shen
 * @Date: 2020/10/23 23:42
 * @Version 1.0
 */
@Component
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint , Object> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("我的自定义验证规则初始化啦~~~");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        log.info("value = " + value);
        return false;
    }
}
