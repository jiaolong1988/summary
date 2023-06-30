package temp;

/**
 * 当线程正在执行任务时，通过interrupt方式 停止线程，使其退出
 
 对象方法	
	interrupt()			：中断线程，当遇到 sleep,wait, join方式时，会抛中断异常，							  
						  会清除中断状态
	isInterrupted()		：返回线程是否中断，不会清除中断状态。
	注意：interrupt()、isInterrupted()是成对使用的

 类方法（不需要new对象直接使用）
	interrupted()		：返回线程是否中断，会清除中断状态。
	
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
