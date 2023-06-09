package com.founder.jt;

interface Output {
	// �ӿ��ﶨ��ĳ�Ա����ֻ���ǳ���,����public static final
	int MAX_CACHE_LINE = 50;

	// �ڽӿ��ж����෽������Ҫʹ��static����
	static String staticTest() {
		return "�ӿ�����෽��";
	}
	
	// �ӿ��ﶨ�����ͨ����ֻ����public�ĳ��󷽷�
	void out();
	void getData(String msg);

	// �ڽӿ��ж���Ĭ�Ϸ�������Ҫʹ��default����
	default void print(String... msgs) {
		for (String msg : msgs) {
			System.out.println(msg);
		}
	}
	// �ڽӿ��ж���Ĭ�Ϸ�������Ҫʹ��default����
	default void test() {
		System.out.println("Ĭ�ϵ�test()����");
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
        //�ӿ�ֱ��ʹ�õķ���
		System.out.println(Output.MAX_CACHE_LINE);
		System.out.println(Output.staticTest());
		
		//ʵ�ֽӿ�ʵ�����ʱ�򣬿��Ե��ýӿ�Ĭ�Ϸ���
		OutputImplement a = new OutputImplement();
		a.print("a","b","c");
		a.test();
	}
}
