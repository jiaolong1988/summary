package com.founder.conver.test.copyNewObject.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;
import com.founder.conver.test.copyNewObject.serializable.bean.Teacher;

/**
 * 复杂对象进行序列化，对象中包含引用对象
 * @author jiaolong
 *
 */
public class WriteTeacher {
	public static void main(String[] args) {
		Person per = new Person("孙悟空", 500);
		
		try (   // 创建一个ObjectOutputStream输出流
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))
			) {
			
			
			Teacher t1 = new Teacher("唐僧", per);
			Teacher t2 = new Teacher("菩提祖师", per);
			// 依次将四个对象写入输出流
			oos.writeObject(per);
			oos.writeObject(t1);
			oos.writeObject(t2);

			// 同一对象(per)多次序列化之后，只有第一次调用writeObject()序列化对象时才进行对象序列化。即便对象值更新了，
			// 再次调用writeObject()进行序列化时，也不会执行序列化操作。也就是说java中对象只能进行一次序列化操作.
			per.setName("jiaolong");
			oos.writeObject(per);
			
			oos.writeObject(t2);
			
			//读取获取新的对象
			read(ois);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void read(ObjectInputStream ois) throws Exception {
		// 依次读取ObjectInputStream输入流中的四个对象		
		Person p = (Person) ois.readObject();
		Teacher t1 = (Teacher) ois.readObject();
		Teacher t2 = (Teacher) ois.readObject();
		Person p1 = (Person) ois.readObject();
		Teacher t3 = (Teacher) ois.readObject();
		
		// 输出true
		System.out.println("t1的student引用和p是否相同：" + (t1.getStudent() == p));
		// 输出true
		System.out.println("t2的student引用和p是否相同：" + (t2.getStudent() == p));
		// 输出true
		System.out.println("t2和t3是否是同一个对象：" + (t2 == t3));
		// 输出false
		System.out.println("t1和t2是否是同一个对象：" + (t1 == t2));
		// 输出false
		System.out.println("p的名字："+p1.getName()+"     p1的名字："+p1.getName());
		System.out.println("p和p1是同一个对象:"+ (p1 == p)+",p1名字为修改为jiaolong后,java未真正执行序列化操作" );
	}
}
