
import java.util.*;

public class RightTest
{
	// ����һ�����ͷ������÷��ͷ����д�һ��T�β�
	static <T> void test(Collection<? extends T> from , Collection<T> to)
	{
		for (T ele : from)
		{
			to.add(ele);
		}
	}
	public static void main(String[] args)
	{
		List<Object> ao = new ArrayList<>();
		List<String> as = new ArrayList<>();
		// ���������ȫ����
		test(as , ao);
	}
}
