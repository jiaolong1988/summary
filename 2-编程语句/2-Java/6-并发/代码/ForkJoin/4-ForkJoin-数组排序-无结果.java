package pool.ForkJoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * ������������
 * @author jiaolong
 * @date 2023-07-11 10:39:49
 */
public class RecursiveActionTest {

	public static void main(String[] args) {
		long[] array = {1,5,4,3,2,
						1,5,4,3,2,
						2,1,5,4,3};
		// ���� ForkJoin �̳߳�
		ForkJoinPool fjp = new ForkJoinPool(3);
		// ��������
		SortTask st = new SortTask(array);
		// ��������
		fjp.invoke(st);
		// ������
		
		System.out.print("��������");
		for(long x:array) 
			System.out.print(x+" ");
	

	}

	static class SortTask extends RecursiveAction {
		private static final long serialVersionUID = 7597257084809473062L;
		
		// implementation details follow
		static final int THRESHOLD = 10;
		
		final long[] array;
		final int lo, hi;

		SortTask(long[] array, int lo, int hi) {
			this.array = array;
			this.lo = lo;
			this.hi = hi;
		}

		SortTask(long[] array) {
			this(array, 0, array.length);
		}

		protected void compute() {
			if (hi - lo < THRESHOLD)
				sortSequentially(lo, hi);
			else {
				int mid = (lo + hi) >>> 1;
				invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
				merge(lo, mid, hi);
			}
		}

		void sortSequentially(int lo, int hi) {
			System.out.println("lo:"+lo+" hi:"+hi);
			Arrays.sort(array, lo, hi);
		}
        
		/**
		 * ����������������ݽ��кϲ�
		 * �������ݴ�0λ�ÿ�ʼ�Ƚϣ�����С������ǰ��
		 * @param lo
		 * @param mid
		 * @param hi
		 */
		void merge(int lo, int mid, int hi) {
			
			long[] buf = Arrays.copyOfRange(array, lo, mid);
			System.out.println("lo:"+lo+" mid:"+mid+" hi:"+hi+" bufLen:"+buf.length+"\n");
			
			print(buf,"buf");
			print(array,"array");
			System.out.println();
			
			for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
			//��д1	
			//	array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];

			//д��2	
				System.out.println("i="+i+" j="+j+" k="+k);
						
				long x = 0L;
				if(k == hi || buf[i] < array[k]) {
					x = buf[i++];
					System.out.println("buf[i++]");
					
				}else {
					x = array[k++];
					System.out.println("array[k++]");
				}

				array[j] =x;
				System.out.println("array["+j+"]=" +x+"\n");
			}
				
		}
	
		void print(long[] arr, String name) {
			System.out.print(name+" -> ");
			for(long l: arr) {
				System.out.print(l+" ");
			}
			System.out.println();
		}
	}
}
