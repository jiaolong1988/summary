package com.founder.conver.test.copyNewObject.serializable.bean;

public class PersonTransient implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private transient int age;

	// ע��˴�û���ṩ�޲����Ĺ�����!
	public PersonTransient(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}
}