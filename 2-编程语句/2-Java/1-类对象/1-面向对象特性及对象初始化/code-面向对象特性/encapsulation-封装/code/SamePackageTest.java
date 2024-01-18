package encapsulation.code;

/**
 * @author jiaolong
 * @date 2024/01/17 11:16
 * @description: 相同包访问权限测试
 *
 * private		当前类访问权限
 * default		包访问权限
 * protected	子类访问权限
 * public		公共访问权限
 */
public class SamePackageTest {
    public static void main(String[] args) {
        Person p = new Person();

        //1.同一个包，无法访问private修饰的字段和方法。如Person的age字段和getAge方法。
        // String age = p.age;
        // p.getAge();

        //2.同一个包，可以访问 无修饰的 字段和方法。
        String hight = p.hight;
        p.getHight();


        //3.同一个包 可以访问 protected修饰的字段和方法。
        String nanem = p.name;
        p.getName();

        //4.同一个包，可以访问 public修饰的字段和方法。
        String sex  = p.sex;
        p.getSex();

    }
}
