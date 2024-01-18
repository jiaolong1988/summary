package encapsulation;

/**
 * @author jiaolong
 * @date 2024/01/17 16:12
 * @description: java多态案例
 *  多态是同一个行为具有多个不同表现形式，对应编程就是同一个接口，使用不同的实例而执行不同操作
 *
 *  方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现.
 * 	(1)方法重载是一个类中定义了多个方法名相同,而他们的参数的数量不同或数量相同而
 *       类型和次序不同,则称为方法的重载(Overloading)。
 * 	(2)方法重写是在子类存在方法与父类的方法的名字相同,而且参数的个数与类型一样,
 *       返回值也一样的方法,就称为重写(Overriding)。
 * 	(3)方法重载是一个类的多态性表现,而方法重写是子类与父类的一种多态性表现
 */
public class PolymorphismTest {
    public static void main(String[] args) {

        //多态是同一个行为具有多个不同表现形式
        //1.子类与父类间的多态
        show(new Circle());
        show(new Square());
        show(new Triangle());

        //2.一个类的多态性表现
        open();
        open("xxx");
        open(12);
    }

    public static void show(Shape shape)  {
        shape.draw();
    }

    public static void open(){
        System.out.println("类中的多态：无参数");
    }
    public static void open(String info){
        System.out.println("类中的多态：String参数:"+info);
    }
    public static void open(int num){
        System.out.println("类中的多态：int参数:"+num);
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