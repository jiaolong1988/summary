

class Foo
{
	public <T> Foo(T t)
	{
		System.out.println(t);
	}
}
public class GenericConstructor
{
	public static void main(String[] args)
	{
		// ���͹������е�T����ΪString��
		new Foo("���Java����");
		// ���͹������е�T����ΪInteger��
		new Foo(200);
		// ��ʽָ�����͹������е�T����ΪString��
		// ����Foo��������ʵ��Ҳ��String������ȫ��ȷ��
		new <String> Foo("���Android����");
		// ��ʽָ�����͹������е�T����ΪString��
		// ������Foo��������ʵ����Double��������������
		new <String> Foo(12.3);
	}
}
