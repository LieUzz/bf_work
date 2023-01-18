package q1;

/**
 * Create a program to reproduce the Counter Thread interference issue,
 * <p>run several times and explain the result.
 */
public class CounterThreadInterference {
    public static void main(String[] args) throws InterruptedException {
        for (int i =0; i < 5; i++) {
            Counter counter = new Counter();
            Thread t1 = new Thread(counter);
            Thread t2 = new Thread(counter);
            t1.start();
            t2.start();
            Thread.sleep(1000);
            System.out.println(counter.getCount());
        }
    }
}


