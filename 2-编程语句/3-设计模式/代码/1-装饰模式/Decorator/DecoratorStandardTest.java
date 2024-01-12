package designPatterns.Decorator;

/**
 * @author jiaolong
 * @date 2024-1-10 10:18
 * @version 1.0
 *
 * װ��ģʽ�ı�׼д��
 */
public class DecoratorStandardTest {
		
	public static void main(String[] args) {

		//��������
		Component cc = new ConcreteComponent("jiaolong");

		//��չ����
		Decorator tshirt = new TShirtsA();
		Decorator trouse = new BigTrouserA();
		Decorator sneak = new SneakersA();

		//��������չcomponent�������ܣ���Ϊ�����ڴ˹����Ͻ�����չ��
		tshirt.setComponent(cc);
		trouse.setComponent(tshirt);
		sneak.setComponent(trouse);
		
		sneak.show();
	}

}

interface Component{
	void show();
}


class ConcreteComponent implements Component {
	private String name;

	public ConcreteComponent(String name) {
		this.name = name;
	}

	@Override
	public void show() {
		System.out.format("װ���%1$s \n", name);
	}

}

abstract class Decorator implements Component{

	protected Component component;
	
	@Override
	public void show() {
		//��������жϣ����� �������ֱ�ӵ��ø÷����ᱨ����Ϊû������ װ�ζ���
		if (component != null){
			component.show();
		}

	}
	
	public void setComponent(Component component) {
		this.component = component;
	}
}



class TShirtsA extends Decorator {

	@Override
	public void show() {
		super.show();
		System.out.println("��T�� ");
		
	}

}

class BigTrouserA extends Decorator {

	@Override
	public void show() {
		super.show();
		System.out.println("��� ");
		
	}

}

class SneakersA extends Decorator {

	@Override
	public void show() {
		super.show();
		System.out.println("����Ь ");	
		
	}

}
