import java.util.ArrayList;
import java.util.List;


//定义类的形参
class ClassDefinedRange<T extends Number>{
	T t;	
	public void t1 (List<? extends Object> s) {}
	public void t2 (List<? extends Object> T) {}
	//错误方式-因为T是一个确定类型，且Objct的子类有多个，因此使用T有误
	//public void t3 (List<T extends Object> s) {}
	public void t4 (List<T> s) {}
	public void t5 (T s) {}
}

//定义类的形参
class ClassDefinedRange1<T extends Number>{
	T t;	
	public void t1 (List<? extends Object> s) {}
}
class ClassDefinedRange2<T extends Number & java.io.Serializable>{
	T t;	
	public void t1 (List<? extends Object> s) {}
	public void t2 (List<T> s) {}
}

/**
 * 在形参与类定义时均可设置 通配符。
 */
public class GenericRangeTest {
	public static void main(String[] args) {	
		//形参设定通配符
		xcTest();
		
		//定义 类型形参不能使用通配符
		ClassDefinedRange1<Integer> a = new ClassDefinedRange1<>();
		ClassDefinedRange2<Long> b = new ClassDefinedRange2<>();
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

