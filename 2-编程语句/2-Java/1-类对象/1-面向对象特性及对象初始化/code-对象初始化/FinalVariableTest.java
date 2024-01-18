package encapsulation;

/**
 * @author jiaolong
 * @date 2024-1-18 11:13
 *  ��ʼ��˳���ǣ��ྲ̬��ʼ���飬�����ʼ���飬���캯����ʼ����
 *  ��ʼ��ʱ��������δָ��ֵ
 *  	1):�ྲ̬��ʼ����ֻ�� ��ʼ�� ��̬final�����;�̬������
 * 		2):�����ʼ����ֻ�� ��ʼ�� �Ǿ�̬��final���� ���κ� ��̬�ͷǾ�̬������
 *  	3):���������ܳ�ʼ�� �κα����ͷ�����
 */
public class FinalVariableTest {
	// �����Ա����ʱָ��Ĭ��ֵ���Ϸ���
	final int a = 6;
	
	// ����������ڹ��������ʼ�����з����ʼֵ
	final String str;
	final int c;
	final static double d ;


	// 1.�ྲ̬��ʼ����
	static {
		// ֻ��Ϊ���������ָ����ʼֵ��
		d = 5.6;
		System.out.println("�ྲ̬��ʼ����");
		testStatic();
	}

	// 2.�����ʼ����
	{
		//�ڳ�ʼ������Ϊ��ʵ��������ָ����ʼֵ���Ϸ�
		str = "Hello";

		// ����aʵ������ʱ�Ѿ�ָ����Ĭ��ֵ��
		// ����Ϊa���¸�ֵ��������渳ֵ���Ƿ�
		// a = 9;
		System.out.println("�����ʼ����");

		testStatic();
		test();
	}


	// 3.���������ɶԼ�û��ָ��Ĭ��ֵ����û���ڳ�ʼ������
	public FinalVariableTest() {
		c = 5;
//		str = "Hello";
//		d=6;
		System.out.println("��������ʼ��");


		testStatic();
		test();
	}

	//4.��ͨ���� ���� Ϊfinal���εĳ�Ա���� ��ֵ
	public static void changeFinal()
	{
		// ��ͨ��������Ϊfinal���εĳ�Ա������ֵ
		// d = 1.2;
		// ��������ͨ������Ϊfinal��Ա����ָ����ʼֵ
		// ch = 'a';
	}

	public static void testStatic(){

	}

	public void test(){

	}
	public static void main(String[] args) {

		/*
		��������
			�ྲ̬��ʼ����
			�����ʼ����
			��������ʼ��
			final a��ֵ��6
			final c��ֵ��5
			final d��ֵ��5.6
		 */
		FinalVariableTest ft = new FinalVariableTest();
		System.out.println("final a��ֵ��"+ft.a);
		System.out.println("final c��ֵ��"+ft.c);
		System.out.println("final d��ֵ��"+ft.d);
	}
}
