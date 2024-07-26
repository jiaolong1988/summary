package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;

/**
 * 对象序列化到文件及从文件中读取对象
 * @author jiaolong
 *
 */
public class WriteObject {
	public static void main(String[] args) throws ClassNotFoundException {
		try (   //java对象可通过流 进行对象的序列号与反序列化
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))
			) {
			
			//将对象写入文件
			oos.writeObject(new Person("孙悟空", 500));			
			//从输入流中读取一个Java对象
			Person p = (Person)ois.readObject();
			
			System.out.println("名字为：" + p.getName() + "  年龄为：" + p.getAge());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
