package com.example.demo.myannotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * https://www.cnblogs.com/luciochn/p/14529281.html
 * @author jiaolong
 * @date 2023/07/20 15:10
 */
public class Test {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserDTO userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setName("新韭菜hhh");

        Set<ConstraintViolation<UserDTO>> checkResult = validator.validate(userDTO);
        if (checkResult.size() > 0) {
            System.out.println(checkResult.iterator().next().getMessage());
        }

    }
}
