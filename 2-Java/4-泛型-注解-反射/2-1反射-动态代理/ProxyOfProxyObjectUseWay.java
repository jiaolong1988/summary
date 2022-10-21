import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface People {
	public People work(String workName);

	public String time();
}

class Student implements People {

	@Override
	public People work(String workName) {
		System.out.println("����������" + workName);
		return this;
	}

	@Override
	public String time() {
		return "2018-06-12";
	}
}

class WorkHandler implements InvocationHandler {
	// ���������
	private Object obj;

	public WorkHandler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("	before ��̬����...");
		System.out.println("	����������ƣ�" + proxy.getClass().getName());
		System.out.println("	�������������" + this.obj.getClass().getName());
		if (method.getName().equals("work")) {
			method.invoke(this.obj, args);
			System.out.println("	work() after ��̬����...");
			//this�������InvocationHandler�ӿ�ʵ���౾����������ʵ�Ĵ������
			return proxy;
		} else {
			System.out.println("	time() after ��̬����...");
			return method.invoke(this.obj, args);
		}
	}

}

public class ProxyOfProxyObjectUseWay {

	public static void main(String[] args) {
		/**
		 * ��������Ҫ����invoke(Object proxy, Method method, Object[] args)�� proxy�������ʹ��
		 * �ο���https://www.jianshu.com/p/f4159d53a0c0
		 */
		People people = new Student();
		InvocationHandler handler = new WorkHandler(people);

		People proxy = (People) Proxy.newProxyInstance(
				people.getClass().getClassLoader(),
				people.getClass().getInterfaces(), 
				handler);
		People p = proxy.work("д����").work("����").work("�Ͽ�");

		System.out.println("-----------------��ӡ���صĶ���" + p.getClass());
		System.out.println("");

		String time = proxy.time();
		System.out.println(time);
	}

}
