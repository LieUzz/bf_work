package q1;

public class Counter implements Runnable{
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increment();
        }
    }
}
