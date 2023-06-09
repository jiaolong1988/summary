import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//����������
interface Interceptor {
	public boolean before(Object proxy, Object target, Method method, Object[] args);

	public void around(Object proxy, Object target, Method method, Object[] args);

	public void after(Object proxy, Object target, Method method, Object[] args);
}

//���������
interface Shopping {
	void learn();
	void play();
}

class Client implements Shopping {
	public void learn() {
		System.out.println("�����������Ʒ");
	}
	public void play() {
		System.out.println("����ȥ����");
	}
}

class MyInterceptor implements Interceptor {

	/**
	 * proxy 	�����Ĵ������
	 * target 	������Ķ���
	 * method	ִ�еķ���
	 * args 	ִ�еķ�������
	 */
	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("before");
		
		if(method.getName().equals("learn")) {
			System.out.println("learn method ����ִ��");
			return true;
		}else {
			System.out.println("play method ������ִ��");
			return false;
		}
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("around");
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("after");
	}
}



//������������
class InterceptorProxy implements InvocationHandler {
	//������Ķ���
	private Object target = null;
	//�������ع���ĵĶ���
	Interceptor interceptor = null;

	InterceptorProxy(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/**
	 * �������߼�
	 *
	 * @param proxy  �������
	 * @param method ���ȷ���
	 * @param args   ���ȷ�������
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (interceptor == null) {
			method.invoke(target, args);
		}
		Object result = null;
		if (interceptor.before(proxy, target, method, args)) {
			result = method.invoke(target, args);
		} else {
			interceptor.around(proxy, target, method, args);
		}
		interceptor.after(proxy, target, method, args);
		return result;
	}
}


/**
 * ��̬����ʵ��������
 */
public class InterceptorProxyImpl {
	public static void main(String[] args) {
		/**
		 * ʹ�ö�̬����ʵ��������
		 */
		
		//����ȥ����������
		InterceptorProxy interceptor = new InterceptorProxy(new MyInterceptor());
		//���ñ�����Ķ��󣬲����ش������
		Shopping shop = (Shopping) interceptor.bind(new Client());
		System.out.println("....ִ��play����");
		shop.play();
		
		System.out.println("\n....ִ��learn����");
		shop.learn();
	}
}
