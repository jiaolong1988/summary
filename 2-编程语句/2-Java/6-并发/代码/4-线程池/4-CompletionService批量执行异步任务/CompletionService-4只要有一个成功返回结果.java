package pool.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ���еص��ö����ѯ����ֻҪ��һ���ɹ����ؽ������������Ϳ��Է���.
 * @author jiaolong
 * @date 2023-07-10 02:38:20
 */
public class CompletionServiceTest4 {

	public static void main(String[] args) throws Exception {

		// �����̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// ���� CompletionService
		CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
		
		// ���ڱ��� Future ����
		List<Future<Integer>> futures = new ArrayList<>(3);
		// �ύ�첽���񣬲����� future �� futures
		futures.add(cs.submit(() -> getPriceByS1()));
		futures.add(cs.submit(() -> getPriceByS2()));
		futures.add(cs.submit(() -> getPriceByS3()));
		
		// ��ȡ��췵�ص�����ִ�н��
		Integer r = 0;
		try {
			// ֻҪ��һ���ɹ����أ��� break
			for (int i = 0; i < 3; ++i) {
				r = cs.take().get();
				// �򵥵�ͨ���п�������Ƿ�ɹ�����
				if (r != null) {
					break;
				}
			}
		} finally {
			// ȡ����������
			for (Future<Integer> f : futures)
				f.cancel(true);
		}
		
		
		// ���ؽ��
		System.out.println("���ؽ����"+r);
		executor.shutdown();

	}

	public static int getPriceByS1() {
		sleep(30);
		System.out.println("getPriceByS1: 1");
		sleep(5);
		System.out.println("getPriceByS1: 1-2");
		return 1;
	}

	public static int getPriceByS2() {
		sleep(30);
		System.out.println("getPriceByS2: 2");
		return 2;
	}

	public static int getPriceByS3() {
		sleep(5);
		System.out.println("getPriceByS3: 3");
		return 3;
	}

	public static void save(int r) {
		System.out.println("save " + r);
	}

	public static void sleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			//�˴��ж��쳣����
		}
	}
}
