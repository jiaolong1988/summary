/**
 * ThreadGroup ʹ�÷���
 * @author jiaolong
 * @date 2023-07-20 01:36:14
 */
class MyThread extends Thread {
	// �ṩָ���߳����Ĺ�����
	public MyThread(String name) {
		super(name);
	}

	// �ṩָ���߳������߳���Ĺ�����
	public MyThread(ThreadGroup group, String name) {
		super(group, name);
	}

	public void run() {
		for (int i = 0; i < 20; i++) {

			System.out.println(this.getThreadGroup().getName() + " " + getName() + " �̵߳�i����" + i);
		}
	}
}

public class ThreadGroupTest {
	public static void main(String[] args) {
		// ��ȡ���߳����ڵ��߳��飬���������߳�Ĭ�ϵ��߳���
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("���߳�������֣�" + mainGroup.getName());
		System.out.println("���߳����Ƿ��Ǻ�̨�߳��飺" + mainGroup.isDaemon());

		new MyThread("���߳�����߳�").start();
		System.out.println("");

		ThreadGroup tg = new ThreadGroup("���߳���");
		new MyThread(tg, "tg����̼߳�").start();
		new MyThread(tg, "tg����߳���").start();
	}
}
