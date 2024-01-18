interface AA{
	void xx();
}

interface BB extends AA {

	/**
	 * CC接口 与 AA接口的方法名称一致.
	 * 	重写 aa接口的xx方法
	 */
	default void xx() {
		System.out.println("接口实现xx");
	}
}

interface CC extends AA {
	/**
	 * CC接口 与 AA接口的方法名称一致，不重写：
	 * 	1.也可以省略，由实现类去实现。如果不写方法名，表示这个接口根本就没有存在的必要。
	 *  2.不省略，也就是与父接口重名，说明该接口与父接口的实现是不一致，让实现该接口的类去实现具体逻辑。
	 */
	
	void xx();
}


public class TestInterface {

	public static void main(String[] args) {

		//在子接口中 重写 父接口实现
		BB b = new BB(){};
		b.xx();
		
		//实现类 实现 接口方法。
		CC c = new CC(){
			@Override
			public void xx() {	
				System.out.println("实现类 实现cc");
			}
		};
		c.xx();
	}

}
