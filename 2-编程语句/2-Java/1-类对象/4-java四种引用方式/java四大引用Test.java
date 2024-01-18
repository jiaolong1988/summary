package test.tmp;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TmpTest {

	public static void main(String[] args) {

	//	strongReference();
	//  softReference();
		weakReference();
	//	phantomReference();
		
	}

	// 强引用
	public static void strongReference() {
		MyObject myObject = new MyObject();// 强引用，未置空无法被GC
		System.out.println("Gc前：" + myObject);

		System.gc();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("未置空Gc：" + myObject);

		myObject = null; // 置空可被GC
		System.gc();
		System.out.println("置空后Gc：" + myObject);
	}

	// 软引用
	public static void softReference() {
		SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());// 软引用
		MyObject mobj =  softReference.get();		
		System.out.println(mobj.info);
		
		// 内存够用
		System.out.println("Gc前：" + softReference.get());
		System.gc();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Gc后内存够用：" + softReference.get());

		// 设置参数-Xms10m -Xmx10m , 内存不够用
		try {
			byte[] bytes = new byte[9 * 1024 * 1024];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Gc后内存不够用：" + softReference.get());
		}
	}
	
    //弱引用
    public static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());//弱引用
        //内存够用
        System.out.println("Gc前：" + weakReference.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Gc后：" + weakReference.get());
    }
    
    
   //虚拟引用
    public static void phantomReference(){
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(new MyObject(), referenceQueue);
        PhantomReference<MyObject> phantomReference1 = new PhantomReference<>(new MyObject(), referenceQueue);
   
        
        System.out.println("init:"+phantomReference.get());
 
       
        List<byte[]> list = new ArrayList<>();
		new Thread(() -> {
			while (true) {
				
				//1KB=1024byte 1byte=8bit byte就是B,表示1MB
				list.add(new byte[1 * 1024 * 1024]);
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1:"+phantomReference.get());
			}
		}, "t1").start();
 
		new Thread(() -> {
			while (true) {
				Reference<? extends MyObject> reference = referenceQueue.poll();
				if (reference != null) {
					System.out.println("t2:***********有虚对象加入队列了"+reference.get());
				}
			}
		}, "t2").start();
 
        //暂停几秒钟线程
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}

class MyObject {
	public String info="jiaolong";
}
