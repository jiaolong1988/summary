/**
 * �ⲿ�� ���Է��� �ڲ����һ�б����ͷ��������۱����뷽���Ƿ�˽��
 * @author jiaolong
 * @date 2023-07-07 11:42:01
 */
public class InnerClassPrivateVar {
	
	class A{
		private String x = "x";
		private void test() {
			System.out.println("A Private Method");
		}
	}
	
	static class B{
		private String y = "y";
		
		private void test() {
			System.out.println("B Private Method");
		}
	}
	
	public void test() {
		A a = new A();
		System.out.println("A private filed:"+a.x);
		a.test();
	}
	
	public static void main(String[] args) {
		InnerClassPrivateVar t = new InnerClassPrivateVar();
		t.test();
		
		System.out.println();
		
		B b= new B();
		System.out.println("B private filed:"+b.y);
		b.test();
		
		 
	}
}