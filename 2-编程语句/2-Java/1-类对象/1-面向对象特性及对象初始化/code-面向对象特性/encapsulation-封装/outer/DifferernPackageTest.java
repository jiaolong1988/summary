package encapsulation.outer;

import encapsulation.code.Person;

/**
 * @author jiaolong
 * @date 2024/01/17 10:59
 * @description: 在不同包的访问权限测试
 *
 * private		当前类访问权限
 * default		包访问权限
 * protected	子类访问权限
 * public		公共访问权限
 */
public class DifferernPackageTest {
    public static void main(String[] args) {
        Person p = new Person();

        //1.不同包，无法访问private和 没有修饰符号的字段和方法。如Person的age字段和getAge方法。
        // String age = p.age;
        // p.getAge();
        // String hight = p.hight;
        // p.getHight();

        /*
         * 2.不同包，只有 子类可以访问protected修饰的字段和方法。
         *  虽然 ManPerson继承了Person,但man仍然无法访问Person类的name,getName方法。
         *  protected 修饰的类和方法，在外部包 只能通过继承Person的子类内部进行访问。
         */

        ManPerson man = new ManPerson();
        man.testProtected();

        //3.不同包，可以访问 public修饰的字段和方法。
        String sex = p.sex;
        p.getSex();
    }
}

class ManPerson extends Person{
    public void testProtected(){
        //通过继承访问 protected修饰的字段和烦啊
        String name = this.name;
        this.getName();
    }
}