package pool;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ���󴴽�
 * @author jiaolong
 * @date 2023-07-04 05:31:02
 */
public class CompletableFutureCreate {

	public static void main(String[] args) {
		// ���� 1��ϴˮ�� -> �տ�ˮ
		CompletableFuture<Void> f1 =  CompletableFuture.runAsync(()->{
		  System.out.println("T1: ϴˮ��...");
		  sleep(1, TimeUnit.SECONDS);
		 
		  System.out.println("T1: �տ�ˮ...");
		  sleep(15, TimeUnit.SECONDS);
		});
		
		// ���� 2��ϴ��� -> ϴ�豭 -> �ò�Ҷ
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
		  System.out.println("T2: ϴ���...");
		  sleep(1, TimeUnit.SECONDS);
		 
		  System.out.println("T2: ϴ�豭...");
		  sleep(2, TimeUnit.SECONDS);
		 
		  System.out.println("T2: �ò�Ҷ...");
		  sleep(1, TimeUnit.SECONDS);
		  return " ���� ";
		});			 
	
	}

	public static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
		}
	}
}
