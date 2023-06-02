
import java.util.*;

public class MyUtils
{
	// ����dest����Ԫ�����ͱ�����src����Ԫ��������ͬ�������丸��
	public static <T> T copy(Collection<? super T> dest, Collection<T> src)
	{
		T last = null;
		for (T ele  : src)
		{
			last = ele;
			dest.add(ele);
		}
		return last;
	}
	public static void main(String[] args)
	{
		List<Number> ln = new ArrayList<>();
		List<Integer> li = new ArrayList<>();
		li.add(5);
		// �˴���׼ȷ��֪�����һ�������Ƶ�Ԫ����Integer����
		// ��src����Ԫ�ص�������ͬ
		Integer last = copy(ln , li);    // ��
		System.out.println(ln);
	}
}