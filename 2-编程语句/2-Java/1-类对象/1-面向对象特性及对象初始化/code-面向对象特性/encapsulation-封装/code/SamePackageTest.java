package encapsulation.code;

/**
 * @author jiaolong
 * @date 2024/01/17 11:16
 * @description: ��ͬ������Ȩ�޲���
 *
 * private		��ǰ�����Ȩ��
 * default		������Ȩ��
 * protected	�������Ȩ��
 * public		��������Ȩ��
 */
public class SamePackageTest {
    public static void main(String[] args) {
        Person p = new Person();

        //1.ͬһ�������޷�����private���ε��ֶκͷ�������Person��age�ֶκ�getAge������
        // String age = p.age;
        // p.getAge();

        //2.ͬһ���������Է��� �����ε� �ֶκͷ�����
        String hight = p.hight;
        p.getHight();


        //3.ͬһ���� ���Է��� protected���ε��ֶκͷ�����
        String nanem = p.name;
        p.getName();

        //4.ͬһ���������Է��� public���ε��ֶκͷ�����
        String sex  = p.sex;
        p.getSex();

    }
}
