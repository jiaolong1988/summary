package com.founder.conver.test.copyNewObject.clone;

public class CreateObject {

	public static void main(String[] args) throws CloneNotSupportedException {
		Person p1 = new Person("zhangsan",21);
	    p1.setAddress("����ʡ", "�人��");
	    
	    Person p2 = (Person) p1.clone();
	    System.out.println("p1:"+p1);
	    System.out.println("p1.getPname:"+p1.getPname().hashCode());
	    
	    System.out.println("================");
	    System.out.println("p2:"+p2);
	    System.out.println("p2.getPname:"+p2.getPname().hashCode());
	    
	    System.out.println("================");
	    
	    p1.display("p1");
	    p2.display("p2");
	    System.out.println("================������֮��Ķ����ַ�޸ģ�");
	    p2.setAddress("����ʡ", "������");
	    p1.display("p1");
	    p2.display("p2");
	}

}
