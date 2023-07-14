package pool.cs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ����
 * 		�������߳��첽ִ��ѯ�ۣ�ͨ�����ε��� Future �� get() ������ȡѯ�۽����֮��ѯ�۽�����������ݿ���.
 * ע�⣺
 * 		�Ǿ��������ȡ���� S1 ���۵ĺ�ʱ�ܳ�����ô�����ȡ���� S2 ���۵ĺ�ʱ�̣ܶ�Ҳ�޷��ñ��� S2 ���۵Ĳ�����ִ�У���Ϊ������̶߳��������� f1.get() �����ϡ�
 * @author jiaolong
 * @date 2023-07-10 02:40:45
 */
public class CompletionServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// �����̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// �첽����� S1 ѯ��
		Future<Integer> f1 = executor.submit(() -> getPriceByS1());
		// �첽����� S2 ѯ��
		Future<Integer> f2 = executor.submit(() -> getPriceByS2());
		// �첽����� S3 ѯ��
		Future<Integer> f3 = executor.submit(() -> getPriceByS3());
		
		// ��ȡ���� S1 ���۲�����
		Integer r1 = f1.get();
		executor.execute(() -> save(r1));

		// ��ȡ���� S2 ���۲�����
		Integer r2 = f2.get();
		executor.execute(() -> save(r2));

		// ��ȡ���� S3 ���۲�����
		Integer r3 = f3.get();
		executor.execute(() -> save(r3));
	}

	public static int getPriceByS1() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public static int getPriceByS2() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 2;
	}
	public static int getPriceByS3() {
		return 3;
	}
	
	public static void save(int r) {
		System.out.println("save "+ r);
	}
}
