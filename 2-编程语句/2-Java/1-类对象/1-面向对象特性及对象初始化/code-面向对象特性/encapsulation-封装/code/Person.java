package encapsulation.code;

/**
 * @author jiaolong
 * @date 2024/01/17 10:57
 * @description: TODO
 */
public class Person {

    private String age;
    String hight;
    protected String name;
    public String sex;

    private void getAge(){
        System.out.println("age...");
    }
    void getHight(){
        System.out.println("hight...");
    }
    protected void getName() {
        System.out.println("name...");
    }
    public void getSex(){
        System.out.println("sex...");
    }

}
