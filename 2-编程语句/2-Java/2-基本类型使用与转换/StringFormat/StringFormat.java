package com.founder.util;

/**
 * java String类中formatter给我们的好处
 * 语法：https://zxhtom.github.io/zxh/20180105.html
 * 案例：https://blog.csdn.net/qq_41071876/article/details/106217212
 * @ClassName:  StringFormat   
 * @Description:TODO(描述这个类的作用)   
 * @author: jiaolong
 * @date:   2022年4月19日 下午2:26:58      
 * @Copyright:
 */
public class StringFormat {

	public static void main(String[] args) {
		/**
		 * %[argument_index$][flags][width]conversion
		    [argument_index$] ，位置索引从1开始计算，用于指定对索引相应的实参进行格式化并替换掉该占位符。
			[flags] ，用于增强格式化能力，可同时使用多个 [flags] ，但某些标识是不能同时使用的。		
			[width] ，用于设置格式化后的字符串最小长度，若使用 [最小宽度] 而无设置 [标识] ，那么当字符串长度小于最小宽度时，则以左边补空格的方式凑够最小宽度。						
			conversion ，用于指定格式化的样式，和限制对应入参的数据类型。

			flags:
				-: 在最小宽度内左对齐,不可以与0标识一起使用。
				0: 若内容长度不足最小宽度，则在左边用0来填充。
				#: 对8进制和16进制，8进制前添加一个0,16进制前添加0x。
				+: 结果总包含一个+或-号。
				空格: 正数前加空格，负数前加-号。
				,: 只用与十进制，每3位数字间用,分隔。
				(: 若结果为负数，则用括号括住，且不显示符号。
			conversion:
				d:十进制
				o:八进制
				x或X:十六进制
				n:平台独立的换行符, 实际就是一个换行符
				b:布尔类型，只要实参为非false的布尔类型，均格式化为字符串true，否则为字符串false。

		 */
		 
		String.format("smallTable:%1$-25s already import successful, status: %2$-7s" ,smallTableName,status)
		
	    System.out.println("实现左对齐：");
	    System.out.println(String.format("%1$-9d", 1245));
	    System.out.println("实现用0填充：");
	    System.out.println(String.format("%1$09d", 1245));
	    System.out.println("实现用+/-填充：");
	    System.out.println(String.format("%1$+9d", 1245));
	    System.out.println("实现用空格填充：");
	    System.out.println(String.format("%1$ 9d", 1245));
	    System.out.println(String.format("%1$ 9d", -1245));
	    System.out.println("实现用,填充：");
	    System.out.println(String.format("%1$,9d", 1245));
	    System.out.println("实现用(填充：");
	    System.out.println(String.format("%1$(9d", -1245));
	    System.out.println("实现结合使用：");
	    System.out.println(String.format("%1$0(9d", -1245));

	}

}
