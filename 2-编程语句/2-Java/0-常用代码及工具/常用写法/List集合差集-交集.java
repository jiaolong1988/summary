import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Test {
	public static void main(String[] args) {

		List<String> a = Arrays.asList("1","2","3","4");
		List<String> b = Arrays.asList("3","4","5","6");
		
		/**
		 * 求交集-求同
		 *  返回两个集合相同的的元素。
		 *  realB.retainAll(realA);返回的相同元素都存放在B集合中
		 */
		Collection<String> realA = new ArrayList<>(a);
		Collection<String> realB = new ArrayList<>(b);		
		realB.retainAll(realA);
		System.out.println("交集结果：" + realB);

		
		/**
		 * 求差集-存异
		 * 	删除指定集合中的元素，并返回该集合
		 *  bb.removeAll(aa);表示为：删除集合bb中所有aa集合的元素，并返回bb剩余的元素。
		 *  =bb - aa
		 */
		Collection<String> realC = new ArrayList<>(a);
		Collection<String> realD = new ArrayList<>(b);
		realC.removeAll(realD);
		System.out.println("差集结果：" + realC);
		
	}

}
