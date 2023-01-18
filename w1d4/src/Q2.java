import java.util.*;
import java.util.stream.Collectors;

public class Q2 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();

        list.add(new Person("Alice", 10));
        list.add(new Person("Bob", 15));
        list.add(new Person("Jack", 14));
        list.add(new Person("John", 12));
        list.add(new Person("Zoe", 13));

        System.out.println(printList(filterJ(list)));
        System.out.println(printList(sortAge(list)));
    }

    /**
     * Filter out all person whose name start with "J"
     * @param list
     * @return
     */
    public static List<Person> filterJ(List<Person> list) {
        return list.stream().filter(p ->p.name.startsWith("J")).collect(Collectors.toList());
    }

    /**
     * Sort all person according to their age in descending order.
     * @param list
     * @return
     */
    public static List<Person> sortAge(List<Person> list) {
        return list.stream().sorted((a, b) -> b.age - a.age).collect(Collectors.toList());
    }

    public static String printList(List<Person> list) {
        StringBuilder str = new StringBuilder();
        for (Person p : list) {
            str.append(p.name).append("-").append(p.age).append(";");
        }
        return str.toString();
    }

    static class Person {
        public String name;
        public int age;

        Person() {}
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


}



