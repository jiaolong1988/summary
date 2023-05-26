package com.founder;


/**
 * 线程状态测试
 *
 * @author fraser
 * @date 2020/3/22 9:15 PM
 */

public class ThreadStateTest2 {

	public static void main(String[] args) throws InterruptedException {

//		//1.线程RUNNABLE
//		Thread thread = new Thread(() -> {});
//		thread.start();
//		//Thread.sleep(1000);//TERMINATED
//		System.out.println(thread.getState());

//		//2.BLOCKED
//		Thread t1 = new Thread(new DemoThreadB());
//		Thread t2 = new Thread(new DemoThreadB());
//		t1.start();
//		t2.start();
//		Thread.sleep(1000);
//		System.out.println((t2.getState()));
//		System.exit(0);
		
		
//		//3.WAITING-无时间参数等待
//		Thread main = Thread.currentThread();
//
//		Thread thread2 = new Thread(() -> {
//			try {
//				Thread.sleep(1000*10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(main.getState());
//		});
//		thread2.start();
//		thread2.join();

		
//		//4.TIMED_WAITING
//		Thread thread3 = new Thread(() -> {
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//			//	Thread.currentThread().interrupt();
//				e.printStackTrace();
//			}
//		});
//		thread3.start();
//
//		Thread.sleep(1000);
//		System.out.println(thread3.getState());
				
	}
}




class DemoThreadB implements Runnable {
	@Override
	public void run() {
		commonResource();
	}

	public static synchronized void commonResource() {
		while(true) {

		}
	}
}
