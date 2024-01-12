package designPatterns.Decorator;

import java.nio.ByteBuffer;

/**
 * @author jiaolong
 * @date 2024-1-10 10:21
 * δʹ��װ��ģʽʵ�ֹ�����չ-�����汾
 */
public class NoDecoratorTest {


	public static void main(String[] args) {
		Person xc = new Person("С��");
        xc.Show();

		System.out.println("��һ��װ�磺");
		xc.WearTShirts();
		xc.WearBigTrouser();
		xc.WearSneakers();
		xc.Show();

		System.out.println("\n�ڶ���װ�磺");
		xc.WearSuit();
		xc.WearTie();
		xc.WearLeatherShoes();
		xc.Show();
	}
}

class Person {
    private String name;
    public Person(String name)
    {
        this.name = name;
    }
 
    public void WearTShirts()
    {
       System.out.println("��T�� ");
    }
 
    public void WearBigTrouser()
    {
       System.out.println("��� ");
    }
 
    public void WearSneakers()
    {
       System.out.println("����Ь ");
    }
 
    public void WearSuit()
    {
       System.out.println("��װ ");
    }
 
    public void WearTie()
    {
       System.out.println("��� ");
    }
 
    public void WearLeatherShoes()
    {
       System.out.println("ƤЬ ");
    }
 
    public void Show()
    {
        System.out.println(String.format("װ���%1$s", name));
    }
}