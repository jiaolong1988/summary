package test.base;

/**
 * 
 * @ClassName:  CharTest   
 * @Description:TODO(描述这个类的作用)   
 * @author: jiaolong
 * @date:   2022年4月21日 上午10:21:14      
 * @Copyright:
 */
public class CharTest
{
	public static void main(String[] args){

		// 使用Unicode编码值来指定字符值,将输出一个'香'字符
		System.out.println('\u9999');		
		System.out.println("=========================");
		
		// 定义一个'疯'字符值
		char zhong = '疯';
		// 直接将一个char变量当成int类型变量使用
		int zhongValue = zhong;
		System.out.println("int:"+zhongValue+" char:"+zhong+" (int) zhong:"+zhong);		
		System.out.println("=========================");
		
		
		// 直接把一个0～65535范围内的int整数赋给一个char变量
		char c = 30127;
		System.out.println(c);
		
	}
}

