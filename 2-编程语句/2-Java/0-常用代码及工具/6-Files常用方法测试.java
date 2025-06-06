
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;

/**
 * Files类日常使用方法
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
     * 字符串文件读写
     * @return void
     **/
    public static void readWriteStringTest() throws Exception {
        Path writeStringPath= Paths.get("writeString.txt");
        // 不追加-自动创建文件
        Files.write(writeStringPath, "15".getBytes(StandardCharsets.UTF_8));

        // 追加-不自动创建文件
        Files.write(writeStringPath, "16".getBytes(), StandardOpenOption.APPEND);


        String content = new String(Files.readAllBytes(writeStringPath), StandardCharsets.UTF_8);
        System.out.println("读取文件中的字符串："+content);
    }

    /**
     * 集合文件读写
     * @return
     **/
    public static void readWriteListTest() throws Exception {
        Path path = Paths.get("pome.txt");
//写入文件
        List<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼跃");
        poem.add("清徐风中碧竿横");
        poem.add("");
        // 直接将多个字符串内容写入指定文件中
        //Path writeFlag = Files.write(Paths.get("pome.txt"), poem, Charset.forName("gbk"));
        Path writeFlag = Files.write(path, poem);
        System.out.println("文件写入成功,写入路径："+writeFlag.toAbsolutePath());

//读取文件方式一
        // List<String> lines = Files.readAllLines(sourcePath, Charset.forName("utf-8"));
        List<String> lines = Files.readAllLines(path);
        System.out.println("读取文件所有信息存储为List:"+lines);

//读取文件方式二
        //Files.lines(sourcePath, Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        StringBuilder sb = new StringBuilder();
        Files.lines(path ).filter(t->{return t.length()>0;}).forEach(line -> {
                sb.append(line) ;
        });
        System.out.println(sb.toString());

    }

    /**
     * 日常操作
     * @return void
     **/
    public static void baseTest() throws Exception {
        Path sourcePath = Paths.get("a.txt").toAbsolutePath();
        Path target = Paths.get("b.txt").toAbsolutePath();
        System.out.println("源文件路径："+sourcePath);

        target.toFile().delete();
        // 复制文件
        Files.move(sourcePath, target);
        Files.copy(target, new FileOutputStream("a.txt"));

        // 判断FilesTest.java文件是否为隐藏文件
        System.out.println("\nFilesTest.java是否为隐藏文件：" + Files.isHidden(sourcePath));
        // 判断指定文件的大小
        System.out.println("\nFilesTest.java的大小为：" + Files.size(sourcePath));

        System.out.println("\n读取当前目录下所有文件和子目录");
        // 使用Java 8新增的Stream API列出当前目录下所有文件和子目录
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));


        // 使用Java 8新增的Stream API读取文件内容
        Files.lines(sourcePath, Charset.forName("utf-8")).forEach(line -> System.out.println(line));

        FileStore cStore = Files.getFileStore(Paths.get("C:"));
        // 判断C盘的总空间，可用空间
        System.out.println("C:共有空间：" + cStore.getTotalSpace());
        System.out.println("C:可用空间：" + cStore.getUsableSpace());
    }
}