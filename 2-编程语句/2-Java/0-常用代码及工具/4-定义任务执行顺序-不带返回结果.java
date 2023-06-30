package jh;

import java.util.function.Consumer;

/**
 * 定义任务执行顺序，且任务不带返回值。
 * 顺序执行无返回值的方法。
 * @author jiaolong
 * @date 2023-03-01 03:42:16
 */
public class TempTest {

	public static void main(String[] args) {

		//定义两个操作内容
	//	Consumer<String> f1 = System.out::println;;
		Consumer<String> f1 = n->{
			System.out.println("f1 n-->" + n);
			//无法修改参数内容
			n="xxx";
		};
	    Consumer<String> f2 = n -> {
	    	System.out.println("f2 n-->" + n);
	    	System.out.println(n + "-F2");
	    };

	    //执行完f1后再执行f2的Accept方法
	    f1.andThen(f2).accept("指定初始参数");
	    
	    System.out.println("===========");
	    
	    //连续执行F的Accept方法
	//    f1.andThen(f2).andThen(f1).andThen(f2).accept("test1");

	}

	
}
