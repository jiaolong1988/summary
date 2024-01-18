package encapsulation;

/**
 * @author jiaolong
 * @date 2024/01/17 16:12
 * @description: java��̬����
 *  ��̬��ͬһ����Ϊ���ж����ͬ������ʽ����Ӧ��̾���ͬһ���ӿڣ�ʹ�ò�ͬ��ʵ����ִ�в�ͬ����
 *
 *  ��������д(Overriding)������(Overloading)��java��̬�ԵĲ�ͬ����.
 * 	(1)����������һ�����ж����˶����������ͬ,�����ǵĲ�����������ͬ��������ͬ��
 *       ���ͺʹ���ͬ,���Ϊ����������(Overloading)��
 * 	(2)������д����������ڷ����븸��ķ�����������ͬ,���Ҳ����ĸ���������һ��,
 *       ����ֵҲһ���ķ���,�ͳ�Ϊ��д(Overriding)��
 * 	(3)����������һ����Ķ�̬�Ա���,��������д�������븸���һ�ֶ�̬�Ա���
 */
public class PolymorphismTest {
    public static void main(String[] args) {

        //��̬��ͬһ����Ϊ���ж����ͬ������ʽ
        //1.�����븸���Ķ�̬
        show(new Circle());
        show(new Square());
        show(new Triangle());

        //2.һ����Ķ�̬�Ա���
        open();
        open("xxx");
        open(12);
    }

    public static void show(Shape shape)  {
        shape.draw();
    }

    public static void open(){
        System.out.println("���еĶ�̬���޲���");
    }
    public static void open(String info){
        System.out.println("���еĶ�̬��String����:"+info);
    }
    public static void open(int num){
        System.out.println("���еĶ�̬��int����:"+num);
    }
}
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Circle.draw()");
    }
}

class Square extends Shape {
    void draw() {
        System.out.println("Square.draw()");
    }
}

class Triangle extends Shape {
    void draw() {
        System.out.println("Triangle.draw()");
    }
}