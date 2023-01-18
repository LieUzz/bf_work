package q4;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class driver {
    public static void main(String args[]){

        Lock key1 = new ReentrantLock();
        Lock key2 = new ReentrantLock();

        Thread t8 = new Thread( () -> {
            try {
                if (key1.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("t8 has key 1.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (key2.tryLock(5, TimeUnit.SECONDS))  {
                        System.out.println("t8 has key 2"); }
            }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t9 = new Thread( () -> {
            try {
                if (key2.tryLock(5, TimeUnit.SECONDS))  {
                    System.out.println("t9 has key 2.");
                    if (key1.tryLock(5, TimeUnit.SECONDS)) {
                        System.out.println("t9 has key 1");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t8.start();
        t9.start();
    }
}


