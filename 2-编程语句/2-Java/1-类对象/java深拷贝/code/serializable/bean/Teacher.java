package com.founder.conver.test.copyNewObject.serializable.bean;

public class Teacher implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Person student;
	private String testInfo;
	public Teacher() {}
	public Teacher(String name, Person student) {
		this.name = name;
		this.student = student;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public Person getStudent() {
		return this.student;
	}
	public String getTestInfo() {
		return testInfo;
	}
	public void setTestInfo(String testInfo) {
		this.testInfo = testInfo;
	}
	
}