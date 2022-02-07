package ru.academits.krivorotov.range;

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

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }

    public Range getIntersection(Range range) {
        if (range.from >= to || from >= range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (to < range.from || from > range.to) { // интервалы не пересекаются
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (to <= range.from || from >= range.to) { // нет пересечения, в остальных случаях есть пересечение или касание
            return new Range[]{new Range(from, to)};
        } else if (from >= range.from && to <= range.to) {
            return new Range[]{};
        } else if (from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        } else if (from < range.from) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        return new Range[]{new Range(range.to, to)};
    }
}