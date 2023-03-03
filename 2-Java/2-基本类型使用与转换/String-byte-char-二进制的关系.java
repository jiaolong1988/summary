package com.founder.dm;

public class ttt {

	public static void main(String[] args) throws Exception {
		
		String info = "abc890";
		System.out.println("binfo.size():"+ info.getBytes().length);
		for(byte b: info.getBytes()) {
			System.out.println("byte:"+b+" char:" + (char) b +" binary:"+Integer.toBinaryString(b)+" int:"+(int)b);
		}
		
		System.out.println("=========================");	
		
		info = "我爱你中国";		
		System.out.println("sinfo.size():"+ info.toCharArray().length);	
		for(char c: info.toCharArray()) {								
			System.out.println("byte:"+(int)c +" char:"+c+" " +" binary:"+Integer.toBinaryString(c));			
		}
		
		System.out.println("=========================");	
		
		String encode = System.getProperty("file.encoding");
		String encodeString = new String(info.getBytes(), encode);	
		String encodeGBK = new String(info.getBytes(), "GBK");	
		
		System.out.println("编码："+encode+" 内容："+encodeString);
		System.out.println("编码：GBK"+" 内容："+encodeGBK);
		
	}
}