package test.base;
/**
	�»��߶�������
**/
public class UnderscoreTest
{
	public static void main(String[] args)
	{
		// ����һ��32λ�Ķ�������,���λ�Ƿ���λ������Ϊ��-2147483645
		int binVal = 0B1000_0000_0000_0000_0000_0000_0000_0011;
		System.out.println(binVal);
		
		//double�����»��߱�ʾ����
		double pi = 3.14_15_92_65_36;			
		double height = 8_8_4_8.23;
		System.out.println(pi);
		System.out.println(height);
	}
}
