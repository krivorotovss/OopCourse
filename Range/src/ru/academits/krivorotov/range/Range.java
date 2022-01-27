package ru.academits.krivorotov.range;

import java.util.Arrays;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public boolean isInside(double from, double to) {
        return getFrom() >= from && getTo() <= to;
    }

    public void getIntersection(double from, double to) {
        if (getFrom() >= from && getTo() <= to) {
            System.out.println(defineIntersection(from, to));
            return;
        }

        if ((getFrom() == to && getTo() != from) || (getFrom() != to && getTo() == from)) {
            System.out.println("null");
            return;
        }

        System.out.println(defineIntersection(from, to));
    }

    public void getAddition(double from, double to) {
        if (!("null".equals(defineIntersection(from, to)))) {
            double minFrom = getMinNumber(getFrom(), from);
            double maxTo = getMaxNumber(getTo(), to);

            System.out.println("Объединенный интервал " + minFrom + ", " + maxTo);
        } else {
            System.out.print("Нет пересечения, 2 интервала: ");
            getArray(from, to);
        }
    }

    public double getMaxNumber(double number1, double number2) {
        return Math.max(number1, number2);
    }

    public double getMinNumber(double number1, double number2) {
        return Math.min(number1, number2);
    }

    public String defineIntersection(double from, double to) {
        int countEntriesInInterval = 0;
        double newFrom = getFrom();
        double newTo = getTo();

        if (getFrom() >= from && getTo() <= to) { // проверка, если 1 интервал входит во второй целиком
            if (isInside(from, to)) {
                newFrom = getMaxNumber(getFrom(), from);
                newTo = getMinNumber(getTo(), to);

                return "Значения пересечения интервалов: " + newFrom + ", " + newTo;
            }
        }

        if (isInside(from)) {
            newFrom = from;
            countEntriesInInterval++;
        }

        if (isInside(to)) {
            newTo = to;
            countEntriesInInterval++;
        }

        if (countEntriesInInterval != 0) {
            return "Значения пересечения интервалов: " + newFrom + ", " + newTo;
        }

        return "null";
    }

    public void getArray(double from, double to) {
        if (getFrom() < from && getTo() > to) {
            double[][] ranges = {{getFrom(), from}, {to, getTo()}};
            System.out.println(Arrays.deepToString(ranges));
            return;
        }

        double[][] ranges = {{getFrom(), getTo()}, {from, to}};
        System.out.println(Arrays.deepToString(ranges));
    }

    public void getSubtraction(double from, double to) {
        if (!("null".equals(defineIntersection(from, to)))) { // есть пересечение
            if (getFrom() >= from && getTo() <= to) { // интервалы равны или первый содержится во втором
                System.out.println("Разность интервалов равна - 0");
            }

            if (getTo() == from || getFrom() == to) { // интервалы касаются в 1 точке
                System.out.print("Нет пересечения, 2 интервала: ");
                getArray(from, to);
            }

            if (getFrom() < from && getTo() <= to) {
                System.out.println("Разность интервалов равна = " + getFrom() + ", " + from);
            }

            if (getFrom() < from && getTo() > to) {
                System.out.print("Разность интервалов равна 2-м интервалам: ");
                getArray(from, to);
            }

            if (getFrom() < to && getTo() > to) {
                System.out.println("Разность интервалов равна = " + to + ", " + getTo());
            }

        } else { // нет пересечения
            System.out.print("Нет пересечения, 2 интервала: ");
            getArray(from, to);
        }
    }
}