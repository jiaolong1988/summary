/*
 * 1.在不赋值的情况下，++i与i++的 i值是一样的，没有区别
 * 2.在赋值的情况下，x = ++i; 与 x = i++;的区别为：
 * 	【2.1】：++在前 先计算后赋值
 *  【2.2】：++在后 先计赋值后计算
 */
public class TestTemp {
		public static void main(String[] args) {
		
			int i=0,j=0;			
			int x=0,y=0;
			
			x = ++i;
			y = j++;
			
			System.out.println("x= ++i : "+x);						
			System.out.println("y= j++ : "+y);
			
			System.out.println();			
			System.out.println("++i   :"+i);
			System.out.println("j++   :"+j);
			
			
			/* 打印结果
				x= ++i : 1
				y= j++ : 0

				++i   :1
				j++   :1
			*/
		}
}