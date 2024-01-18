package encapsulation.outer;

import encapsulation.code.Person;

/**
 * @author jiaolong
 * @date 2024/01/17 10:59
 * @description: �ڲ�ͬ���ķ���Ȩ�޲���
 *
 * private		��ǰ�����Ȩ��
 * default		������Ȩ��
 * protected	�������Ȩ��
 * public		��������Ȩ��
 */
public class DifferernPackageTest {
    public static void main(String[] args) {
        Person p = new Person();

        //1.��ͬ�����޷�����private�� û�����η��ŵ��ֶκͷ�������Person��age�ֶκ�getAge������
        // String age = p.age;
        // p.getAge();
        // String hight = p.hight;
        // p.getHight();

        /*
         * 2.��ͬ����ֻ�� ������Է���protected���ε��ֶκͷ�����
         *  ��Ȼ ManPerson�̳���Person,��man��Ȼ�޷�����Person���name,getName������
         *  protected ���ε���ͷ��������ⲿ�� ֻ��ͨ���̳�Person�������ڲ����з��ʡ�
         */

        ManPerson man = new ManPerson();
        man.testProtected();

        //3.��ͬ�������Է��� public���ε��ֶκͷ�����
        String sex = p.sex;
        p.getSex();
    }
}

class ManPerson extends Person{
    public void testProtected(){
        //ͨ���̳з��� protected���ε��ֶκͷ���
        String name = this.name;
        this.getName();
    }
}