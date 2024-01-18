package encapsulation;

/**
 * @author jiaolong
 * @date 2024-1-18 11:13
 *  初始化顺序是：类静态初始化块，对象初始化块，构造函数初始化。
 *  初始化时变量必须未指定值
 *  	1):类静态初始化块只能 初始化 静态final变量和静态方法。
 * 		2):对象初始化块只能 初始化 非静态的final变量 和任何 静态和非静态方法。
 *  	3):构造器能能初始化 任何变量和方法。
 */
public class FinalVariableTest {
	// 定义成员变量时指定默认值，合法。
	final int a = 6;
	
	// 下面变量将在构造器或初始化块中分配初始值
	final String str;
	final int c;
	final static double d ;


	// 1.类静态初始化块
	static {
		// 只能为【类变量】指定初始值。
		d = 5.6;
		System.out.println("类静态初始化块");
		testStatic();
	}

	// 2.对象初始化块
	{
		//在初始化块中为【实例变量】指定初始值，合法
		str = "Hello";

		// 定义a实例变量时已经指定了默认值，
		// 不能为a重新赋值，因此下面赋值语句非法
		// a = 9;
		System.out.println("对象初始化块");

		testStatic();
		test();
	}


	// 3.构造器，可对既没有指定默认值、有没有在初始化块中
	public FinalVariableTest() {
		c = 5;
//		str = "Hello";
//		d=6;
		System.out.println("构造器初始化");


		testStatic();
		test();
	}

	//4.普通方法 不能 为final修饰的成员变量 赋值
	public static void changeFinal()
	{
		// 普通方法不能为final修饰的成员变量赋值
		// d = 1.2;
		// 不能在普通方法中为final成员变量指定初始值
		// ch = 'a';
	}

	public static void testStatic(){

	}

	public void test(){

	}
	public static void main(String[] args) {

		/*
		输出结果：
			类静态初始化块
			对象初始化块
			构造器初始化
			final a的值：6
			final c的值：5
			final d的值：5.6
		 */
		FinalVariableTest ft = new FinalVariableTest();
		System.out.println("final a的值："+ft.a);
		System.out.println("final c的值："+ft.c);
		System.out.println("final d的值："+ft.d);
	}
}
