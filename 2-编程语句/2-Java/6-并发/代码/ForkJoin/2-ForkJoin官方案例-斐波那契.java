package pool.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 쳲���������
 *  	1��1��2��3��5��8��13��21��34��55
 *  ���㷽��
 * 		�ӵ�3�ʼ��ÿһ�����ǰ����֮�͡�
 *      �ȵ�һ��ڶ��Ϊ1
 *   
 * @author jiaolong
 * @date 2023-07-10 03:57:23
 */
public class ForkJoinTest {

	public static void main(String[] args) {
		// �������������̳߳�
		ForkJoinPool fjp = new ForkJoinPool(4);
		// ������������
		Fibonacci fib = new Fibonacci(2);
		// ������������
		Integer result = fjp.invoke(fib);
		
		// ������
		System.out.println(result);
	}

	// �ݹ�����
	static class Fibonacci extends RecursiveTask<Integer> {
		private static final long serialVersionUID = -4199481511415415519L;
		
		final int n;

		Fibonacci(int n) {
			this.n = n;
		}

		protected Integer compute() {
			System.out.println("==="+n);
			if (n <= 1)
				return n;
			Fibonacci f1 = new Fibonacci(n - 1);
			// ����������
			f1.fork();
			
			Fibonacci f2 = new Fibonacci(n - 2);
			// �ȴ��������������ϲ����
			return f2.compute() + f1.join();
		}
	}
}
