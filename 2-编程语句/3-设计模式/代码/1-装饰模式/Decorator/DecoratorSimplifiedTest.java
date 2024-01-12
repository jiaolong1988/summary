package designPatterns.Decorator;

/**
 * @author jiaolong
 * @date 2024-1-10 10:18
 * װ��ģʽ�ļ�д��-��Component��ConcreateComponent�ϲ���д��
 */
public class DecoratorSimplifiedTest {

	public static void main(String[] args) {
		//��������
		PersonN person = new PersonN("jiaolong");
		
		//��չ����
		Finery tShirts = new TShirts();
		Finery trouser = new BigTrouser(); 
		Finery sneakers = new Sneakers();


		//��person�Ļ�������չ����
		trouser.decorate(person);
		//��trouser�Ļ�������չ����
		sneakers.decorate(trouser);
		//��sneakers�Ļ�������չ����
		tShirts.decorate(sneakers);

		tShirts.show();
	}

}

class PersonN {
	private String name;

	public PersonN() {
	}

	public PersonN(String name) {
		this.name = name;
	}

	public void show() {
		System.out.format("װ���%1$s \n", name);
	}
}

class Finery extends PersonN {

	protected PersonN personN;

	public void decorate(PersonN personN) {
		this.personN = personN;
	}

	@Override
	public void show() {
		//��������жϣ����� �������ֱ�ӵ��ø÷����ᱨ����Ϊû������ װ�ζ���
		if (personN != null){
			personN.show();
		}

	}
}

class TShirts extends Finery {

	@Override
	public void show() {
		super.show();
		System.out.println("��T�� ");
		
	}

}

class BigTrouser extends Finery {

	@Override
	public void show() {
		super.show();
		System.out.println("��� ");
		
	}

}

class Sneakers extends Finery {

	@Override
	public void show() {
		super.show();
		System.out.println("����Ь ");	
		
	}

}
