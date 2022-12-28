import javax.xml.bind.JAXB;
import java.io.File;

public class JlTest {
    public static void main(String[] args) {
      Jiaolong jl = new Jiaolong();
     // jl.jl="jiaolong";
     jl.setJl("jiaolong");
      jl.setR_OBJECT_ID("111");

      File file = new File("D:\\jiaolong.xml");
      JAXB.marshal(jl, file);
    }
}
