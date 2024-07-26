package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.PersonTransient;

/**
 * transient 忽略字段进行序列化
 * @author jiaolong
 *
 */
public class TransientTest{
	public static void main(String[] args){
		try(// 创建一个ObjectOutputStream输出流
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
			// 创建一个ObjectInputStream输入流
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transient.txt")))
		{
			//transient 关键字指定的字段在序列化时,会忽略该字段
			PersonTransient per = new PersonTransient("孙悟空", 500);
			// 系统会per对象转换字节序列并输出
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
