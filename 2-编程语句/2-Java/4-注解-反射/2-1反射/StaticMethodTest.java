package com.founder.jt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

class Mtest {
	@Deprecated
	private static String name = "jiaolong";

	@Deprecated
	public static void replace(String str, List<String> list) {
		System.out.println("����replace����: " + str);
	}
}

public class StaticMethodTest {
	public static void main(String[] args) throws Exception {
		fieldTest();
		System.out.println("====");
		methodTest();
	}

	public static void fieldTest() throws Exception {
		Field field = Mtest.class.getDeclaredField("name");
		System.out.println("���ͻ�����:" + field.toGenericString());
		System.out.println("�ֶε�����:" + field.getName());
		System.out.println("�ֶε�����:" + field.getType());
		// �����ֶ��Ƿ���Է���
		System.out.println("�ֶ��Ƿ���Է��ʣ�" + field.isAccessible());
		field.setAccessible(true);
		System.out.println("�ֶ��Ƿ���Է��ʣ�" + field.isAccessible());
		// ���þ�̬�ֶε�ֵ
		System.out.println("name�ֶε�ֵ��" + field.get("name"));
		field.set("name", "updateName");
		System.out.println("�޸ĺ�name�ֶε�ֵ��" + field.get("name"));
		System.out.println("�ֶ�ע�����֣�"+field.getAnnotation(Deprecated.class).annotationType().getName().toString());
		
	}

	public static void methodTest() throws Exception {
		Method method = Mtest.class.getDeclaredMethod("replace", String.class, List.class);
		// ���þ�̬����
		method.invoke(null, "jiaolong", new ArrayList<String>());
		System.out.println("���������֣�" + method.getName());
		System.out.println("�����ķ���������" + method.toGenericString());
		System.out.println("������return���ͣ�" + method.getReturnType());
		System.out.println("�����Ĳ�������-Class��ʽ��" + method.getParameterTypes()[0].getName());
		System.out.println("������ע�⣺" + method.getAnnotation(Deprecated.class).toString());

		System.out.println("�����в�����������" + method.getParameterCount());
		System.out.println("====");
		Parameter[] ps = method.getParameters();
		for (Parameter p : ps) {
			System.out.println("���������Ƿ���ڣ�" + p.isNamePresent());
			System.out.println("�������֣�" + p.getName());
			System.out.println("��������" + p.getType());
			System.out.println("�����ķ��ͣ�" + p.getParameterizedType());
			System.out.println("�����Ƿ�Ϊ�ɱ������" + p.isVarArgs());
			System.out.println("====");
		}
	}
}
