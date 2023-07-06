package pool;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * CompletableFuture-串行方法使用案例
 * @author jiaolong
 * @date 2023-07-05 05:03:26
 */
public class CompletableFutureTest {
	
	public static void main(String[] args) throws Exception {
		seriesExecMethodTest();
		
		//thenCompose-串行执行解决的问题
		thenComposeTest();
	}
	
	/**
	 * 串行方法使用案例
	 * @throws Exception
	 */
	public static void seriesExecMethodTest() throws Exception {
		CompletableFuture<Void> f1  =  CompletableFuture.runAsync(()->{
			  System.out.println("T1: 洗水壶...");				 
		});
		
		System.out.println("串行执行。。。series");
		
		//可以设置Void类型
		Function<Void, String> test = val ->{
			System.out.println("thenApply: Function interface");
			return "return_"+val;
		};
		
		CompletableFuture<?> result = null;
		
		result = f1.thenApply(test);
		result = f1.thenApply(x ->{return "11";});
		
		//没有返回结果
		result = f1.thenAccept(a -> {
			System.out.println("thenAccept: Consumer interface");
		});
		//没有返回结果
		result = f1.thenRun(() -> {
			System.out.println("thenRun: Runnable interface");
		});
		
		//返回一个新的 CompletableFuture对象
		result = f1.thenCompose(z->{
			return CompletableFuture.supplyAsync(()->{
				System.out.println("thenCompose: function, new CompletableFuture");
				return "new CompletableFuture";
			});
		});
		
		System.out.println("返回结果："+result.get());
	}
	
    public static void thenComposeTest() throws Exception {
    	/**
		 * thenApply返回 CompletableFuture<?>类型的结果
		 * thenCompose() 直接 function函数的结果，用于简化返回结果
		 */
		
		CompletableFutureTest ct = new CompletableFutureTest();

		//方式1
//		CompletableFuture<?> result = ct.getUsersDetail("xxx")
//				 .thenApply(user -> ct.getCreditRating(user));		
//		System.out.println(((CompletableFuture<?>)result.get()).get());

		//方式2
		CompletableFuture<CompletableFuture<Double>> result = ct.getUsersDetail("xxx")
				 .thenApply(user -> ct.getCreditRating(user));		
		System.out.println(((CompletableFuture<Double>)result.get()).get());
		
		//替代方案
		CompletableFuture<Double> result1 = ct.getUsersDetail("xxx")
				 .thenCompose(user -> ct.getCreditRating(user));
		System.out.println(result1.get());
    }

	private CompletableFuture<String> getUsersDetail(String userId) {
		 return CompletableFuture.supplyAsync(() ->"xx");
	 }
	 
	private CompletableFuture<Double> getCreditRating(String user) {
		 return CompletableFuture.supplyAsync(() -> 10.2);
	}

	
}
