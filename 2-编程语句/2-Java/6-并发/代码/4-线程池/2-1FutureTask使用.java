import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// ���� FutureTask
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			int x = 1 + 2;
			return x;
		});
		
		// �����̳߳�
		ExecutorService es = Executors.newCachedThreadPool();
		// �ύ FutureTask
		es.submit(futureTask);
		// ��ȡ������
		Integer result = futureTask.get();
		System.out.println(result);

	}

}
