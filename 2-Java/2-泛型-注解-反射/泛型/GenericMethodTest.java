
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericMethodTest{

	static <T> T tt(T t1, T t2) {
		return t2;
	}	
	
	// ����һ�����ͷ������÷��ͷ����д�һ��T�����β�
	static <T> void test(Collection<? extends T> from, Collection<T> to){
		
	}
	
	// ����һ�����ͷ������÷��ͷ����д�һ��T�����βΣ�
	static <T> void fromArrayToCollection(T[] a, Collection<T> c){
	}
	
	
	public static void main(String[] args){
	
		

		/**
		 * ���ͷ���ͨ�� ����������(�����͵Ĳ���)  �Ƿ���ڸ��ӹ�ϵ ����ȷ�������з��͵�ʵ������
		 *
		 */
		List<String> as = new ArrayList<>();
		List<Object> ao = new ArrayList<>();
		// ������뽫�����������
		test(as,ao);
		
		
		Collection<String> cs = new ArrayList<>();
		Collection<Object> co = new ArrayList<>();
		Integer[] ia = new Integer[100];
		Float[] fa = new Float[100];
		Number[] na = new Number[100];
		Collection<Number> cn = new ArrayList<>();
		// ���������T����Number����
		fromArrayToCollection(ia, cn);
		// ���������T����Number����
		fromArrayToCollection(fa, cn);
		// ���������T����Number����
		fromArrayToCollection(na, cn);
		
		System.out.println(tt(na, cs).getClass().getSuperclass().getName());
		
		
		// ���������T����Object����
		fromArrayToCollection(na, co);		
		// ���������T����String���ͣ���na��һ��Number���飬
		// ��ΪNumber�Ȳ���String���ͣ�
		// Ҳ�����������࣬���Գ��ֱ������
		//fromArrayToCollection(na, cs);
		
	}
}

