package com.founder;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

// ������ظ�ע��
@Repeatable(Annos.class)
@interface Anno {
	String aa() default "jiaolong";
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos {
	Anno[] value();
}

// ʹ��4��ע�����θ���
@SuppressWarnings(value = "unchecked")
@Deprecated //��ʾ��������
// ʹ���ظ�ע�����θ���
@Anno(aa="xxx")
@Anno
public class ClassTest {
	// Ϊ���ඨ��һ��˽�еĹ�����
	private ClassTest() {
	}

	// ����һ���в����Ĺ�����
	public ClassTest(String name) {
		System.out.println("ִ���в����Ĺ�����");
	}

	// ����һ���޲�����info����
	public void info() {
		System.out.println("ִ���޲�����info����");
	}

	// ����һ���в�����info����
	public void info(String str) {
		System.out.println("ִ���в�����info����" + "����str����ֵ��" + str);
	}
	
	private void privateMethod() {
		
	}

	// ����һ�������õ��ڲ���
	private class Inner {
	}
	
	public static void main(String[] args) throws Exception {
		// ���������Ի�ȡClassTest��Ӧ��Class
		Class<ClassTest> clazz = ClassTest.class;
		
//		// ��ȡ��Class��������Ӧ���ȫ��������
//		Constructor<?>[] ctors = clazz.getDeclaredConstructors();
//		System.out.println("ClassTest��ȫ�����������£�");
//		for (Constructor<?> c : ctors) {
//			System.out.println(c);
//		}
//		// ��ȡ��Class��������Ӧ���ȫ��public������
//		Constructor<?>[] publicCtors = clazz.getConstructors();
//		System.out.println("ClassTest��ȫ��public���������£�");
//		for (Constructor<?> c : publicCtors) {
//			System.out.println(c);
//		}
		
		
//		// ��ȡ��Class��������Ӧ���ȫ��public����
//		Method[] mtds = clazz.getMethods();
//		System.out.println("ClassTest��ȫ��public�������£�");
//		for (Method md : mtds) {
//			System.out.println(md);
//		}
//		
//		// ��ȡ��Class��������Ӧ���ָ������
//		Method m = clazz.getMethod("info", String.class);
//		System.out.println("ClassTest���һ���ַ���������info()����Ϊ��" + m);
	
//		// ��ȡ��Class��������Ӧ����ϵ�ȫ��ע��
//		Annotation[] anns = clazz.getAnnotations();
//		System.out.println("ClassTest��ȫ��Annotation���£�");
//		for (Annotation an : anns) {
//				
//			if(Annos.class.isInstance(an)) {
//				System.out.println(an.annotationType());
//				Annos annos = (Annos)an;
//				System.out.println("0===="+annos.value()[0].aa());
//			}
//			if(an instanceof  Annos) {
//				System.out.println(an.annotationType());
//				Annos annos = (Annos)an;
//				System.out.println("1===="+annos.value()[0].aa());
//			}
//			try {				
//				an.getClass().asSubclass(Annos.class);
//				
//				System.out.println(an.annotationType());
//				Annos annos = (Annos)an;
//				System.out.println("2===="+annos.value()[0].aa());
//			}catch(Exception e) {
//			//	e.printStackTrace();
//			}					
//		}
		
				
		System.out.println("��ClassԪ���ϵ�@SuppressWarningsע��Ϊ��" + Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
		System.out.println("��ClassԪ���ϵ�@Annoע��Ϊ��" + Arrays.toString(clazz.getAnnotationsByType(Anno.class)));
//		
//		// ��ȡ��Class��������Ӧ���ȫ���ڲ���
//		Class<?>[] inners = clazz.getDeclaredClasses();
//		System.out.println("ClassTest��ȫ���ڲ������£�");
//		for (Class<?> c : inners) {
//			System.out.println(c);
//		}
//		
//		// ʹ��Class.forName��������ClassTest��Inner�ڲ���
//		Class<?> inClazz = Class.forName("com.founder.ClassTest$Inner");		
//		// ͨ��getDeclaringClass()���ʸ������ڵ��ⲿ��
//		System.out.println("inClazz��Ӧ����ⲿ��Ϊ��" + inClazz.getDeclaringClass());
//		System.out.println("ClassTest�İ�Ϊ��" + clazz.getPackage());
//		System.out.println("ClassTest�ĸ���Ϊ��" + clazz.getSuperclass());

		//Objectת��ΪString,��Object���Ƿ�̳���һ��String��
		System.out.println(String.class.isAssignableFrom(Object.class));
		//Stringת��ΪObjct,��String���Ƿ�̳���һ��Object��
		System.out.println(Object.class.isAssignableFrom(String.class));
	}
}
