package test.base;


public class IntegerValTest
{
	public static void main(String[] args)
	{
	
	   //0b ������  0x ʮ����
	   //����8λ�Ķ������� Ĭ���� int����
		int binVal1  = 0b11010100;
		// ����һ��32λ�Ķ�������,���λ�Ƿ���λ��
		int binVal2 = 0B10000000000000000000000000000011;
		System.out.println(binVal1); // ���212
		System.out.println(binVal2); // ���-2147483645
		System.out.println("===============================");
		
		/*
		 ����һ��8λ�Ķ����ƣ�����ֵĬ��ռ32λ���������һ��������
		 ֻ��ǿ������ת����byteʱ��������������յ���binByte�����-23����Ϊbyte���ַ�Χ��-128��127
		 */
		int binInt = 0b11101001;
		byte binByte =  (byte)0b11101001;
		System.out.println("(int)0b11101001="+binInt+"   (byte)0b11101001="+binByte); // ���-23
		System.out.println("===============================");
		
		
		/*
		  ����һ��32λ�Ķ�������,���λ��1��
		  ��������ֵ�������L��׺����˸�������ʵ��ռ64λ����32λ��1���Ƿ���λ��
		  ���binVal5��ֵ����2��31�η� + 2 + 1
		 */
		long binVal5 = 0B10000000000000000000000000000011L;
		System.out.println(binVal5); // ���2147483651
	}
}