package com.founder.jt;

interface Output {
	// 接口里定义的成员变量只能是常量,都是public static final
	int MAX_CACHE_LINE = 50;

	// 在接口中定义类方法，需要使用static修饰
	static String staticTest() {
		return "接口里的类方法";
	}
	
	// 接口里定义的普通方法只能是public的抽象方法
	void out();
	void getData(String msg);

	// 在接口中定义默认方法，需要使用default修饰
	default void print(String... msgs) {
		for (String msg : msgs) {
			System.out.println(msg);
		}
	}
	// 在接口中定义默认方法，需要使用default修饰
	default void test() {
		System.out.println("默认的test()方法");
	}

}

class OutputImplement implements Output {
	
	@Override
	public void out() {
		System.out.println("out no parameter");
	}

	@Override
	public void getData(String msg) {
		System.out.println("out have a parameter");
	}	
}

public class interfaceTest{
	public static void main(String[] args) {
        //接口直接使用的方法
		System.out.println(Output.MAX_CACHE_LINE);
		System.out.println(Output.staticTest());
		
		//实现接口实现类的时候，可以调用接口默认方法
		OutputImplement a = new OutputImplement();
		a.print("a","b","c");
		a.test();
	}
}
