
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;

/**
 * Files���ճ�ʹ�÷���
 * 
 * @author jiaolong
 * @date 2023-06-13 02:45:57
 */
public class FilesTest {
	public static void main(String[] args) throws Exception {
		Path sourcePath = Paths.get("src/FilesTest.java").toAbsolutePath();
		System.out.println("Դ�ļ�·����"+sourcePath);
		
		// �����ļ�
		Files.copy(sourcePath, new FileOutputStream("a.txt"));
		
		// �ж�FilesTest.java�ļ��Ƿ�Ϊ�����ļ�
		System.out.println("\nFilesTest.java�Ƿ�Ϊ�����ļ���" + Files.isHidden(sourcePath));
		
		// һ���Զ�ȡFilesTest.java�ļ���������
		List<String> lines = Files.readAllLines(sourcePath, Charset.forName("gbk"));
		System.out.println("��ȡ�ļ�������Ϣ�洢ΪList:"+lines);
		
		// �ж�ָ���ļ��Ĵ�С
		System.out.println("\nFilesTest.java�Ĵ�СΪ��" + Files.size(sourcePath));
		
		List<String> poem = new ArrayList<>();
		poem.add("ˮ��̶������Ծ");
		poem.add("������б̸ͺ�");
		// ֱ�ӽ�����ַ�������д��ָ���ļ���
		Path writeFlag = Files.write(Paths.get("pome.txt"), poem, Charset.forName("gbk"));
		System.out.println("�ļ�д��ɹ�,д��·����"+writeFlag.toAbsolutePath());
		
		System.out.println("\n��ȡ��ǰĿ¼�������ļ�����Ŀ¼");
		// ʹ��Java 8������Stream API�г���ǰĿ¼�������ļ�����Ŀ¼
		Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
		
		
		// ʹ��Java 8������Stream API��ȡ�ļ�����
		Files.lines(sourcePath, Charset.forName("gbk")).forEach(line -> System.out.println(line));
		
		FileStore cStore = Files.getFileStore(Paths.get("C:"));
		// �ж�C�̵��ܿռ䣬���ÿռ�
		System.out.println("C:���пռ䣺" + cStore.getTotalSpace());
		System.out.println("C:���ÿռ䣺" + cStore.getUsableSpace());
	}
}
