package com.founder.conver.test;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

/**
 * https://blog.csdn.net/weixin_48321825/article/details/125772006
 * �ַ����������
 * @author jiaolong
 * @date 2023-02-15 12:00:26
 */
public class TempTest {

	public static void main(String[] args) throws Exception  {
		System.out.println("OS default charset:-->"+System.getProperty("sun.jnu.encoding"));
		System.out.println("jvm default charset:-->"+System.getProperty("file.encoding")+"\n");
		
		
		String str = "����";

		//��1�� ��ʽһ
		//����java�ַ��������һ����׼�������������ǽ��ַ�������ʾ���ַ�����charset���룬�����ֽڷ�ʽ��ʾ��
		byte[] bytes= str.getBytes("UTF-8");
		//����java�ַ����������һ����׼����������һ�������������෴�����ֽ����鰴��charset���н��룬���ת��Ϊunicode�洢���������
		String b = new String(bytes,"UTF-8");
		System.out.println(b);
		
		//��2��-��ʽ2
        String b1 = new String(str.getBytes("UTF-8"),"UTF-8");
        System.out.println(b1);		

        System.out.println("===========================");
		
		// ���б����ʽ
		SortedMap<String,Charset> map = Charset.availableCharsets();
		Set<String> set = map.keySet();
		for(String key : set) {		
			String nfs;
			try {
				nfs = new String(str.getBytes(key), key);
				if(str.equals(nfs)) {
					System.out.println(key+" --> "+nfs);
				}
			} catch (Exception e) {
				
			}
				
			
			Charset charset = (Charset) map.get(key);
		}
		
	}

}
