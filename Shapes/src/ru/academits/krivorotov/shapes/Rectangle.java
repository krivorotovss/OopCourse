package ru.academits.krivorotov.shapes;

import java.util.Objects;

public class Rectangle implements Shape {
    double sideLength1;
    double sideLength2;

    public Rectangle(double sideLength1, double sideLength2) {
        this.sideLength1 = sideLength1;
        this.sideLength2 = sideLength2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength1, sideLength2);
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "Прямоугольник: площадь = " + getArea() + ", периметр = " + getPerimeter() + ", ширина = " + getWidth() + ", высота = " + getHeight();
    }

    @Override
    public double getWidth() {
        return sideLength1;
    }

    @Override
    public double getHeight() {
        return sideLength2;
    }

    @Override
    public double getArea() {
        return sideLength1 * sideLength2;
    }

    @Override
    public double getPerimeter() {
        return (sideLength1 + sideLength2) * 2;
    }
}