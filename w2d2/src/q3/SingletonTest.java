package q3;
import java.util.*;

public class SingletonTest {
    public static void main(String[] args) throws InterruptedException  {
        List<Singleton> list = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Singleton s = Singleton.getInstance();
            Thread t = new Thread(() -> list.add(s));
            threads.add(t);
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            threads.get(i).join();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
