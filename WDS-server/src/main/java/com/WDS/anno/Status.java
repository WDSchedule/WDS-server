package com.WDS.anno;

import com.WDS.validation.StatusValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //元注解 加入说明文档
@Target(ElementType.FIELD) //元注解 说明该注释可以用在什么位置
@Retention(RetentionPolicy.RUNTIME) // 元注解 标注注解会保留到什么阶段

@Constraint(
        validatedBy = {StatusValidation.class}
) // 规定定义校验方法的类
public @interface Status {
    // 提供校验失败后的提示信息
    String message() default "status参数的值只能是字典中规定的值";

    // 指定分组
    Class<?>[] groups() default {};

    //负载 获取到Status注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
