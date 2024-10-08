import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 创建 FutureTask
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			int x = 1 + 2;
			return x;
		});
		
		// 创建线程池
		ExecutorService es = Executors.newCachedThreadPool();
		// 提交 FutureTask
		es.submit(futureTask);
		// 获取计算结果
		Integer result = futureTask.get();
		System.out.println(result);

	}

}
