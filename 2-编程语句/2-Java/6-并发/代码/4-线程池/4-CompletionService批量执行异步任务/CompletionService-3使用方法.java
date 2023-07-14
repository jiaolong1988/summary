package pool.cs;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletionService ʹ�÷���
 * @author jiaolong
 * @date 2023-07-10 02:39:58
 */
public class CompletionServiceTest3 {

	public static void main(String[] args) throws Exception {

		// �����̳߳�
		ExecutorService executor =  Executors.newFixedThreadPool(3);
		// ���� CompletionService
		CompletionService<Integer> cs = new  ExecutorCompletionService<>(executor);
		
		// �첽����� S1 ѯ��
		cs.submit(()->getPriceByS1());
		// �첽����� S2 ѯ��
		cs.submit(()->getPriceByS2());
		// �첽����� S3 ѯ��
		cs.submit(()->getPriceByS3());
		
		// ��ѯ�۽���첽���浽���ݿ�
		for (int i=0; i<3; i++) {
		  Integer r = cs.take().get();
		  executor.execute(()->save(r));
		}
		
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
