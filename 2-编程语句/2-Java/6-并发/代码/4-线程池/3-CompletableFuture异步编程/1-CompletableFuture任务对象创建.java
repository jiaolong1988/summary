package pool;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 对象创建
 * @author jiaolong
 * @date 2023-07-04 05:31:02
 */
public class CompletableFutureCreate {

	public static void main(String[] args) {
		// 任务 1：洗水壶 -> 烧开水
		CompletableFuture<Void> f1 =  CompletableFuture.runAsync(()->{
		  System.out.println("T1: 洗水壶...");
		  sleep(1, TimeUnit.SECONDS);
		 
		  System.out.println("T1: 烧开水...");
		  sleep(15, TimeUnit.SECONDS);
		});
		
		// 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
		  System.out.println("T2: 洗茶壶...");
		  sleep(1, TimeUnit.SECONDS);
		 
		  System.out.println("T2: 洗茶杯...");
		  sleep(2, TimeUnit.SECONDS);
		 
		  System.out.println("T2: 拿茶叶...");
		  sleep(1, TimeUnit.SECONDS);
		  return " 龙井 ";
		});			 
	
	}

	public static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
		}
	}
}
