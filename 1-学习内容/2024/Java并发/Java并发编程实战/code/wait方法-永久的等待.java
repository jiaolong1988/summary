
public class Test {
	synchronized void apply() {
		// ����д��
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
		//����wait�����󣬳������Զ�ĵȴ������� notify����֪ͨ
		Test t = new Test();
		t.apply();
		System.out.println("3333");
	}

}
