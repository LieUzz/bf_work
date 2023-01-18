/**
 * create an abstract class named shape that contains two integers and an empty method named printArea.
 * <p>Provide three classes named Rectangle,Triangle and Circle subclass that each one of the classes extends the Class Shape.
 * <p>Each one of the classes contains only the method printArea() that prints the area of Shape.
 */
abstract class Shape {
    int a;
    int b;
    // method with no provided implementation
    abstract void printArea();
}

/*
    I don't know what is the relationship between two integers and area.
    I guess for Rectangle, area = a * b;
            for Triangle, area = a * b / 2;
            for Circle, area = pi * (a/2) ^ 2;
    But for the question description, it is not clear.
    Thus, I just use xxx represent the area value.
 */

class Rectangle extends Shape {

    @Override
    void printArea() {
        System.out.println("Rectangle's area is xxx");
    }
}

class Triangle extends Shape {

    @Override
    void printArea() {
        System.out.println("Triangle's area is xxx");
    }
}

class Circle extends Shape {

    @Override
    void printArea() {
        System.out.println("Circle's area is xxx");
    }
}