package ru.academits.krivorotov.shapes;

import java.util.Objects;

public class Square implements Shape {
    double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength);
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "Квадрат: площадь = " + getArea() + ", периметр = " + getPerimeter() + ", сторона = " + getWidth();
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return (sideLength + sideLength) * 2;
    }
}