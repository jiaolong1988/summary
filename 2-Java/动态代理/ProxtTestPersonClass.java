package test.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person1 {
	void walk();

	void sayHello(String name);
}

class ChinaPerson implements Person1{

	@Override
	public void walk() {
		System.out.println("china walk!");
		
	}

	@Override
	public void sayHello(String name) {
		System.out.println("china say hello!");
		
	}
	
}
/**
 * 
 * @ClassName:  MyInvokationHandler1   
 * @Description:TODO(实现Person接口的类为ChinaPerson,代理对象通过反射调用ChinaPerson的相应方法)   
 * @author: jiaolong
 * @date:   2022年3月4日 上午11:01:09      
 * @Copyright:
 */
class MyInvokationHandler1 implements InvocationHandler {
	/*
	 * 执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法 其中： proxy：代表动态代理对象 method：代表正在执行的方法
	 * args：代表调用目标方法时传入的实参。
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.print("----正在执行的方法:" + method);
		if(args != null) {
			System.out.print("参数:" + args.toString());
		}
		System.out.println("");
		
		
		//传入代理对象进行方法调用
		ChinaPerson chinaPerson = new ChinaPerson();
		Object result = method.invoke(chinaPerson, args);
		
		

		
		System.out.println("\n");
		return result;
	}
}


/**
 * 
 * @ClassName:  ProxtTestPersonClass   
 * @Description:TODO(java创建代理对象,)   
 * @author: jiaolong
 * @date:   2022年3月4日 上午11:08:14      
 * @Copyright:
 */
public class ProxtTestPersonClass {
	public static void main(String[] args) throws Exception {
		//代理对象的业务处理逻辑
		InvocationHandler handler = new MyInvokationHandler1();

	
//具体写法1-创建代理对象
        //获取person的代理class 注意：第二个参数一定要写接口类
		Class<?> proxyClass = Proxy.getProxyClass(ChinaPerson.class.getClassLoader(), Person1.class);
		//设置代理类的构造方法
		Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
		//创建一个动态代理对象
		Person1 p = (Person1) constructor.newInstance(handler);

//具体写法2-创建代理对象		
//		//使用指定的InvocationHandler来生成一个动态代理对象
//		Person p1 = (Person)Proxy.newProxyInstance(
//													Person.class.getClassLoader(), 
//												    new Class[]{Person.class}, 
//												    handler);
		
		//调用动态代理对象的walk()和sayHello()方法
		System.out.println("【实现Person接口的ChinaPerson类，并创建ChinaPerson的动态代理类】");
		p.walk();
		p.sayHello("孙悟空");
		
		

	}

}
