package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.PersonTransient;

/**
 * transient �����ֶν������л�
 * @author jiaolong
 *
 */
public class TransientTest{
	public static void main(String[] args){
		try(// ����һ��ObjectOutputStream�����
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
			// ����һ��ObjectInputStream������
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transient.txt")))
		{
			//transient �ؼ���ָ�����ֶ������л�ʱ,����Ը��ֶ�
			PersonTransient per = new PersonTransient("�����", 500);
			// ϵͳ��per����ת���ֽ����в����
			oos.writeObject(per);
			PersonTransient p = (PersonTransient)ois.readObject();
			System.out.println(p.getAge());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
