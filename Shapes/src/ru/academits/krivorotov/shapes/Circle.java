package ru.academits.krivorotov.shapes;

import java.util.Objects;

public class Circle implements Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "Круг: площадь = " + getArea() + ", периметр = " + getPerimeter() + ", диаметр = " + getWidth();
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return (Math.PI * radius * radius) / 2;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }
}