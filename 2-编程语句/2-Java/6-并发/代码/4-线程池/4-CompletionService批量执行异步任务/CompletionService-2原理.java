package pool.cs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * CompletionService ԭ��
 * @author jiaolong
 * @date 2023-07-10 02:40:16
 */
public class CompletionServiceTest2 {

	public static void main(String[] args) throws Exception {
		
		// ������������
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
		
		// �����̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// �첽����� S1 ѯ��
		Future<Integer> f1 = executor.submit(() -> getPriceByS1());
		// �첽����� S2 ѯ��
		Future<Integer> f2 = executor.submit(() -> getPriceByS2());
		// �첽����� S3 ѯ��
		Future<Integer> f3 = executor.submit(() -> getPriceByS3());
		
		// ���� S1 �����첽������������
		executor.execute(() -> {
			try {
				bq.put(f1.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// ���� S2 �����첽������������
		executor.execute(() -> {
			try {
				bq.put(f2.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		// ���� S3 �����첽������������
		executor.execute(() -> {
			try {
				bq.put(f3.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		// �첽�������б���
		for (int i = 0; i < 3; i++) {
			Integer r = bq.take();
			executor.execute(() -> save(r));
		}
		
		executor.shutdown();
	}

	public static int getPriceByS1() {
		sleep(30);
		return 1;
	}
	public static int getPriceByS2() {
		sleep(30);
		return 2;
	}
	public static int getPriceByS3() {
		sleep(5);
		return 3;
	}
	public static void save(int r) {
		System.out.println("save "+r);
	}
	public static void sleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
