/**
 * ThreadGroup 使用方法
 * @author jiaolong
 * @date 2023-07-20 01:36:14
 */
class MyThread extends Thread {
	// 提供指定线程名的构造器
	public MyThread(String name) {
		super(name);
	}

	// 提供指定线程名、线程组的构造器
	public MyThread(ThreadGroup group, String name) {
		super(group, name);
	}

	public void run() {
		for (int i = 0; i < 20; i++) {

			System.out.println(this.getThreadGroup().getName() + " " + getName() + " 线程的i变量" + i);
		}
	}
}

public class ThreadGroupTest {
	public static void main(String[] args) {
		// 获取主线程所在的线程组，这是所有线程默认的线程组
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("主线程组的名字：" + mainGroup.getName());
		System.out.println("主线程组是否是后台线程组：" + mainGroup.isDaemon());

		new MyThread("主线程组的线程").start();
		System.out.println("");

		ThreadGroup tg = new ThreadGroup("新线程组");
		new MyThread(tg, "tg组的线程甲").start();
		new MyThread(tg, "tg组的线程乙").start();
	}
}
