package pool.cs;

import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * �������ͱ��۲�����
 * @author jiaolong
 * @date 2023-07-10 02:36:57
 */
public class CompletionServiceTest5 {

	public static void main(String[] args) throws Exception {
		// �����̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		// ���� CompletionService
		CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
		// �첽����� S1 ѯ��
		cs.submit(() -> getPriceByS1());
		// �첽����� S2 ѯ��
		cs.submit(() -> getPriceByS2());
		// �첽����� S3 ѯ��
		cs.submit(() -> getPriceByS3());
		
		// ��ѯ�۽���첽���浽���ݿ�
		// ��������ͱ���
		AtomicReference<Integer> m = new AtomicReference<>(Integer.MAX_VALUE);
		CountDownLatch latch = new CountDownLatch(3);
		
		for (int i = 0; i < 3; i++) {
			executor.execute(() -> {
				Integer r = null;
				try {
					r = cs.take().get();
				} catch (Exception e) {
				}
				save(r);
				m.set(Integer.min(m.get(), r));
				//����ʱ����
				latch.countDown();
			});
		}
		//�ȴ�ִ�����
		latch.await();
		
		// ���ؽ��
		System.out.println("���ؽ����" + m);
	    executor.shutdown();

	}

	public static int getPriceByS1() {
		sleep(10);
		return 1;
	}

	public static int getPriceByS2() {
		sleep(15);
		return 2;
	}

	public static int getPriceByS3() {
		sleep(5);
		return 3;
	}

	public static void save(int r) {
		System.out.println("save " + r);
	}

	public static void sleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// �˴��ж��쳣����
		}
	}
}
