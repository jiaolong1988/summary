package com.example.demo.myannotations;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author jiaolong
 * @date 2023/07/19 17:10
 */
public class UserDTO {
    @NotBlank(message = "姓名不能为空")
    @IsLeek // 我们自定义的注解
    private String name;

    @Min(value = 18, message = "年龄不能小于18")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
