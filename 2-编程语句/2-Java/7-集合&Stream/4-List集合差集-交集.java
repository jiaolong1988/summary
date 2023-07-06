import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {
	public static void main(String[] args) {

		List<String> a = Arrays.asList("1","2","3","4");
		List<String> b = Arrays.asList("3","4","5","6");
		
		/**
		 * �󽻼�-��ͬ
		 *  ��������������ͬ�ĵ�Ԫ�ء�
		 *  realB.retainAll(realA);���ص���ͬԪ�ض������B������
		 */
		Collection<String> realA = new ArrayList<>(a);
		Collection<String> realB = new ArrayList<>(b);		
		realB.retainAll(realA);
		System.out.println("���������" + realB);

		
		/**
		 * ��-����
		 * 	ɾ��ָ�������е�Ԫ�أ������ظü���
		 *  bb.removeAll(aa);��ʾΪ��ɾ������bb������aa���ϵ�Ԫ�أ�������bbʣ���Ԫ�ء�
		 *  =bb - aa
		 */
		Collection<String> realC = new ArrayList<>(a);
		Collection<String> realD = new ArrayList<>(b);
		realC.removeAll(realD);
		System.out.println("������" + realC);
		
	}

}
