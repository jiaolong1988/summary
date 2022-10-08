

//ͨ�����������ж������������
class Apple<T> {
	public Apple() {}
	//���ͱ���(���ܶ���static ���ͱ���)
	private T info;
	//���͹��췽��
	public Apple(T info) {
		this.info = info;
	}
	//���Ͳ���
	public void setInfo(T info) {
		this.info = info;
	}
	//���ͷ���ֵ
	public T getInfo() {
		return this.info;
	}
	// ���������󣬲����ھ�̬������ʹ�÷��Ͳ���
	//public static void bar(T msg){}
	//���������󣬾�̬����������������
    //static T info;
}

//����̳з��͸���ʱ������ָ������ķ������ͣ�����Ҳ�ɶ��巺��
class AppleChild<T> extends Apple<String> {
	T b;
	//��д���෽��
	public String getInfo() {
		return super.getInfo();
	}
	
	public void setB(T b) {
		this.b= b;
	}	
	public T getB() {
		return this.b;
	}
}

//���ͽӿ�
interface AppleInterface<T>{
	//���巺�ͷ���
	public T test(T info);
}
class AppleInterfaceImpl implements AppleInterface<String>{

	@Override
	public String test(String info) {
		return info;
	}
	
}

/**
 * ����������� �ӿ� �����еĶ���
 * @author jiaolong
 * @date 2022-10-07 12:20:17
 */
public class GenericDefinedTest {
	public static void main(String[] args) {

		//1.���巺����
		Apple<String> a1 = new Apple<>("ƻ��");
		Apple<Double> a2 = new Apple<>(5.67);		
		System.out.println(a1.getInfo());
		System.out.println(a2.getInfo()+"\r\n");
		
	    //2.���巺������
		AppleChild<String> ac = new AppleChild<>();
		ac.setB("jiaolong");
		System.out.println(ac.getB());
		
		//3.���巺�ͽӿ�
		AppleInterfaceImpl impl = new AppleInterfaceImpl();
		System.out.println(impl.test("chenglong"));
	}
}
