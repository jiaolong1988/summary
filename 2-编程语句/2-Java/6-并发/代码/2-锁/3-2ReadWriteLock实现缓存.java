import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ʹ�ö�д��ʵ�ֻ���
 * @author jiaolong
 * @date 2023-06-13 04:31:58
 * @param <K>
 * @param <V>
 */
class Cache<K, V> {
	// ��������
	final Map<K, V> m = new HashMap<>();

	final ReadWriteLock rwl = new ReentrantReadWriteLock();
	final Lock r = rwl.readLock();
	final Lock w = rwl.writeLock();

	/**
	 * ��д��ʵ�ֻ��湦��
	 * @param key
	 * @return
	 */
	V get(K key) {
		V v = null;
		
		// ������
		r.lock();
		try {
			v = m.get(key);
		} finally {
			r.unlock();
		}
				
		// �����д��ڣ�����
		if (v != null) { 
			return v;
		}
		
		// �����в����ڣ���ѯ���ݿ⣬�򻺴� д����
		w.lock();
		try {
			// �ٴ���֤
			// �����߳̿����Ѿ���ѯ�����ݿ�
			v = m.get(key); 
			if (v == null) { 		
				// ��ѯ���ݿ�
				// v= ʡ�Դ�������
				m.put(key, v);
			}
		} finally {
			w.unlock();
		}
		return v;
	}
}