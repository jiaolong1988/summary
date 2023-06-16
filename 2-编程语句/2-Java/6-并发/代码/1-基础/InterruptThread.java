package temp;

/**
 * 当线程正在执行任务时，通过interrupt方式 停止线程，使其退出
 * @author jiaolong
 * @date 2023-05-22 03:38:06
 */
public class InterruptThread extends Thread {

	@Override
	public void run() {		
		while (true) {
			if (isInterrupted()) {
				System.out.println("收到中断通知，推出程序。");
				break;
			}
			//出发InterruptedException
			try {
				/**
				 * 当其他线程通过调用th.interrupt() 来中断 th 线程时，大概率地会触发 InterruptedException 异常，
				 * 在触发 InterruptedException 异常的同时，JVM 会同时把线程的中断标志位清除，所以这个时候
				 *  th.isInterrupted()返回的是 false。
				 */
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				//重新设置中断标志位
			    interrupt();
			}
			
			System.out.println("正在执行任务。");
			
		}
	}

	public static void main(String[] args) {

		Thread t = new InterruptThread();
		t.start();
		
		//模拟中断效果
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		t.interrupt();
	}

}
