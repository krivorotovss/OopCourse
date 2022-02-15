package ru.academits.krivorotov.shapes_main;

import ru.academits.krivorotov.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}

class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(10),
                new Square(5),
                new Triangle(1, 1, 2, 2, 3, 4),
                new Triangle(2, 2, 4, 5, 8, 7),
                new Rectangle(10, 15),
                new Rectangle(10, 11),
                new Circle(11),
        };

        Arrays.sort(shapes, new AreaComparator());
        System.out.println(("Наибольшая площадь фигуры - " + shapes[shapes.length - 1]));

        Arrays.sort(shapes, new PerimeterComparator());
        System.out.println(("Фигура со вторым по величине периметром - " + shapes[shapes.length - 2]));

        Circle circle = new Circle(5);
        circle.setRadius(10);
        System.out.println("Радиус круга = " + circle.getRadius());

        Rectangle rectangle = new Rectangle(4, 5);
        rectangle.setHeight(2);
        rectangle.setWidth(6);
        System.out.println("Периметр прямоугольника = " + rectangle.getPerimeter());

        Square square = new Square(2);
        square.setSideLength(3);
        System.out.println("Площадь квадрата = " + square.getArea());

        Triangle triangle = new Triangle(2, 2, 5, 3, 4, 6);
        triangle.setX1(3);
        triangle.setY1(3);
        triangle.setX2(1);
        triangle.setY2(0);
        triangle.setX3(5);
        triangle.setY3(5);
        System.out.println("Площадь треугольника = " + triangle.getArea());
    }
}