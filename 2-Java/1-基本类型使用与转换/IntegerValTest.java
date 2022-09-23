package test.base;


public class IntegerValTest
{
	public static void main(String[] args)
	{
	
	   //0b 二进制  0x 十六进
	   //二个8位的二进制数 默认是 int类型
		int binVal1  = 0b11010100;
		// 定义一个32位的二进制数,最高位是符号位。
		int binVal2 = 0B10000000000000000000000000000011;
		System.out.println(binVal1); // 输出212
		System.out.println(binVal2); // 输出-2147483645
		System.out.println("===============================");
		
		/*
		 定义一个8位的二进制，该数值默认占32位，因此它是一个正数。
		 只是强制类型转换成byte时产生了溢出，最终导致binByte变成了-23，因为byte数字范围是-128到127
		 */
		int binInt = 0b11101001;
		byte binByte =  (byte)0b11101001;
		System.out.println("(int)0b11101001="+binInt+"   (byte)0b11101001="+binByte); // 输出-23
		System.out.println("===============================");
		
		
		/*
		  定义一个32位的二进制数,最高位是1。
		  但由于数值后添加了L后缀，因此该整数的实际占64位，第32位的1不是符号位。
		  因此binVal5的值等于2的31次方 + 2 + 1
		 */
		long binVal5 = 0B10000000000000000000000000000011L;
		System.out.println(binVal5); // 输出2147483651
	}
}