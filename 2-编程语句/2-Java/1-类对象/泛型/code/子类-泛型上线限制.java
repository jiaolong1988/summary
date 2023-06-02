import java.util.ArrayList;
import java.util.List;

// 定义一个抽象类Shape[形状]
abstract class Shape {
	public abstract void draw(Canvas c);
}

//定义Shape的子类Circle[圆圈]
class Circle extends Shape {
	// 实现画图方法，以打印字符串来模拟画图方法实现
	public void draw(Canvas c) {
		System.out.println("Circle：" + c );
	}
}

//定义Shape的子类Rectangle[长方形]
class Rectangle extends Shape {
	// 实现画图方法，以打印字符串来模拟画图方法实现
	public void draw(Canvas c) {
		System.out.println("Rectangle：" + c);
	}
}

//[油画]
class Canvas {
	
//	public void drawAll(List<Shape> shapes){
//		for (Shape s : shapes) {
//			s.draw(this);
//		}
//	}
	
	//使用被限制的泛型通配符
	public void drawAll(List<? extends Shape> shapes) {
		for (Shape s : shapes) {
			s.draw(this);
			
		}
	}
}


/**
 * 在形参与类定义时均可设置 通配符。
 */
public class GenericRangeTest {
	public static void main(String[] args) {	
		//形参设定通配符
		xcTest();
		
	}

	//通配符在形参的设定
    public static void xcTest() {
    	//1.通配符的测试
		List<Circle> circleList = new ArrayList<>();	
		List<Rectangle> rectangleList = new ArrayList<>();
		circleList.add(new Circle());
		rectangleList.add(new Rectangle());
			
		/**
		 *  1.由于List<Circle>并不是List<Shape>的子类型,因此不能直接转化 List<Shape> s = circleList;	
		 *  2.<? extends Shape>表示Shape的任意一个子类的类型，因此List<? extends Shape> 只能以父类(Shape)的方式获取对象。
		 *  3.shape1,shape2不能添加对象，因为集合不能确定集合的具体类型。
		 */
		List<? extends Shape> shape1 = circleList;
		List<? extends Shape> shape2 = rectangleList;
		Shape h1 = shape1.get(0);
		//无法添加数据
		//shape2.add((Shape)new Circle());
		
		//只能以Object的方式获取对象，不能添加数据
		List<?> list = circleList;
		Shape h2 = (Shape) list.get(0);
		
		//2.形参上设置通配符
		Canvas c = new Canvas();
		c.drawAll(circleList);
		c.drawAll(rectangleList);
    }
}

