
//递归
//https://www.cnblogs.com/cainiao-chuanqi/p/11320972.html
public class Recursion {

	/*
	 关于 递归中 递进和回归的理解*/
	public static void recursion_display(int n) {
	    int temp=n;//保证前后打印的值一样
	     System.out.println("递进:" + temp);
	    if (n > 0) {
	        recursion_display(--n);
	    }
	    System.out.println("回归:" + temp);
	}
	
	
	public static int fib(int n) throws Exception {
	    if (n < 0)
	        throw new Exception("参数不能为负！");
	    else if (n == 0 || n == 1)
	        return n;
	    else
	        return fib(n - 1) + fib(n - 2);
	}
	
	public static void main(String[] args) throws Exception {
		recursion_display(6);
		//1 1 2 3 5 8 13
		System.out.println("斐波那契："+fib(6));
	}
}
