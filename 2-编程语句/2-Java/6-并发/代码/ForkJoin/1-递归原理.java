package pool.ForkJoin;

/**
 * �ݹ鰸������ϰ
 * @author jiaolong
 * @date 2023-07-10 05:50:23
 */
public class RecursiveLearn {

	public static void main(String[] args) {
	
		System.out.println("�ݹ�ʵ�ַ�ʽ1����ͽ����"+sum(4));
		System.out.println("�ݹ�ʵ�ַ�ʽ2����ͽ����"+a(4));
	}
	
	/**
	 * �ݹ飬ǰn�����
	 * @param n
	 * @return
	 */
	public static int sum(int n) {
		if(n == 1) { 
			//�ݹ����
			return 1;
			
		}else {
			//���õݹ�
			
			//n-1
			int j = n - 1;
			
			//n ���� n-1��ĺ�
			return n + sum(j);
		}
		
	}
	
	/**
	 * ���� 1+2+3+4�ĺ�
	 * 	�ݹ�ֽⲽ�裬a��b��c��d �����Ĳ��裬�޷������ֲ�һ�����ݹ� �޷��� ����Щ���� ���й��ɻ���
	 * @param x
	 * @return
	 */	
	public  static int a(int x) {
		//4+(3+2+1) ��˼�� ��4���ֵ ���� ǰ����ĺ�
		int res = x + b(x - 1);
		return res;
	}

	private static int b(int x) {
		//3+(2+1)
		int res = x + c(x - 1);
		return res;
	}

	private static int c(int x) {
		//2+(1)
		int res = x + d (x - 1);
		return res;
	}
	
	private static int d(int i) {
		//i=1
		return 1;
	}

}
