

/**
 * �ж�ʵ�������ͣ��ж�������࣬�ж�Integer.type��Integer.class������
 * @author jiaolong
 * @date 2022-10-06 10:27:12
 */
public class JudgeClassOrObjectType {

	
	public static void main(String[] args) {
							
			/**
			  	1. obj instanceof ����
				�����ж�һ������ʵ���Ƿ���һ�����ӿڵĻ��������ӽӿڵ�ʵ��
			*/	
			System.out.println("String����ΪObject���͵�ʵ����"+("" instanceof Object));				
			System.out.println("obj������String���͵�ʵ����"+(new Object() instanceof String));					
			System.out.println("================================");
			
			/**
				2. class1.isAssignableFrom(class2)
			 	�ж� class2 �ǲ��� class1 ����������ӽӿ�	
			 */		
			System.out.println("String���Ƿ���Object������-true��"+ Object.class.isAssignableFrom(String.class));
			System.out.println("Object���Ƿ���String������-false��"+String.class.isAssignableFrom(Object.class));
			
			System.out.println("================================");
			
			/**
			 	3. asSubclass
					public <U> Class<? extends U> asSubclass(Class<U> clazz) {
				    	if (clazz.isAssignableFrom(this))
					        return (Class<? extends U>) this;
					    else
					        throw new ClassCastException(this.toString());
					}
		        �����ǽ��������������class����ת������clazz��������ʾ�� class �����ĳ�����ࡣ
		    */
			System.out.println("String��Object�����࣬����Stirngת��ΪObject�����ࣺ"+String.class.asSubclass(Object.class));
			//2.Object������String������,�޷���ȡString��������Ϣ�����׳��쳣��
			//Class<?> c2 = Object.class.asSubclass(String.class);

			
			/**
			 * 4.Integer.class ��int.class
			 */
			System.out.println("================================");
			Class<?> integerClass = Integer.class;
			System.out.println("Integer��class����:"+integerClass);
			Class<?> intClass = int.class;
			System.out.println("int��class����:"+intClass);
			Class<Integer> it = Integer.TYPE;
			System.out.println("Integer��ԭʼ���Ͷ�Ӧ��Class����"+it);
		
	}

}
