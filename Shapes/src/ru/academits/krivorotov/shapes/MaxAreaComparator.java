package ru.academits.krivorotov.shapes;

import java.util.Comparator;

public class MaxAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape object1, Shape object2) {
        return (int) (object1.getArea() - object2.getArea());
    }
}