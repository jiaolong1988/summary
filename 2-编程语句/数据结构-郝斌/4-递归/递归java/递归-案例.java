
//�ݹ�
//https://www.cnblogs.com/cainiao-chuanqi/p/11320972.html
public class Recursion {

	/*
	 ���� �ݹ��� �ݽ��ͻع�����*/
	public static void recursion_display(int n) {
	    int temp=n;//��֤ǰ���ӡ��ֵһ��
	     System.out.println("�ݽ�:" + temp);
	    if (n > 0) {
	        recursion_display(--n);
	    }
	    System.out.println("�ع�:" + temp);
	}
	
	
	public static int fib(int n) throws Exception {
	    if (n < 0)
	        throw new Exception("��������Ϊ����");
	    else if (n == 0 || n == 1)
	        return n;
	    else
	        return fib(n - 1) + fib(n - 2);
	}
	
	public static void main(String[] args) throws Exception {
		recursion_display(6);
		//1 1 2 3 5 8 13
		System.out.println("쳲�������"+fib(6));
	}
}
