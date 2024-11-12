/*
 *	1.在不赋值的情况下，++i与i++的 i值是一样的，没有区别.
 *	2.在赋值的情况下，x = ++i; 与 x = i++;的区别为：
 * 		【2.1】：++在前 ,赋值为自增 【后】 的值
 *  	【2.2】：++在后 ,赋值为自增 【前】 的值
 *
 *  https://developer.aliyun.com/article/878744
 */
public class TestTemp {
		public static void main(String[] args) {
			
      /*  i++: 先自增，然后返回 自增之前的值
          ++i: 先自增，然后返回 自增之后的值
         本质上都是执行了 i = i + 1 操作，只是返回值不同而已
      */
        
        int a=1, b=1;
        System.out.println("a:"+ ++a);  //2
        System.out.println("a:"+ a);    //2
        System.out.println("b:"+ b++);  //1
        System.out.println("b:"+ b);    //2

        System.out.println("================");

        int i = 0;
        int j = 0;
        i = i++;  //等同于 i=i
        System.out.println("i="+i);
        j = i++;
        System.out.println("i = " + i + ", j = " + j);
		
		}
}