import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * �򻯵��̳߳أ�����˵������ԭ��
 * 
 * 
 * @author jiaolong
 * @date 2023-06-29 01:54:59
 */
public class MyThreadPool{
  // ������������ʵ�������� - ������ģʽ
  BlockingQueue<Runnable> workQueue;
  // �����ڲ������߳�
  List<WorkerThread> threads = new ArrayList<>();
  
  // ���췽��
  MyThreadPool(int poolSize,  BlockingQueue<Runnable> workQueue){
    this.workQueue = workQueue;
    
    // ���������߳�
    for(int idx=0; idx<poolSize; idx++){
      WorkerThread work = new WorkerThread();
      work.start();
      threads.add(work);
    }
  }
  
  // �ύ����
  void execute(Runnable command) throws InterruptedException{
    workQueue.put(command);
  }
  
  // �����̸߳����������񣬲�ִ������
  class WorkerThread extends Thread{
    public void run() {
      // ѭ��ȡ����ִ��
      while(true){ //��
        try {
			Runnable task = workQueue.take();
			task.run();
		} catch (InterruptedException e) {
			this.interrupt();
			e.printStackTrace();
		}
      } 
    }
  }  
  
  
  public static void main(String[] args) throws InterruptedException {
	// �����н���������
	BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
	// �����̳߳�  
	MyThreadPool pool = new MyThreadPool(10, workQueue);
	
	// �ύ����  
	pool.execute(()->{
	    System.out.println("hello1");
	});
	
	System.out.println("main end...");
  }
  
 
  /**
   *  ʵ������
   *  interrupt();
   *  
   *  ��̬����
   *  interrupted()
   */
 
	
}
 
