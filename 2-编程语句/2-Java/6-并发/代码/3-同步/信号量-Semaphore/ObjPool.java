package temp;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

//快速实现一个限流器
//16 | Semaphore：如何快速实现一个限流器？-王宝令 java并发编程
class ObjPool<T, R> {
	final List<T> pool;
	// 用信号量实现限流器
	final Semaphore sem;

	// 构造函数
	ObjPool(int size, T t) {
		pool = new Vector<T>() {
		};
		for (int i = 0; i < size; i++) {
			pool.add(t);
		}
		sem = new Semaphore(size);
	}

	// 利用对象池的对象，调用 func
	R exec(Function<T, R> func) throws InterruptedException {
		T t = null;
		sem.acquire();
		try {
			t = pool.remove(0);
			return func.apply(t);
		} finally {
			pool.add(t);
			sem.release();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// 创建对象池
		ObjPool<Long, String> pool = new ObjPool<Long, String>(10, 2L);
		
	
		// 通过对象池获取 t，之后执行  
		String x = pool.exec(t -> {
		    System.out.println("对象池 执行业务逻辑"+t);
		    return t.toString();
		});
		
		System.out.println("返回结果："+x);
	}
}
