import java.util.HashMap;
import java.util.Map;

interface TestInterface{
	void iTest() ;
}

class Test1{
	public void test1() {
		System.out.println("this is old test1 method.");
	}
}

public class Test3 {	
	public static void main(String[] args) {
		//重写类中公共方法
		Test1 t = new Test1() {
			@Override
			public void test1() {
				System.out.println("this is new test1 method.");
			}			
		};
		t.test1();
		
		//实现接口方法
		TestInterface it = new TestInterface() {		
			@Override
			public void iTest() {
				System.out.println("this is interfact method.");
				
			}
		};	
		it.iTest();
		
}
}
