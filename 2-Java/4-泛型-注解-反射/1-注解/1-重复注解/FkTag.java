package com.founder.tt;

import java.lang.annotation.*;

// ָ����ע����Ϣ�ᱣ��������ʱ
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(FkTags.class)
public @interface FkTag {
	// Ϊ��ע�ⶨ��2����Ա����
	String name() default "������";

	int age();
}
