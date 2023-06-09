package tt;

 interface InterfaceA {
    //定义静态常量
   //public static final int a  = 5;

   // public static final 关键字系统会自动补齐
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

        // 接口的引用类型可指向实现该接口的实例
        InterfaceC interfacec =  new Inital();

        //打印类型
        System.out.println(interfacec.getClass());

        //通过接口访问 InterfaceC 中的常量  a,b,c。其中 a,b是接口InterfaceC从父接口获得
        System.out.println(InterfaceC.a);
        System.out.println(InterfaceC.b);
        System.out.println(InterfaceC.c);


    }

}