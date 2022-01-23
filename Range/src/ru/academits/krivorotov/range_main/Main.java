package ru.academits.krivorotov.range_main;

import ru.academits.krivorotov.range.Range;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите вещественное число:");
        double userNumber = scanner.nextDouble();

        double from = 2.5;
        double to = 10.2;

        Range range = new Range(from, to);

        if (range.isInside(userNumber)) {
            System.out.printf("Число %.2f попадает в указанный диапазон%n", userNumber);
        } else {
            System.out.println("Число вне диапазона");
            return;
        }

        System.out.printf("Длина диапазона чисел = %.2f%n", range.getLength());

        from = 5;
        to = 18;

        range.setFrom(from);
        range.setTo(to);

        System.out.println("Новые значения: from = " + range.getFrom() + ", to = " + range.getTo());

        System.out.printf("Новая длина диапазона чисел = %.2f%n", range.getLength());
    }
}