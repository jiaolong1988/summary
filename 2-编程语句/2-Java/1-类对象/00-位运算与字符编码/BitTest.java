package com.saveFileDemo;

/**
 * java 位运算
 * @author jiaolong
 * @date 2023-02-13 05:51:50
 */
public class BitTest {

	/* 输出内容：
		-2145577635 转换为16进制801d155d
		=======byte类型8位二进制 自动转换为 32位int类型二进制======
		-12 byte形式的二进制：11111111111111111111111111110100
		-12 int 形式的二进制：11111111111111111111111111110100
		
		=======将int类型32为二进制 转换为 8位byte类型二进制 ======
		-12 byte & 0xFF形式的二进制：11110100
		-12 int  & 0xFF形式的二进制：11110100
		
		=======byte类型8位二进制 转换为 int、byte的值是不一样的======
		-12 int 类型二进制的值244
		-12 byte类型二进制的值-12
		
		=======0xFF代表什么======
		0xFF是什么：255
		0xFF是一个数字可赋值给一个int变量：255
		0xFF二进制数字：11111111
		
		【11, -21, 21】 --> 十六进制:0b eb 15  
	 */
	public static void main(String[] args) {
		//转换为16进制
		System.out.println("-2145577635 转换为16进制"+Integer.toHexString(-2145577635));

		byte tb = -12;
		System.out.println("=======byte类型8位二进制 自动转换为 32位int类型二进制======");
		System.out.println("-12 byte形式的二进制："+ Integer.toBinaryString(tb));
		System.out.println("-12 int 形式的二进制："+Integer.toBinaryString((int)tb));

		System.out.println("\n=======将int类型32为二进制 转换为 8位byte类型二进制 ======");
		//将高24位全部变成0，低8位保持不变
		System.out.println("-12 byte & 0xFF形式的二进制："+Integer.toBinaryString(tb & 0xFF));
		System.out.println("-12 int  & 0xFF形式的二进制："+Integer.toBinaryString(((int)tb) & 0xFF));

		System.out.println("\n=======byte类型8位二进制 转换为 int、byte的值是不一样的======");
		System.out.println("-12 int 类型二进制的值"+(int)(tb & 0xFF));
		System.out.println("-12 byte类型二进制的值"+(byte)(tb & 0xFF));

		System.out.println("\n=======0xFF代表什么======");
		int ox = 0xFF;
		System.out.println("0xFF是什么："+0xFF);
		System.out.println("0xFF是一个数字可赋值给一个int变量："+ox);
		System.out.println("0xFF二进制数字："+Integer.toBinaryString(0xFF));

		byte[] md5Array = { 11, -21, 21 };
		String str = bytesToHex(md5Array);
		System.out.println("\n【11, -21, 21】 --> 十六进制:"+str);
	}

	/**
	 * 将字节数组的每个元素转换为16进制
	 * @param md5Array
	 * @return
	 */
	public static String bytesToHex(byte[] md5Array) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < md5Array.length; i++) {
			int temp = 0xff & md5Array[i];// TODO:此处为什么添加 0xff & ？
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1) {
				// 如果是十六进制的0f，默认只显示f，此时要补上0
				strBuilder.append("0").append(hexString);
			} else {
				strBuilder.append(hexString);
			}
			strBuilder.append(" ");
		}
		return strBuilder.toString();
	}
}

