package com.WDS.validation;

import com.WDS.anno.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StatusValidation implements ConstraintValidator<Status, Integer> {
    /**
     *
     * @param value 将来要校验的数据
     * @param context
     * @return false校验不通过，true为通过
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 提供校验规则
        if (value == null)
            return false;
        List<Integer> values = Arrays.asList(0,1,2,3);
        if (values.contains(value))
            return true;

        return false;
    }

}
