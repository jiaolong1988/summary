package test.base;

/**
 * 
 * @ClassName:  CharTest   
 * @Description:TODO(��������������)   
 * @author: jiaolong
 * @date:   2022��4��21�� ����10:21:14      
 * @Copyright:
 */
public class CharTest
{
	public static void main(String[] args){

		// ʹ��Unicode����ֵ��ָ���ַ�ֵ,�����һ��'��'�ַ�
		System.out.println('\u9999');		
		System.out.println("=========================");
		
		// ����һ��'��'�ַ�ֵ
		char zhong = '��';
		// ֱ�ӽ�һ��char��������int���ͱ���ʹ��
		int zhongValue = zhong;
		System.out.println("int:"+zhongValue+" char:"+zhong+" (int) zhong:"+zhong);		
		System.out.println("=========================");
		
		
		// ֱ�Ӱ�һ��0��65535��Χ�ڵ�int��������һ��char����
		char c = 30127;
		System.out.println(c);
		
	}
}

