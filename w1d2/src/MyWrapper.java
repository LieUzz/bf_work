import java.util.Objects;

/**
 *  My owm wrapper, which is similar as Integer.
 *  It only has basic methods like
 *  intValue(), longValue(), floatValue(), doubleValue(), equals(), hashCode();
 */
public final class MyWrapper extends Number {
    private final int value;
    public MyWrapper() {
        this.value = 0;
    }
    public MyWrapper(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long)value;
    }

    @Override
    public float floatValue() {
        return (float)value;
    }

    @Override
    public double doubleValue() {
        return (double)value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyWrapper myWrapper = (MyWrapper) o;
        return value == myWrapper.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
