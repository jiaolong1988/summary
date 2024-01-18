package temp;
/**
 * @author jiaolong
 * @date 2024-1-18 14:46
 * 先执行 父类构造器，在执行子类构造器
 */
class Creature{
    public Creature(){
        System.out.println("Creature生物 无参数的构造器");
    }
}

//动物
class Animal extends Creature{
    public Animal(String name){
        System.out.println("Animal带一个参数的构造器，"+ "该动物的name为" + name);
    }

    public Animal(String name , int age){
        // 使用this调用同一个重载的构造器
        this(name);
        System.out.println("Animal带两个参数的构造器，"+ "其age为" + age);
    }
}

//狼
public class Wolf extends Animal{
    public Wolf(){
        // 显式调用父类有两个参数的构造器
        super("灰太狼", 3);
        System.out.println("Wolf狼 无参数的构造器");
    }

    public static void main(String[] args){
        new Wolf();
        
      /*输出结果：
        Creature无参数的构造器
        Animal带一个参数的构造器，该动物的name为灰太狼
        Animal带两个参数的构造器，其age为3
        Wolf无参数的构造器
       */
    }
}
