package com.founder.conver.test.copyNewObject;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.BeanUtils;

import com.founder.conver.test.copyNewObject.serializable.bean.Person;
import com.founder.conver.test.copyNewObject.serializable.bean.Teacher;
import com.google.gson.Gson;


public class DeepCopyTest {

	public static void main(String[] args) {
		System.out.println("========commons-lang工具包提供的对象深拷贝=============");
		/**
		 * commons-lang包的java对象深度拷贝。
		 * 使用的时java io的对象流进行实现，通过ByteArrayOutputStream将对象信息保存在字节数组中
		 * @author jiaolong
		 *
		 */
		//通过io流的对象流进行对象的实例化与反实例化
		Person p = new Person("jiaolong",20);
		Teacher t = new Teacher("teacher", p);
		//深拷贝对象
		Teacher tCopy = (Teacher)SerializationUtils.clone(t);
		System.out.println("Teacher拷贝前的数据："+t.getName()+"- "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		
		p.setName("jl");
		System.out.println("修改Teacher拷贝前的数据："+t.getName()+"- "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		System.out.println("Teacher拷贝后的数据："+tCopy.getName()+" - "+tCopy.getStudent().getName()+" - "+t.getStudent().getAge());		
		
		
		System.out.println("\n=========spring提供的深拷贝工具(推荐)===========");		
		//通过反射实现
		Teacher nt = new Teacher();
		BeanUtils.copyProperties(t, nt);
		System.out.println("spring Teacher拷贝前的数据："+t.getName()+" - "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		
		t.setName("xxxx");
		System.out.println("修改spring Teacher拷贝前的数据："+t.getName()+" - "+t.getStudent().getName()+" - "+t.getStudent().getAge());
		System.out.println("spring Teacher拷贝后的数据："+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
		
		System.out.println("\n=========Gson进行json的序列化进行深拷贝============");		
		Gson gson = new Gson();
		Teacher teacher = gson.fromJson(gson.toJson(nt), Teacher.class);
		System.out.println("Gson Teacher拷贝前的数据："+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
	
		nt.setName("xxxx");
		System.out.println("修改Gson Teacher拷贝前的数据："+nt.getName()+" - "+nt.getStudent().getName()+" - "+nt.getStudent().getAge());
		System.out.println("Gson Teacher拷贝后的数据："+teacher.getName()+" - "+teacher.getStudent().getName()+" - "+teacher.getStudent().getAge());


	}

}
