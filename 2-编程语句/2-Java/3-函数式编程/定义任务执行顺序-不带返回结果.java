package jh;

import java.util.function.Consumer;

/**
 * ��������ִ��˳�������񲻴�����ֵ��
 * ˳��ִ���޷���ֵ�ķ�����
 * @author jiaolong
 * @date 2023-03-01 03:42:16
 */
public class TempTest {

	public static void main(String[] args) {

		//����������������
	//	Consumer<String> f1 = System.out::println;;
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
	    f1.andThen(f2).accept("ָ����ʼ����");
	    
	    System.out.println("===========");
	    
	    //����ִ��F��Accept����
	//    f1.andThen(f2).andThen(f1).andThen(f2).accept("test1");

	}

	
}
