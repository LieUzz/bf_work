package q2;

/**
 * Come up with a synchronization mechanism
 * <p>(other than using synchronized keyword) to make the Counter work as expected.
 */
public class CouterThreadSynchronization {

    public static void main(String[] args) throws InterruptedException {
        for (int i =0; i < 5; i++) {
            CounterWithSynchronization counter = new CounterWithSynchronization();
            Thread t1 = new Thread(counter);
            Thread t2 = new Thread(counter);
            t1.start();

            t2.start();

            Thread.sleep(1000);
            System.out.println(counter.getCount());
        }
    }
}
