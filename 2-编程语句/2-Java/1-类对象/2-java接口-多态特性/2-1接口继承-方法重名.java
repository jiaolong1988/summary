interface AA{
	void xx();
}

interface BB extends AA {

	/**
	 * CC�ӿ� �� AA�ӿڵķ�������һ��.
	 * 	��д aa�ӿڵ�xx����
	 */
	default void xx() {
		System.out.println("�ӿ�ʵ��xx");
	}
}

interface CC extends AA {
	/**
	 * CC�ӿ� �� AA�ӿڵķ�������һ�£�����д��
	 * 	1.Ҳ����ʡ�ԣ���ʵ����ȥʵ�֡������д����������ʾ����ӿڸ�����û�д��ڵı�Ҫ��
	 *  2.��ʡ�ԣ�Ҳ�����븸�ӿ�������˵���ýӿ��븸�ӿڵ�ʵ���ǲ�һ�£���ʵ�ָýӿڵ���ȥʵ�־����߼���
	 */
	
	void xx();
}


public class TestInterface {

	public static void main(String[] args) {

		//���ӽӿ��� ��д ���ӿ�ʵ��
		BB b = new BB(){};
		b.xx();
		
		//ʵ���� ʵ�� �ӿڷ�����
		CC c = new CC(){
			@Override
			public void xx() {	
				System.out.println("ʵ���� ʵ��cc");
			}
		};
		c.xx();
	}

}
