import java.util.Objects;

/**
 * Design and implement your own HashMap which should support linked list structure
 */
public class Q3 {
    public static void main(String[] args) {

        Person p1 = new Person("Bob", "Smith");
        Person p2 = new Person("Alice", "Williams");

        MyHashMap<Person, Integer> map = new MyHashMap<>();

        map.put(p1, 1);
        System.out.println(map.get(p1));
        map.put(p2, 2);
        map.put(p1, 3);
        System.out.println(map.get(p1));
        map.remove(p1);
        System.out.println(map.get(p1));
    }
}

class MyHashMap<K,V> {

    private final MyLinkedList<Pair<K, V>>[] table;
    private final int capacity = 10;

    static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new MyLinkedList[capacity];
    }

    public void put(K newKey, V data) {
        if (newKey == null)
            return;    //does not allow to store null.

        //calculate hash of key.
        int hash = hashCode(newKey);

        Pair<K, V> newPair = new Pair<>(newKey, data);
        if (table[hash] == null) {
            table[hash] = new MyLinkedList<>();
            table[hash].add(newPair);
        } else {
            MyLinkedList<Pair<K, V>> list = table[hash];
            boolean isAdd = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(newKey)) {
                    list.get(i).value = data;
                    isAdd = true;
                    break;
                }
            }
            if (!isAdd) {
                list.add(newPair);
            }
        }
    }

    public V get(K key) {
        int hash = hashCode(key);
        if (table[hash] == null) {
            return null;
        } else {
            MyLinkedList<Pair<K, V>> list = table[hash];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(key)) {
                    return list.get(i).value;
                }
            }
            return null;   //returns null if key is not found.
        }
    }


    public boolean remove(K deleteKey) {

        int hash = hashCode(deleteKey);
        if (table[hash] == null) {
            return false;
        } else {
            MyLinkedList<Pair<K, V>> list = table[hash];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(deleteKey)) {
                    list.remove(list.get(i));
                    return true;
                }
            }
            return false;
        }

    }

    private int hashCode(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

}


class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}


class MyLinkedList<E> {
    private int size = 0;
    Node<E> first;
    Node<E> last;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node<E> x) {
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public E get(int index) {

        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x.item;

    }

}
