package com.founder.conver.test.copyNewObject;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.BeanUtils;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;
import com.founder.conver.test.copyNewObject.serializable.bean.Teacher;
import com.google.gson.Gson;


public class DeepCopyTest {

	public static void main(String[] args) {
		System.out.println("========commons-lang���߰��ṩ�Ķ������=============");
		/**
		 * commons-lang����java������ȿ�����
		 * ʹ�õ�ʱjava io�Ķ���������ʵ�֣�ͨ��ByteArrayOutputStream��������Ϣ�������ֽ�������
		 * @author jiaolong
		 *
		 */
		//ͨ��io���Ķ��������ж����ʵ�����뷴ʵ����
		Person p = new Person("jiaolong",20);
		Teacher t = new Teacher("teacher", p);
		//�������
		Teacher tCopy = (Teacher)SerializationUtils.clone(t);
		System.out.println("Teacher����ǰ�����ݣ�"+t.getName()+"- "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		
		p.setName("jl");
		System.out.println("�޸�Teacher����ǰ�����ݣ�"+t.getName()+"- "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		System.out.println("Teacher����������ݣ�"+tCopy.getName()+" - "+tCopy.getStudent().getName()+" - "+t.getStudent().getAge());		
		
		
		System.out.println("\n=========spring�ṩ���������(�Ƽ�)===========");		
		//ͨ������ʵ��
		Teacher nt = new Teacher();
		BeanUtils.copyProperties(t, nt);
		System.out.println("spring Teacher����ǰ�����ݣ�"+t.getName()+" - "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		
		t.setName("xxxx");
		System.out.println("�޸�spring Teacher����ǰ�����ݣ�"+t.getName()+" - "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		System.out.println("spring Teacher����������ݣ�"+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
		
		System.out.println("\n=========Gson����json�����л��������============");		
		Gson gson = new Gson();
		Teacher teacher = gson.fromJson(gson.toJson(nt), Teacher.class);
		System.out.println("Gson Teacher����ǰ�����ݣ�"+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
	
		nt.setName("xxxx");
		System.out.println("�޸�Gson Teacher����ǰ�����ݣ�"+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
		System.out.println("Gson Teacher����������ݣ�"+teacher.getName()+" - "+teacher.getStudent().getName()+" - "+teacher.getStudent().getAge());


	}

}
