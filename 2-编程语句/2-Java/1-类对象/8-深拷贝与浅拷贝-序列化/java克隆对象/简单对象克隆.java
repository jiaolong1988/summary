package designPatterns.Prototype;

/**
 * @author jiaolong
 * @date 2024/01/16 15:31
 * @description: ԭ��ģʽ
 * ��¡���� ����ʵ��Cloneable�ӿڣ���ʵ�� Object���clone������
 *
 * ��������
 * ��ȡԪ�������¡�������Ϣ��
 * pro1:chenglong
 * pro2:chenglong
 *
 * ���ÿ�¡�������Ϣ����ԭ������бȽϣ�
 * pro1:chenglong
 * pro2:jiaolong
 */
public class PrototypePatternTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        ConcretePrototype pro1 = new ConcretePrototype();
        pro1.setName("chenglong");
        ConcretePrototype pro2 = (ConcretePrototype) pro1.clone();

        System.out.println("��ȡԪ�������¡�������Ϣ��");
        System.out.println("pro1:"+pro1.getName());
        System.out.println("pro2:"+pro2.getName());

        System.out.println("\n���ÿ�¡�������Ϣ����ԭ������бȽϣ�");
        pro2.setName("jiaolong");
        System.out.println("pro1:"+pro1.getName());
        System.out.println("pro2:"+pro2.getName());

    }
}

/**
 * @author jiaolong
 * @date 2024-1-17 15:54
 * ��¡�������ʵ��Cloneable�ӿڣ���ʵ�� Object���clone����
 */
class Prototype implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class ConcretePrototype extends Prototype {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}


