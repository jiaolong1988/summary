package thread;

/**
 *  每个线程都有独立的变量副本。线程不销毁，threadlocal的变量会一直存在。
 * @author: jiaolong
 * @date: 2024/06/20 15:07
 **/
public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        // 启动两条线程，
        // 两条线程共享同一个Account
        Account at = new Account("初始名");
		/*
		虽然两条线程共享同一个账户，即只有一个账户名
		但由于账户名是ThreadLocal类型的，所以每条线程
		都完全拥有各自的账户名副本，所以从i == 6之后，将看到两条
		线程访问同一个账户时看到不同的账户名。
		*/
        MyTest t1 = new MyTest(at, "线程甲");
        t1.start();
        MyTest t2 = new MyTest(at, "线程乙");
        t2.start();

    }
}

class Account {
    /* 定义一个ThreadLocal类型的变量，该变量将是一个线程局部变量
    每个线程都会保留该变量的一个副本 */
    private ThreadLocal<String> name = new ThreadLocal<>();

    // 定义一个初始化name成员变量的构造器
    public Account(String str) {
        this.name.set(str);
        // 下面代码用于访问当前线程的name副本的值
        System.out.println("---初始化：" + this.name.get());
    }

    // name的setter和getter方法
    public String getName() {
        return name.get();
    }
    public ThreadLocal<String> getThredLocal(){
        return name;
    }

    public void setName(String str) {
        this.name.set(str);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name=" + name +
                '}';
    }
}

class MyTest extends Thread {
    // 定义一个Account类型的成员变量
    private Account account;

    public MyTest(Account account, String name) {
        super(name);
        this.account = account;
    }

    public void run() {
        // 循环10次
        for (int i = 0; i < 10; i++) {
            // 当i == 6时输出将账户名替换成当前线程名
            if (i == 6) {
                account.setName(getName());
            }
            // 输出同一个账户的账户名和循环变量
            System.out.println(Thread.currentThread().getName()+" --> 本地变量："+account.getName() + " 账户的i值：" + i);
        }
        System.out.println(Thread.currentThread().getName()+" -------> 线程即将结束："+account.getName());
    }
}