package com.founder.conver.test.copyNewObject.clone;

public class CreateObject {

	public static void main(String[] args) throws CloneNotSupportedException {
		Person p1 = new Person("zhangsan",21);
	    p1.setAddress("湖北省", "武汉市");
	    
	    Person p2 = (Person) p1.clone();
	    System.out.println("p1:"+p1);
	    System.out.println("p1.getPname:"+p1.getPname().hashCode());
	    
	    System.out.println("================");
	    System.out.println("p2:"+p2);
	    System.out.println("p2.getPname:"+p2.getPname().hashCode());
	    
	    System.out.println("================");
	    
	    p1.display("p1");
	    p2.display("p2");
	    System.out.println("================将复制之后的对象地址修改：");
	    p2.setAddress("湖北省", "荆州市");
	    p1.display("p1");
	    p2.display("p2");
	}

}
