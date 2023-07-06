import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * �߳�1 ���� �߳�2�������ɡ�
 * @author jiaolong
 * @date 2023-07-04 10:10:12
 */
public class FutureTaskTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// �������� T2 �� FutureTask
		FutureTask<String> ft2 = new FutureTask<>(new T2Task());
		// �������� T1 �� FutureTask
		FutureTask<String> ft1  = new FutureTask<>(new T1Task(ft2));
		
		// �߳� T1 ִ������ ft1
		Thread T1 = new Thread(ft1);
		T1.start();
		
		// �߳� T2 ִ������ ft2
		Thread T2 = new Thread(ft2);
		T2.start();
		
		// �ȴ��߳� T1 ִ�н��
		System.out.println(ft1.get());
	}
}

/**
 * 
 * T1Task ��Ҫִ�е�����ϴˮ�����տ�ˮ���ݲ�
 * @author jiaolong
 * @date 2023-07-04 10:09:18
 */
class T1Task implements Callable<String> {
	FutureTask<String> ft2;

	// T1 ������Ҫ T2 ����� FutureTask
	T1Task(FutureTask<String> ft2) {
		this.ft2 = ft2;
	}

	@Override
	public String call() throws Exception {
		System.out.println("T1: ϴˮ��...");
		TimeUnit.SECONDS.sleep(1);

		System.out.println("T1: �տ�ˮ...");
		TimeUnit.SECONDS.sleep(15);
		
		//��ȡ T2 �̵߳Ĳ�Ҷ--�ص�
		String tf = ft2.get();
		System.out.println("T1: �õ���Ҷ:" + tf);

		System.out.println("T1: �ݲ�...");
		return " �ϲ�:" + tf;
	}
}

/**
 * T2Task ��Ҫִ�е�����:ϴ�����ϴ�豭���ò�Ҷ
 * @author jiaolong
 * @date 2023-07-04 10:09:43
 */
class T2Task implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("T2: ϴ���...");
		TimeUnit.SECONDS.sleep(1);

		System.out.println("T2: ϴ�豭...");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("T2: �ò�Ҷ...");
		TimeUnit.SECONDS.sleep(1);
		return " ���� ";
	}
}