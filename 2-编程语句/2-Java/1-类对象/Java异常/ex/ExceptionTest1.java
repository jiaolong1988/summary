package test.java.ex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * 
 * @ClassName:  ExceptionTest1   
 * @Description:checked异常与Runtime异常案例    
 * @author: jiaolong
 * @date:   2022年3月22日 上午11:31:36      
 * @Copyright:
 */
public class ExceptionTest1 {

	public static void main(String[] args) {

//checked异常,必须进行继续处理如：try, throws Exception。
		//案例1
		File f = new File("C:\test.txt");
		FileReader r = new FileReader(f); //A
		BufferedReader br = new BufferedReader(r);
		br.readLine(); //B
		br.close(); //C			
		
		//案例2
		test1("");

		//案例3
		testMyException();
				
				
				
//RuntimeException，可以不处理异常
		//案例1
		int a = 0;
		int b = 100;
		int c = b/a;
				
		//案例2
		test2("");		
		
				


	}

	public static void test1(String flag) throws Exception {

		// checked异常
		// 必须在方法中throws异常 或 代码中try catch
		throw new Exception("this is test1!");

	}

	public static void test2(String flag) {
		// runtime异常
		// 异常抛出后，调用这可以不处理异常
		throw new RuntimeException("this is test2!");
	}
	
	
	public static void testMyException()  throws MyException{
		//自定义异常
		System.out.println("this is testMyException！");
		
	}

}
