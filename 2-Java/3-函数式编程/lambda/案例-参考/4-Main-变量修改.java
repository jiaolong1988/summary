package com.jiaolong.java;
import java.util.function.Function;

/**
 * lambda变量捕获
 * 1.非局部变量：lambda表达式可以更改
 * 2.局部变量：lambda表达式不可更改
 */
public class Main {
    static String x1 = "Hello";
	String x2 = "Hello";
    public static void main(String[] argv) {
        
        Function<String,String> func1 = y -> {x1="jiaolong"; return y + " "+ x1 ;};
        System.out.println(func1.apply("w3cschool.cn"));

        Function<String,String> func2 = y -> {/*x="a"; */   return y + " "+ x2 ;};
        System.out.println(func2.apply("w3cschool.cn"));
    }
}