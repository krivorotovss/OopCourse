package ru.academits.krivorotov.range_main;

import ru.academits.krivorotov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(2.5, 10.2);
        Range range2 = new Range(1.2, 11.2);

        System.out.print("1) ");
        range1.getIntersection(range2.getFrom(), range2.getTo());

        System.out.print("2) ");
        range1.getAddition(range2.getFrom(), range2.getTo());

        System.out.print("3) ");
        range1.getSubtraction(range2.getFrom(), range2.getTo());

        range1.setFrom(5);
        range1.setTo(18);

        System.out.println("Новые значения: from = " + range1.getFrom() + ", to = " + range1.getTo());
        System.out.printf("Новая длина диапазона чисел = %.2f%n", range1.getLength());
    }
}