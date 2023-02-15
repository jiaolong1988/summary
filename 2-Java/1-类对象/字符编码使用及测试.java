package com.founder.conver.test;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

/**
 * https://blog.csdn.net/weixin_48321825/article/details/125772006
 * 字符集编码测试
 * @author jiaolong
 * @date 2023-02-15 12:00:26
 */
public class TempTest {

	public static void main(String[] args) throws Exception  {
		System.out.println("OS default charset:-->"+System.getProperty("sun.jnu.encoding"));
		System.out.println("jvm default charset:-->"+System.getProperty("file.encoding")+"\n");
		
		
		String str = "繁体";

		//【1】 方式一
		//这是java字符串处理的一个标准函数，其作用是将字符串所表示的字符按照charset编码，并以字节方式表示。
		byte[] bytes= str.getBytes("UTF-8");
		//这是java字符串处理的另一个标准函数，和上一个函数的作用相反，将字节数组按照charset进行解码，最后转换为unicode存储进行输出。
		String b = new String(bytes,"UTF-8");
		System.out.println(b);
		
		//【2】-方式2
        String b1 = new String(str.getBytes("UTF-8"),"UTF-8");
        System.out.println(b1);		

        System.out.println("===========================");
		
		// 所有编码格式
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
