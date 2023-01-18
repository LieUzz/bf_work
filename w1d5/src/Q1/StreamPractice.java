package Q1;

import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {


        List<Employee> list = new ArrayList<>();
        Employee p1 = new Employee("Shawn", 27, 22000);
        Employee p2 = new Employee("Bob", 23, 15000);
        Employee p3 = new Employee("Tom", 25, 23500);
        Employee p4 = new Employee("Alice", 31, 30000);
        Employee p5 = new Employee("XXX", 33, 30000);
        Employee p6 = new Employee("XXZ", 33, 30000);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        avgSalary(list);
        System.out.println();

        filterAge(list);
        System.out.println();

        mapEmployee(list);
        System.out.println();

        findNameStartWithX(list);
        System.out.println();

        sortSalary(list);
        System.out.println();

    }

    /**
     * Calculate the average salary of all the employees and print the salary
     * @param list list of employees;
     */
    public static void avgSalary(List<Employee> list) {
        list.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .ifPresent(value -> System.out.println("The average salary of all the employees is $" + value));

    }
    /**
     * Filter for employees whose age is greater than 30 and print out their names
     * @param list list of employees;
     */
    public static void filterAge(List<Employee> list) {
        list.stream().filter(e -> e.getAge() > 30).map(Employee::getName).forEach(System.out::println);
    }

    /**
     * Collect a Map of employees where the key is the salary of employee (Integer)
     * and the value is a list of employees (Employee)
     * - Employees who have the same salary should belong to the same list with the matching salary key
     * @param list list of employees;
     */
    public static void mapEmployee(List<Employee> list) {

        Map<Integer, List<Employee>> map = list.stream()
                .collect(Collectors.groupingBy(Employee::getSalary));

        for (Integer key : map.keySet()) {
            System.out.println(key + map.get(key).toString());
        }
    }

    /**
     * Find any one employee whose name starts with the character X
     * - If such an employee exists then print out the name, if
     * not then print “no such employee exists”
     * @param list list of employees;
     */

    public static void findNameStartWithX(List<Employee> list) {
        Optional<String> opt = list.stream()
                .map(Employee::getName)
                .filter(name -> name.startsWith("X"))
                .findAny();

        if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {
            System.out.println("no such employee exists");
        }
    }

    /**
     * Sort the list of Employees by salary in descending order and print the list
     */
    public static void sortSalary(List<Employee> list) {
        list.stream().sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList()).forEach(System.out::println);
    }


}
