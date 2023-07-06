package pool;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * AND��ϵ
 * @author jiaolong
 * @date 2023-07-04 05:31:02
 */
public class CompletableFutureAndTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		// ���� 3������ 1 ������ 2 ��ɺ�ִ�У��ݲ�
		CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf)->{
		    System.out.println("T1: �õ���Ҷ:" + tf);
		    System.out.println("T1: �ݲ�...");
		    return " �ϲ�:" + tf;
		  });
		// �ȴ����� 3 ִ�н��
		System.out.println(f3.join());
		 
	
	}

	public static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
		}
	}
}
