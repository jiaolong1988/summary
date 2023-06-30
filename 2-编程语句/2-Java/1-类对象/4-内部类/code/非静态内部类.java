/**
 * ��̬�ڲ���ʹ���ܽ�
 * 	1.ֻ�����ⲿ���н��� ʵ�������ã�����test������
 * 
 * 	2.���ⲿ��-�ڲ���-�ڲ��෽�� ��������ͬʱ
 * 		 ͨ�� �ⲿ������.this.varName �����ⲿ��ʵ������
 * 		 ͨ�� this.varName �����ڲ���ʵ���ı���
 * 
 *  3.�ڲ��������ⲿ��û����������������ֱ�ӷ����ⲿ��������
 *  	3.1����ڲ��෽���ж���ı������� �� �ⲿ�ඨ���������ͬ������ʵ��� �ڲ��෽������ľֲ�������
 *         Ҳ����˵���ֲ��������ȷ���
 * 
 * @author jiaolong
 * @date 2023-06-29 11:38:37
 */
public class DiscernVariable {
	private String noRepeatVar ="�ⲿ�ඨ��� ���ظ� ʵ������";
	private String prop = "�ⲿ�ඨ��� ʵ������";
	
	private class InClass {
		private String prop = "�ڲ��ඨ��� ʵ������";
		
		public void info(){
			String prop = "�ڲ��෽���ж���� �ֲ�����";
			
			// ͨ�� �ⲿ������.this.varName �����ⲿ��ʵ������
			System.out.println("�ⲿ��-�ڲ���-�ڲ��෽�� ��������ͬ,�����ⲿ�����[DiscernVariable.this.prop]��" +  DiscernVariable.this.prop);
			
			// ͨ�� this.varName �����ڲ���ʵ���ı���
			System.out.println("�ⲿ��-�ڲ���-�ڲ��෽�� ��������ͬ,�����ڲ������[this.prop]��" + this.prop);
			// ֱ�ӷ��ʾֲ�����
			System.out.println("�ⲿ��-�ڲ���-�ڲ��෽�� ��������ͬ,�����ڲ��෽���ľֲ�����[prop]��" + prop);
			
			System.out.println("\n���������ظ���"+noRepeatVar);
		}
	}
	
	public void test(){
		// �ⲿ�಻��ֱ�ӷ��ʷǾ�̬�ڲ����ʵ������, prop
		// ��������ڲ����ʵ��������������ʽ�����ڲ������
		InClass in = new InClass();
		in.info();
	}
	
	public static void main(String[] args){
		new DiscernVariable().test();
	}
}
