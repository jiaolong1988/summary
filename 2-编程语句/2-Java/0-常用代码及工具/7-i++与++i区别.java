/*
 * 1.�ڲ���ֵ������£�++i��i++�� iֵ��һ���ģ�û������
 * 2.�ڸ�ֵ������£�x = ++i; �� x = i++;������Ϊ��
 * 	��2.1����++��ǰ �ȼ����ֵ
 *  ��2.2����++�ں� �ȼƸ�ֵ�����
 */
public class TestTemp {
		public static void main(String[] args) {
		
			int i=0,j=0;			
			int x=0,y=0;
			
			x = ++i;
			y = j++;
			
			System.out.println("x= ++i : "+x);						
			System.out.println("y= j++ : "+y);
			
			System.out.println();			
			System.out.println("++i   :"+i);
			System.out.println("j++   :"+j);
			
			
			/* ��ӡ���
				x= ++i : 1
				y= j++ : 0

				++i   :1
				j++   :1
			*/
		}
}