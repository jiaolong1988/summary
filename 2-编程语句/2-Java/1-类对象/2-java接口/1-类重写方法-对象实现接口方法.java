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
		//��д���й�������
		Test1 t = new Test1() {
			@Override
			public void test1() {
				System.out.println("this is new test1 method.");
			}			
		};
		t.test1();
		
		//ʵ�ֽӿڷ���
		TestInterface it = new TestInterface() {		
			@Override
			public void iTest() {
				System.out.println("this is interfact method.");
				
			}
		};	
		it.iTest();
		
}
}
