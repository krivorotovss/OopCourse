package ru.academits.krivorotov.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "Треугольник: площадь = " + getArea() + ", периметр = " + getPerimeter() + ", ширина = " + getWidth() + ", высота = " + getHeight();
    }

    @Override
    public double getWidth() {
        if (checkStraightLine(x1, y1, x2, y2, x3, y3) == 0) {
            return 0;
        }

        double maxNumber = findMaxNumber(x1, x2, x3);
        double minNumber = findMinNumber(x1, x2, x3);

        return maxNumber - minNumber;
    }

    @Override
    public double getHeight() {
        if (checkStraightLine(x1, y1, x2, y2, x3, y3) == 0) {
            return 0;
        }

        double maxNumber = findMaxNumber(y1, y2, y3);
        double minNumber = findMinNumber(y1, y2, y3);

        return maxNumber - minNumber;
    }

    @Override
    public double getArea() {
        if (checkStraightLine(x1, y1, x2, y2, x3, y3) == 0) {
            return 0;
        }

        double side1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double halfPerimeter = (side1Length + side2Length + side3Length) / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - side1Length) * (halfPerimeter - side2Length) * (halfPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        if (checkStraightLine(x1, y1, x2, y2, x3, y3) == 0) {
            return 0;
        }

        double side1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        return side1Length + side2Length + side3Length;
    }

    public static double findMaxNumber(double number1, double number2, double number3) {
        double maxNumber;

        if (number1 >= number2) {
            maxNumber = Math.max(number1, number3);
        } else {
            maxNumber = Math.max(number2, number3);
        }

        return maxNumber;
    }

    public static double findMinNumber(double number1, double number2, double number3) {
        double minNumber;

        if (number1 >= number2) {
            minNumber = Math.min(number3, number2);
        } else {
            minNumber = Math.min(number1, number3);
        }

        return minNumber;
    }

    public static double checkStraightLine(double x1, double y1, double x2, double y2, double x3, double y3) {
        double epsilon = 1.0e-10;

        if (Math.abs((y1 - y2) * (x1 - x3) - (x1 - x2) * (y1 - y3)) <= epsilon) { // Точки на одной прямой
            return 0;
        }

        return 1;
    }
}