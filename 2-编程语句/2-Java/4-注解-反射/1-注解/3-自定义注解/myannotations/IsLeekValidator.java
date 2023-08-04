package com.example.demo.myannotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsLeekValidator implements ConstraintValidator<IsLeek, String> {
    //是否强制校验
    private boolean required;

    @Override
    public void initialize(IsLeek constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (required) {
            // 名字以"新韭菜"开头的则校验通过
            System.out.println("---"+name);
            return name.startsWith("新韭菜");
        }
        return false;
    }

}
