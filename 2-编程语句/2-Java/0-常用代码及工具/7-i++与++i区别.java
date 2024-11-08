/*
 * 	【2.1】：++在前 先计算后赋值
 *  【2.2】：++在后 先计赋值后计算
 *   https://developer.aliyun.com/article/878744
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