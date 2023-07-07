package pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Future �ӿ��е� cancel���� �� get����������һ��
 * 	cancel���� ������ get���� ֮ǰִ�С�
 * @author jiaolong
 * @date 2023-07-07 02:24:22
 */
public class FutureTaskThreadCancelTest {

	//�ڲ���-״̬����
	static class ControlStop{
		private Future<String> future;
		private volatile boolean isStop ;

		ControlStop(Future<String> future, boolean isStop) {
			this.future = future;
			this.isStop = isStop;
		}
	}
   
	//�ڲ���-�����̵߳�ȡ��
	static class TaskCancel implements Runnable{
		private ControlStop cs;
		private TaskCancel(ControlStop cs){
			this.cs = cs;
		}

		@Override
		public void run() {
			Future<String> future = cs.future;

			//������ִ�� cancel����
			while(!future.isDone()) {
				System.out.println("���߳�����û�н���...");
				if(cs.isStop){
					future.cancel(true);
				}
			}

			//��ִ��get����
			if (!future.isCancelled()){
				System.out.println("���߳����������");
				try {
					String result = future.get();
					System.out.println("���߳����������-�����"+result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("���߳�����ȡ��");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//1.���̳߳� �ύ����
		Callable<String> callable = () -> {
			System.out.println("���� Callable �� call ����");

			Thread.sleep(500000);
			return "Hello from Callable";
		};
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
						
		//��ʽ1
		//FutureTask<String> future = way1(callable, executorService);		
		//��ʽ2
		Future<String> future = executorService.submit(callable);

		//2.����һ���е��߳� �����������
		TaskCancel tc = new TaskCancel(new ControlStop(future, false));
		new Thread(tc).start();

		//3.�����Ӻ󣬴������ ֹͣ����
		TimeUnit.SECONDS.sleep(5);
		tc.cs.isStop=true;

		executorService.shutdown();
	}
	
	public static FutureTask<String> way1(Callable<String> callable, ExecutorService executorService) {
		FutureTask<String> futureTask = new FutureTask<>(callable);		
		executorService.submit(futureTask);
		
		return futureTask;
	}
	
	
}
