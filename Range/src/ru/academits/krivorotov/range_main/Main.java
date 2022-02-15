package ru.academits.krivorotov.range_main;

import ru.academits.krivorotov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(2.5, 18.2);
        Range range2 = new Range(1.2, 11.2);

        System.out.println("Пересечение диапазонов: " + range1.getIntersection(range2));

        System.out.println("Объединение диапазонов: " + Arrays.toString(range1.getUnion(range2)));

        System.out.println("Разность диапазонов: " + Arrays.toString(range1.getDifference(range2)));

        double number = 10;
        
        if (range1.isInside(number)) {
            System.out.println("Число " + number + " попадает в диапазон range1");
        } else {
            System.out.println("Число " + number + " не попадает в диапазон range1");
        }

        range1.setFrom(5);
        range1.setTo(18);

        System.out.println("Новые значения: from = " + range1.getFrom() + ", to = " + range1.getTo());
        System.out.printf("Новая длина диапазона чисел = %.2f%n", range1.getLength());
    }
}