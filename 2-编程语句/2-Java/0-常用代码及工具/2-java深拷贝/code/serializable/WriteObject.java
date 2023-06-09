package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;

/**
 * �������л����ļ������ļ��ж�ȡ����
 * @author jiaolong
 *
 */
public class WriteObject {
	public static void main(String[] args) throws ClassNotFoundException {
		try (   //java�����ͨ���� ���ж�������к��뷴���л�
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))
			) {
			
			//������д���ļ�
			oos.writeObject(new Person("�����", 500));			
			//���������ж�ȡһ��Java����
			Person p = (Person)ois.readObject();
			
			System.out.println("����Ϊ��" + p.getName() + "  ����Ϊ��" + p.getAge());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
