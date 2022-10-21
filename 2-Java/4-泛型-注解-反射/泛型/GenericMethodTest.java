
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericMethodTest{

	static <T> T tt(T t1, T t2) {
		return t2;
	}	
	
	// 声明一个泛型方法，该泛型方法中带一个T类型形参
	static <T> void test(Collection<? extends T> from, Collection<T> to){
		
	}
	
	// 声明一个泛型方法，该泛型方法中带一个T类型形参，
	static <T> void fromArrayToCollection(T[] a, Collection<T> c){
	}
	
	
	public static void main(String[] args){
	
		

		/**
		 * 泛型方法通过 参数类型中(带泛型的参数)  是否存在父子关系 的来确定方法中泛型的实际类型
		 *
		 */
		List<String> as = new ArrayList<>();
		List<Object> ao = new ArrayList<>();
		// 下面代码将产生编译错误
		test(as,ao);
		
		
		Collection<String> cs = new ArrayList<>();
		Collection<Object> co = new ArrayList<>();
		Integer[] ia = new Integer[100];
		Float[] fa = new Float[100];
		Number[] na = new Number[100];
		Collection<Number> cn = new ArrayList<>();
		// 下面代码中T代表Number类型
		fromArrayToCollection(ia, cn);
		// 下面代码中T代表Number类型
		fromArrayToCollection(fa, cn);
		// 下面代码中T代表Number类型
		fromArrayToCollection(na, cn);
		
		System.out.println(tt(na, cs).getClass().getSuperclass().getName());
		
		
		// 下面代码中T代表Object类型
		fromArrayToCollection(na, co);		
		// 下面代码中T代表String类型，但na是一个Number数组，
		// 因为Number既不是String类型，
		// 也不是它的子类，所以出现编译错误
		//fromArrayToCollection(na, cs);
		
	}
}

