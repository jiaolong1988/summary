package com.founder.tt;

import java.lang.annotation.*;

// ָ����ע����Ϣ�ᱣ��������ʱ
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FkTags {
	// ����value��Ա�������ó�Ա�����ɽ��ܶ��@FkTagע��
	FkTag[] value();
}
