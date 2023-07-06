import java.util.ArrayList;
import java.util.List;

// ����һ��������Shape[��״]
abstract class Shape {
	public abstract void draw(Canvas c);
}

//����Shape������Circle[ԲȦ]
class Circle extends Shape {
	// ʵ�ֻ�ͼ�������Դ�ӡ�ַ�����ģ�⻭ͼ����ʵ��
	public void draw(Canvas c) {
		System.out.println("Circle��" + c );
	}
}

//����Shape������Rectangle[������]
class Rectangle extends Shape {
	// ʵ�ֻ�ͼ�������Դ�ӡ�ַ�����ģ�⻭ͼ����ʵ��
	public void draw(Canvas c) {
		System.out.println("Rectangle��" + c);
	}
}

//[�ͻ�]
class Canvas {
	
//	public void drawAll(List<Shape> shapes){
//		for (Shape s : shapes) {
//			s.draw(this);
//		}
//	}
	
	//ʹ�ñ����Ƶķ���ͨ���
	public void drawAll(List<? extends Shape> shapes) {
		for (Shape s : shapes) {
			s.draw(this);
			
		}
	}
}


/**
 * ���β����ඨ��ʱ�������� ͨ�����
 */
public class GenericRangeTest {
	public static void main(String[] args) {	
		//�β��趨ͨ���
		xcTest();
		
	}

	//ͨ������βε��趨
    public static void xcTest() {
    	//1.ͨ����Ĳ���
		List<Circle> circleList = new ArrayList<>();	
		List<Rectangle> rectangleList = new ArrayList<>();
		circleList.add(new Circle());
		rectangleList.add(new Rectangle());
			
		/**
		 *  1.����List<Circle>������List<Shape>��������,��˲���ֱ��ת�� List<Shape> s = circleList;	
		 *  2.<? extends Shape>��ʾShape������һ����������ͣ����List<? extends Shape> ֻ���Ը���(Shape)�ķ�ʽ��ȡ����
		 *  3.shape1,shape2������Ӷ�����Ϊ���ϲ���ȷ�����ϵľ������͡�
		 */
		List<? extends Shape> shape1 = circleList;
		List<? extends Shape> shape2 = rectangleList;
		Shape h1 = shape1.get(0);
		//�޷��������
		//shape2.add((Shape)new Circle());
		
		//ֻ����Object�ķ�ʽ��ȡ���󣬲����������
		List<?> list = circleList;
		Shape h2 = (Shape) list.get(0);
		
		//2.�β�������ͨ���
		Canvas c = new Canvas();
		c.drawAll(circleList);
		c.drawAll(rectangleList);
    }
}

