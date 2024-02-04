package jh;

import java.util.function.Consumer;

/**
 * ��������ִ��˳��.
 * @author jiaolong
 * @date 2023-03-01 03:42:16
 */
public class TempTest {

	public static void main(String[] args) {
		/*
			f ... ���ղ�����1 ���ز���Ϊ:3
			g ... ���ղ���: 3 ���ؽ����6
			andThen �������Ϊ��1�� ���ؽ��Ϊ: 6
		*/
		//��һ���������ָ��������ǰһ������ķ��ؽ������һ����������������
		functionTest();

		System.out.println("===========");
		/*
			f1 n-->������ͬ�Ĳ���
			f2 n-->������ͬ�Ĳ���
			������ͬ�Ĳ���-F2
			===========
		*/
		// consumer������ͬ�Ĳ�����ִ�в�ͬ������ÿ������û�з��ؽ��
		ConsumerTest();
	}

	/**
	 * function ��һ���������ָ��������ǰһ������ķ��ؽ������һ����������������
	 * @return: void
	 **/
	public static void functionTest() {
		Function<Integer, Integer> f = s -> {
			System.out.println("f ... ���ղ�����"+s+" ���ز���Ϊ:3");
			return 3;
		};

		Function<Integer, String> g = s -> {
			String res = String.valueOf(s*2);
			System.out.println("g ... ���ղ���: "+s +" ���ؽ����"+res);

			return res;
		};

		//ע�ⷵ�ص���String���ͣ�����Integer
		String calculatingResult = f.andThen(g).apply(1);
		System.out.println("andThen �������Ϊ��1�� ���ؽ��Ϊ: "+calculatingResult);

	}


	/**
	 * consumer������ͬ�Ĳ�����ִ�в�ͬ������ÿ������û�з��ؽ��
	 */
	public static void ConsumerTest() {

		//	Consumer<String> f1 = System.out::println;
		//����������������
		Consumer<String> f1 = n->{
			System.out.println("f1 n-->" + n);
			//�޷��޸Ĳ�������
			n="xxx";
		};
	    Consumer<String> f2 = n -> {
	    	System.out.println("f2 n-->" + n);
	    	System.out.println(n + "-F2");
	    };

	    //ִ����f1����ִ��f2��Accept����
	    f1.andThen(f2).accept("������ͬ�Ĳ���");
	    
	    System.out.println("===========");
	    
	    //����ִ��F��Accept����
	   // f1.andThen(f2).andThen(f1).andThen(f2).accept("test1");
	}
	
}
