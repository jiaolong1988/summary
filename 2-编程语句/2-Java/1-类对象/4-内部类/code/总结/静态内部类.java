/**
��̬�ڲ��� 
	1.	���Զ����κα����ͷ���(�����Ǿ�̬���������ͷ���)�����������á�ʵ��������ʵ����������
	2.	�������ⲿ��� �κη����� ʵ��������
 * @author jiaolong
 * @date 2023-06-30 11:05:14
 */
public class StaticInnerClass {
	//�ⲿ��ʵ������
	String a="z";
	void test() {}
	
	static int staticInt=0;

	//��̬�ڲ���
	static class B {
		//1.�����κα����ͷ���
		String c="cc";
		void xx() {} 
		
		static String d="dd";
		static void yy() {}
		int x = staticInt;
			
		
		//2.���������ⲿ�� �ķǾ�̬����
		//String x =a;
		//static String refString =a;
		
		//3.���������ⲿ�� �ķǾ�̬����
		//test();
	}
	
	//������ ��̬����顢��̬������ʵ������ ��ʵ��������
	static {
		 B b = new  B();
	}
	static void staticMethod() {
		 B b = new  B();	
	}	
	void testInnnerClass() {
		 B b = new  B();
	}
}
