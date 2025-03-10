
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
    public static void main(String[] args) throws Exception{
       // baseTest(sourcePath,target);
       // readWriteListTest();
        readWriteStringTest();
    }

    /**
     * �ַ����ļ���д
     * @return void
     **/
    public static void readWriteStringTest() throws Exception {
        Path writeStringPath= Paths.get("writeString.txt");
        // ��׷��-�Զ������ļ�
        Files.write(writeStringPath, "15".getBytes(StandardCharsets.UTF_8));

        // ׷��-���Զ������ļ�
        Files.write(writeStringPath, "16".getBytes(), StandardOpenOption.APPEND);


        String content = new String(Files.readAllBytes(writeStringPath), StandardCharsets.UTF_8);
        System.out.println("��ȡ�ļ��е��ַ�����"+content);
    }

    /**
     * �����ļ���д
     * @return
     **/
    public static void readWriteListTest() throws Exception {
        Path path = Paths.get("pome.txt");
//д���ļ�
        List<String> poem = new ArrayList<>();
        poem.add("ˮ��̶������Ծ");
        poem.add("������б̸ͺ�");
        poem.add("");
        // ֱ�ӽ�����ַ�������д��ָ���ļ���
        //Path writeFlag = Files.write(Paths.get("pome.txt"), poem, Charset.forName("gbk"));
        Path writeFlag = Files.write(path, poem);
        System.out.println("�ļ�д��ɹ�,д��·����"+writeFlag.toAbsolutePath());

//��ȡ�ļ���ʽһ
        // List<String> lines = Files.readAllLines(sourcePath, Charset.forName("utf-8"));
        List<String> lines = Files.readAllLines(path);
        System.out.println("��ȡ�ļ�������Ϣ�洢ΪList:"+lines);

//��ȡ�ļ���ʽ��
        //Files.lines(sourcePath, Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        StringBuilder sb = new StringBuilder();
        Files.lines(path ).filter(t->{return t.length()>0;}).forEach(line -> {
                sb.append(line) ;
        });
        System.out.println(sb.toString());

    }

    /**
     * �ճ�����
     * @return void
     **/
    public static void baseTest() throws Exception {
        Path sourcePath = Paths.get("a.txt").toAbsolutePath();
        Path target = Paths.get("b.txt").toAbsolutePath();
        System.out.println("Դ�ļ�·����"+sourcePath);

        target.toFile().delete();
        // �����ļ�
        Files.move(sourcePath, target);
        Files.copy(target, new FileOutputStream("a.txt"));

        // �ж�FilesTest.java�ļ��Ƿ�Ϊ�����ļ�
        System.out.println("\nFilesTest.java�Ƿ�Ϊ�����ļ���" + Files.isHidden(sourcePath));
        // �ж�ָ���ļ��Ĵ�С
        System.out.println("\nFilesTest.java�Ĵ�СΪ��" + Files.size(sourcePath));

        System.out.println("\n��ȡ��ǰĿ¼�������ļ�����Ŀ¼");
        // ʹ��Java 8������Stream API�г���ǰĿ¼�������ļ�����Ŀ¼
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));


        // ʹ��Java 8������Stream API��ȡ�ļ�����
        Files.lines(sourcePath, Charset.forName("utf-8")).forEach(line -> System.out.println(line));

        FileStore cStore = Files.getFileStore(Paths.get("C:"));
        // �ж�C�̵��ܿռ䣬���ÿռ�
        System.out.println("C:���пռ䣺" + cStore.getTotalSpace());
        System.out.println("C:���ÿռ䣺" + cStore.getUsableSpace());
    }
}