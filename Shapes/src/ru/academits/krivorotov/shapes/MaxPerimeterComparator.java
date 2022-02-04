package ru.academits.krivorotov.shapes;

import java.util.Comparator;

public class MaxPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape object1, Shape object2) {
        return (int) (object1.getPerimeter() - object2.getPerimeter());
    }
}