
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	String walk();
	void sayHello(String name);
}

class ChinaPerson implements Person{
	@Override
	public String walk() {
		System.out.println("china walk!");
		return "jiaolong";
		
	}
	@Override
	public void sayHello(String name) {
		System.out.println("china say hello! ");	
	}
}

//�̳�д��
class BaseInvokationHandler implements InvocationHandler {
	/*
	 * proxy��	�Ѵ����Ĵ������
	 * method��	��������ִ�еķ���
	 * args��	����ִ�з����Ĳ�����
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		System.out.print("----����ִ�еķ���:" + method);
		if(args != null) 
			System.out.print("  ����:" + args[0].toString()+"\n");
		else
			System.out.print(" �޲���: \n" );
			
		//��Ҫ������Ķ���
		ChinaPerson cp = new ChinaPerson();
		Object result = method.invoke(cp, args);	
		
		return result;
	}
}

//�ռ�д��
class AopInvokationHandler implements InvocationHandler {
	//��Ҫ������Ķ���
    private Object target = null;
    
    AopInvokationHandler(){}
    AopInvokationHandler(Object target) {
        this.target = target;
    }

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 	  //�����������������
        							  target.getClass().getInterfaces(),	  //���������Ľӿ���Ϣ
        							  this);								  //InvocationHandlerʵ����
    }

	/*
	 * proxy��	�Ѵ����Ĵ������
	 * method��	��������ִ�еķ���
	 * args��	����ִ�з����Ĳ�����
	 */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("����ǰ...");
        Object obj = method.invoke(target, args);
        System.out.println(obj==null?"û�з���ֵ" :"����ֵ"+(String)obj);
        System.out.println("�����...");
        
        //���ô���ķ��ؽ��
        return "jiao_val";
    }
}



public class ProxyBaseUse {
	/**
	 * �����÷�
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void baseUseWay() throws Exception {
				
		
//д��1-�����������Ĺ���		
		
//		  //��ȡperson�Ĵ���class ע�⣺�ڶ�������һ��Ҫд�ӿ��� 
//		  Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), Person.class);
//		  //���ô�����Ĺ��췽�� 
//		  Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class); 
//		  //����һ����̬������� 
//		  Person p = (Person) constructor.newInstance(handler);
		 

//д��2-�����������(д��1�ļ�д)				
		//��������ҵ�����߼�
		InvocationHandler handler = new BaseInvokationHandler();
		
		//�������-�ӿ�
		ClassLoader personInterfaceLoader = Person.class.getClassLoader();
		//�������-�ӿ�ʵ����
		ClassLoader interfaceImplClassLoader = ChinaPerson.class.getClassLoader();
		//�������-�ӿ�ʵ�����ʵ��
		ClassLoader interfaceImplClassObjectLoader = new ChinaPerson().getClass().getClassLoader();
		
		//ʹ��ָ����InvocationHandler������һ����̬�������
		Person p = (Person)Proxy.newProxyInstance(interfaceImplClassObjectLoader,  	 	//�������-ʹ�ýӿڵ������������
											    //new Class[]{Person.class},     		//�ӿ�
												  ChinaPerson.class.getInterfaces(),
												  handler);						 	    //ҵ�񷽷�
		
		//ͨ�������������walk()��sayHello()����
		String val = p.walk();
		System.out.println("===����ֵ"+val);
		
		System.out.println("");
		p.sayHello("�����");
	}
	
	/**
	 * ��̬����AOP��ʵ��
	 */
	public static void aopUseWay() {
		AopInvokationHandler dpih = new AopInvokationHandler();
		Person p = (Person)dpih.bind(new ChinaPerson());
		
		System.out.println("ִ��walk�����ķ��ؽ����"+p.walk());
		System.out.println("");
		p.sayHello("aop");
		
	}
	
	public static void main(String[] args) throws Exception {
		/**
		 * ��̬����Ļ���ʹ����AOP��ʵ�ְ���
		 */
		baseUseWay();
		System.out.println(" \r\n----------------- \r\n");
		aopUseWay();
	}

}
