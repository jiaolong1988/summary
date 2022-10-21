import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {

    public static void main(String[] args) {
        try {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream,1);

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String data = "1234567890";
                        while (true) {
                            pipedOutputStream.write(data.getBytes());
                            pipedOutputStream.flush();
                            System.out.println("pipedOutputStream.write "+data);
                            //Thread.sleep(10000);
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            });
            thread1.start();

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] data = new byte[10];
                        while (true) {
                            int hasHead = pipedInputStream.read(data);
                            System.out.println("pipedInputStream.read:"+new String(data,0,hasHead));
                            Thread.sleep(100);
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            });
            thread2.start();


        }catch (Exception e){
            System.out.println(e);
        }
    }
}
