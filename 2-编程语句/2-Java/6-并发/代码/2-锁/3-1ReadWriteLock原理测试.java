import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ��д��ԭ�����
	1.�������߳�ͬʱ�����������
	2.ֻ����һ���߳�д���������
	3.���һ��д�߳�����ִ��д��������ʱ��ֹ���̶߳����������
 * @author jiaolong
 * @date 2023-06-13 02:15:39
 * @param <K>
 * @param <V>
 */
class Cache<K, V> {
	final Map<K, V> m = new HashMap<>();
	final ReadWriteLock rwl = new ReentrantReadWriteLock();
	// ����
	final Lock r = rwl.readLock();
	// д��
	final Lock w = rwl.writeLock();

	// ������
	V get(K key) {
		r.lock();
		try {
			return m.get(key);
		} finally {
			r.unlock();
		}
	}

	// д����
	V put(K key, V v) {
		w.lock();
		try {
			V x = m.put(key, v);
            //д����ͣ5�룬����������������ȴ�д��ִ����Ϻ󣬶����ſɶ�ȡ���ݡ�
			writeSleep(5);	
			
			return x;
		} finally {
			w.unlock();
		}
	}
	
	public void writeSleep(int val) {
		try {
			Thread.sleep(1000*val);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Cache<String,String> cache = new Cache<>();
		
		List<String> list = new ArrayList<>();
		for(int i=0;i<2;i++) {
			//�߳�ʼ�ն�ȡ��������
			new Thread(()->{
				while(true) {
					String info = Thread.currentThread().getName()+"-"+cache.get("A")+Calendar.getInstance().getTime();
					list.add(info);
					try {
						Files.write(Paths.get("writeInfo.txt"), list, Charset.forName("gbk"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(info);
				}
				
			}).start();
		}

		new Thread(()->{
		    int i=1;
			while(true) {
				cache.put("A", "hello"+i);
				cache.writeSleep(5);	
				i++;
			}		
		}).start();
	}
}
