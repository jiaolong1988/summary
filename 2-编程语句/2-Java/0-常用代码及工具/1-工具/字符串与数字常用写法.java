import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.time.ZoneId;

public class T2 {

	public static void main(String[] args) {
		//�ַ���ƴ��, ��ʾ�����usr\local\bin
	    String joined = String.join(File.separator, "usr", "local", "bin");
        System.out.println(joined);
           

		//���12345
		StringBuffer sb = new StringBuffer(5);
		sb.append("123456789");
		sb.setLength(5);
		System.out.println(sb.length()+" "+sb.toString());
		
		// ����һ��32λ�Ķ�������,���λ�Ƿ���λ������Ϊ��-2147483645
		int binVal = 0B1000_0000_0000_0000_0000_0000_0000_0011;
		System.out.println(binVal);
		
		//double�����»��߱�ʾ����
		double pi = 3.14_15_92_65_36;
		System.out.println(pi);
		
		double height = 8_8_4_8.23;
		System.out.println(height);
		
		
	}
}
