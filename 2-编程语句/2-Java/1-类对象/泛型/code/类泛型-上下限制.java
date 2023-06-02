import java.util.ArrayList;
import java.util.List;


//��������β�
class ClassDefinedRange<T extends Number>{
	T t;	
	public void t1 (List<? extends Object> s) {}
	public void t2 (List<? extends Object> T) {}
	//����ʽ-��ΪT��һ��ȷ�����ͣ���Objct�������ж�������ʹ��T����
	//public void t3 (List<T extends Object> s) {}
	public void t4 (List<T> s) {}
	public void t5 (T s) {}
}

//��������β�
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
 * ���β����ඨ��ʱ�������� ͨ�����
 */
public class GenericRangeTest {
	public static void main(String[] args) {	
		//�β��趨ͨ���
		xcTest();
		
		//���� �����ββ���ʹ��ͨ���
		ClassDefinedRange1<Integer> a = new ClassDefinedRange1<>();
		ClassDefinedRange2<Long> b = new ClassDefinedRange2<>();
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

