package designPatterns.Decorator;


/**
 * @author jiaolong
 * @date 2024-1-10 15:02
 * δʹ��װ��ģʽʵ�ֹ�����չ-������
 */
public class NoDecoratorUPTest {
    public static void main(String[] args) {
        Person person = new Person("jiaolong");
        FineryX tshirt = new TShirtsX();
        FineryX bigTrouser = new WearBigTrouserX();

        person.Show();
        tshirt.show();
        bigTrouser.show();
    }

}

class PersonX {
    private String name;
    public PersonX(String name)
    {
        this.name = name;
    }

    public void show()
    {
        System.out.println(String.format("װ���%1$s", name));
    }
}

abstract class FineryX {
    public abstract void show();
}


class TShirtsX extends FineryX {
    @Override
    public void show() {
        System.out.println("��T�� ");
    }
}

class WearBigTrouserX extends FineryX {
    @Override
    public void show() {
        System.out.println("��� ");
    }
}