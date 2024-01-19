package test.base;
/**
	下划线定义数字
**/
public class UnderscoreTest
{
	public static void main(String[] args)
	{
		// 定义一个32位的二进制数,最高位是符号位。数字为：-2147483645
		int binVal = 0B1000_0000_0000_0000_0000_0000_0000_0011;
		System.out.println(binVal);
		
		//double类型下划线表示数字
		double pi = 3.14_15_92_65_36;			
		double height = 8_8_4_8.23;
		System.out.println(pi);
		System.out.println(height);
	}
}
