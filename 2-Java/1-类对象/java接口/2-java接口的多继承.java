package tt;

 interface InterfaceA {
    //���徲̬����
   //public static final int a  = 5;

   // public static final �ؼ���ϵͳ���Զ�����
   int a = 2;

}


 interface InterfaceB {

    int b = 2;
}

 interface InterfaceC extends InterfaceB, InterfaceA {

    int c = 5;
}


//https://blog.csdn.net/qq_18505715/article/details/72802984
public class Inital implements InterfaceC{

    public static void main(String[] args) {

        // �ӿڵ��������Ϳ�ָ��ʵ�ָýӿڵ�ʵ��
        InterfaceC interfacec =  new Inital();

        //��ӡ����
        System.out.println(interfacec.getClass());

        //ͨ���ӿڷ��� InterfaceC �еĳ���  a,b,c������ a,b�ǽӿ�InterfaceC�Ӹ��ӿڻ��
        System.out.println(InterfaceC.a);
        System.out.println(InterfaceC.b);
        System.out.println(InterfaceC.c);


    }

}