package reflect;

import java.lang.reflect.Field;

class Student{
	private String studentName="studentName";
}

public class Test {
	private String testName="testName";

	public static void main(String[] args) throws Exception {
			
		//获取自身对象
		Class<Test> tc = Test.class;
		Field tcField = tc.getDeclaredField("testName");
		System.out.println(tcField.isAccessible());
		tcField.setAccessible(true);
		
		Test tn = new Test();
		System.out.println("====>"+tcField.get(tn)+"\n");
		
		
	    //反射name字段是否可以访问
	    Class<?> studentClass = Student.class;	    
	    Student student = new Student();		
		for (Field field : studentClass.getDeclaredFields()) {
			System.out.println(field.isAccessible());// 这里的结果是false
			field.setAccessible(true);
			
			System.out.println("字段名称："+field.getName());
			System.out.println("获取对象字段的值："+field.get(student));
		}		
	}
}
