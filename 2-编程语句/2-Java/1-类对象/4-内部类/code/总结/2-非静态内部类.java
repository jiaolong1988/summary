/**
�Ǿ�̬�ڲ��� 
	1.���ܶ����κ�static�����ͷ����������������ⲿ����κη����ͱ���������static�����ͷ�����
	2.ֻ�����ⲿ���ʵ�������� ����newʵ������
 * @author jiaolong
 * @date 2023-06-30 11:05:14
 */
public class ObjectInnerClas{
	static String staticField="xx";
	static void staticTest() {
		System.out.println("static method");
	}
	
	//�Ǿ�̬�ڲ���
	class A {
		//���ܶ��徲̬����
		//static String c="xx";
		//���ܶ���static����
		//static void aa() {}
		
		//�������þ�̬����
		String c = staticField;
		//�������� ��̬����
		void notStaticMethod() {
			staticTest();
		}
	}
	
	static {
		//���� ʵ���� �Ǿ�̬�ڲ���
		//A a = new A();
	}
	static void staticMethod() {
		//�����ھ�̬���� ʵ���� �Ǿ�̬�ڲ���
		//A a = new A();		
	}
	
	void testInnnerClass() {
		//ֻ���� �Ǿ�̬���� ʵ����
		A a = new A();
	}
		
}
