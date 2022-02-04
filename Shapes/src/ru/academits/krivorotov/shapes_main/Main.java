package ru.academits.krivorotov.shapes_main;

import ru.academits.krivorotov.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(10);
        Square square2 = new Square(5);
        Triangle triangle1 = new Triangle(1, 2, 5, 5, 3, 7);
        Triangle triangle2 = new Triangle(2, 2, 4, 5, 8, 7);
        Rectangle rectangle1 = new Rectangle(10, 15);
        Rectangle rectangle2 = new Rectangle(10, 11);
        Circle circle1 = new Circle(11);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1};

        Comparator<Shape> AreaComparator = new MaxAreaComparator();
        Arrays.sort(shapes, AreaComparator);

        for (int i = 0; i <= shapes.length - 1; i++) {
            if (i == shapes.length - 1) {
                System.out.println("Наибольшая площадь фигуры - " + shapes[i]);
            }
        }

        Comparator<Shape> PerimeterComparator = new MaxPerimeterComparator();
        Arrays.sort(shapes, PerimeterComparator);

        for (int i = 0; i <= shapes.length - 1; i++) {
            if (i == shapes.length - 2) {
                System.out.println("Фигура со вторым по величине периметром - " + shapes[i]);
            }
        }
    }
}