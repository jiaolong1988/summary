package com.saveFileDemo;

/**
 * java λ����
 * @author jiaolong
 * @date 2023-02-13 05:51:50
 */
public class BitTest {

	public static void main(String[] args) {
		//ת��Ϊ16����
		System.out.println("-2145577635 ת��Ϊ16����"+Integer.toHexString(-2145577635));		
	
		byte tb = -12;
		System.out.println("=======byte����8λ������ �Զ�ת��Ϊ 32λint���Ͷ�����======");		
		System.out.println("-12 byte��ʽ�Ķ����ƣ�"+ Integer.toBinaryString(tb)); 
		System.out.println("-12 int ��ʽ�Ķ����ƣ�"+Integer.toBinaryString((int)tb)); 
		
		System.out.println("\n=======��int����32Ϊ������ ת��Ϊ 8λbyte���Ͷ����� ======");
		//����24λȫ�����0����8λ���ֲ���
		System.out.println("-12 byte & 0xFF��ʽ�Ķ����ƣ�"+Integer.toBinaryString(tb & 0xFF));  
		System.out.println("-12 int  & 0xFF��ʽ�Ķ����ƣ�"+Integer.toBinaryString(((int)tb) & 0xFF));  
		
		System.out.println("\n=======byte����8λ������ ת��Ϊ int��byte��ֵ�ǲ�һ����======");
		System.out.println("-12 int ���Ͷ����Ƶ�ֵ"+(int)(tb & 0xFF));
		System.out.println("-12 byte���Ͷ����Ƶ�ֵ"+(byte)(tb & 0xFF));
	
		System.out.println("\n=======0xFF����ʲô======");
		int ox = 0xFF;
		System.out.println("0xFF��ʲô��"+0xFF);
		System.out.println("0xFF��һ�����ֿɸ�ֵ��һ��int������"+ox);
		System.out.println("0xFF���������֣�"+Integer.toBinaryString(0xFF));
		
		byte[] md5Array = { 11, -21, 21 };
		String str = bytesToHex(md5Array);
		System.out.println("\n��11, -21, 21�� --> ʮ������:"+str);
	}

	/**
	 * ���ֽ������ÿ��Ԫ��ת��Ϊ16����
	 * @param md5Array
	 * @return
	 */
	public static String bytesToHex(byte[] md5Array) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < md5Array.length; i++) {
			int temp = 0xff & md5Array[i];// TODO:�˴�Ϊʲô��� 0xff & ��
			String hexString = Integer.toHexString(temp);
			if (hexString.length() == 1) {
				// �����ʮ�����Ƶ�0f��Ĭ��ֻ��ʾf����ʱҪ����0
				strBuilder.append("0").append(hexString);
			} else {
				strBuilder.append(hexString);
			}
		}
		return strBuilder.toString();
	}
	
}
