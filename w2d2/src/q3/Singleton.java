package q3;

/**
 * Create a Singleton Class.
 * <p>And write a code to test if your singleton class work in multiThread environment?
 */
public class Singleton {
    private static volatile Singleton instance = null;
    private Singleton() {}
    public static Singleton getInstance() {
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance==null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
