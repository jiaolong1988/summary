package temp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁案例
 * @author jiaolong
 * @date 2023-06-09 03:10:46
 */
public class ReentrantLockTest {
	//true：队列已满 false:队列已空
	private boolean isCheck = true;
	//锁
	final Lock lock = new ReentrantLock();
	// 条件变量：队列不满
	final Condition notFull = lock.newCondition();
	// 条件变量：队列不空
	final Condition notEmpty = lock.newCondition();

	// 入队
	void enq() {
		String tname = Thread.currentThread().getName()+" ";
		
		System.out.println(tname+"尝试获得锁");
		lock.lock();
		System.out.println(tname+"已获取锁");		
		
		try {
			while (isCheck) {
				System.out.println(tname+"队列已满，进入条件等待队列。");
				// 等待队列不满
				notFull.await();
			}
			
			System.out.println(tname+"执行入队操作ok");
			// 省略入队操作...
			
			// 入队后, 通知可出队
			notEmpty.signal();
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	// 出队
	void deq() {
		lock.lock();
		
		String tname = Thread.currentThread().getName()+" ";
		System.out.println(tname+"出队获取锁ok");
		try {
			while (!isCheck) {		
				System.out.println(tname+"队列已空，进入条件等待队列。");				
				// 等待队列不空
			    notEmpty.await();			
			}
			
			System.out.println(tname+"执行出队操作ok");
			// 省略出队操作...
			
			// 出队后，通知可入队
			notFull.signal();
			isCheck = false;
		}catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

		
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest bq = new ReentrantLockTest();
		
		Thread t1 =	new Thread(()->{
			bq.enq();
		});
		t1.start();
		
        Thread.sleep(1000*5);
        System.out.println("\n等待5秒\n");
        
		new Thread(()->{
			bq.deq();
		}).start();
	}
}