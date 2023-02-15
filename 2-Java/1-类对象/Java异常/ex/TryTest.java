package test.java.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * 
 * @ClassName:  TryTest   
 * @Description:TODO(自动关闭流)   
 * @author: jiaolong
 * @date:   2022年3月22日 下午5:15:06      
 * @Copyright:
 */
public class TryTest {

	public static void main(String[] args) {
		String fielPath = "D:\\test\\test.sql";
		redFile(fielPath);
	}
	
	public static void redFile(String filePath) {
		File file = new File(filePath);     
		//自动关闭流
		try (InputStream in = new FileInputStream(file);) {

			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}

			System.out.println("数据读取完毕, 并执行FileInputStream的close()方法！");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
