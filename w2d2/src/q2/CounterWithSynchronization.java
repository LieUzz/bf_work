package q2;
import java.util.concurrent.atomic.AtomicInteger;
public class CounterWithSynchronization implements Runnable{
    private final AtomicInteger count = new AtomicInteger(0);

    public int getCount() {
        return count.get();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }
}
