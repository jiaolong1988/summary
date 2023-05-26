
public class Test {
	synchronized void apply() {
		// 经典写法
		while (true) {
			try {
				System.out.println("1111");
				wait();
				
				System.out.println("2222");
			} catch (Exception e) {
			}
		}

	}
	 
	public static void main(String[] args) {
		//调用wait方法后，程序会永远的等待，除非 notify进行通知
		Test t = new Test();
		t.apply();
		System.out.println("3333");
	}

}
