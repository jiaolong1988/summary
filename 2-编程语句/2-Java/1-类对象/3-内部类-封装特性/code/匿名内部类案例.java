import java.util.HashMap;
import java.util.Map;

interface TestInterface{
	void iTest() ;
}

public class Test3 {	
	public static void main(String[] args) {
		
		//�����ڲ���
		TestInterface it = new TestInterface() {		
			@Override
			public void iTest() {
				System.out.println("this is interfact method.");
				
			}
		};	
		it.iTest();
		
}
}
