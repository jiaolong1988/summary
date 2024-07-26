package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;
import com.founder.conver.test.copyNewObject.serializable.bean.Teacher;

/**
 * ���Ӷ���������л��������а������ö���
 * @author jiaolong
 *
 */
public class WriteTeacher {
	public static void main(String[] args) {
		Person per = new Person("�����", 500);
		
		try (   // ����һ��ObjectOutputStream�����
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))
			) {
			
			
			Teacher t1 = new Teacher("��ɮ", per);
			Teacher t2 = new Teacher("������ʦ", per);
			// ���ν��ĸ�����д�������
			oos.writeObject(per);
			oos.writeObject(t1);
			oos.writeObject(t2);

			// ͬһ����(per)������л�֮��ֻ�е�һ�ε���writeObject()���л�����ʱ�Ž��ж������л����������ֵ�����ˣ�
			// �ٴε���writeObject()�������л�ʱ��Ҳ����ִ�����л�������Ҳ����˵java�ж���ֻ�ܽ���һ�����л�����.
			per.setName("jiaolong");
			oos.writeObject(per);
			
			oos.writeObject(t2);
			
			//��ȡ��ȡ�µĶ���
			read(ois);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void read(ObjectInputStream ois) throws Exception {
		// ���ζ�ȡObjectInputStream�������е��ĸ�����		
		Person p = (Person) ois.readObject();
		Teacher t1 = (Teacher) ois.readObject();
		Teacher t2 = (Teacher) ois.readObject();
		Person p1 = (Person) ois.readObject();
		Teacher t3 = (Teacher) ois.readObject();
		
		// ���true
		System.out.println("t1��student���ú�p�Ƿ���ͬ��" + (t1.getStudent() == p));
		// ���true
		System.out.println("t2��student���ú�p�Ƿ���ͬ��" + (t2.getStudent() == p));
		// ���true
		System.out.println("t2��t3�Ƿ���ͬһ������" + (t2 == t3));
		// ���false
		System.out.println("t1��t2�Ƿ���ͬһ������" + (t1 == t2));
		// ���false
		System.out.println("p�����֣�"+p1.getName()+"     p1�����֣�"+p1.getName());
		System.out.println("p��p1��ͬһ������:"+ (p1 == p)+",p1����Ϊ�޸�Ϊjiaolong��,javaδ����ִ�����л�����" );
	}
}
