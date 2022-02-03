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

    public Range getIntersection(Range object2) {
        if (this.from < object2.from && this.to > object2.to || this.from > object2.from && this.to < object2.to) { // проверка, если 1 интервал входит во второй целиком
            double newFrom = Math.max(this.from, object2.from);
            double newTo = Math.min(this.to, object2.to);

            return new Range(newFrom, newTo);
        }

        if (this.from <= object2.from && this.to <= object2.to || this.from >= object2.from && this.to >= object2.to) {
            if (this.from <= this.to && this.to == object2.from || object2.from <= object2.to && this.from == object2.to) { // касание в 1 точке
                return null;
            }

            if (this.to < object2.from || this.from > object2.to) { // интервалы не пересекаются
                return null;
            }

            if (this.from <= object2.from) {
                return new Range(object2.from, this.to);
            } else {
                return new Range(this.from, object2.to);
            }
        }

        return null;
    }

    public Range[] getUnification(Range object2) {
        if (this.to < object2.from || this.from > object2.to) { // интервалы не пересекаются
            Range[] array = new Range[2];
            array[0] = this;
            array[1] = object2;

            return array;
        } else {
            double newFrom = Math.min(this.from, object2.from);
            double newTo = Math.max(this.to, object2.to);

            return new Range[]{new Range(newFrom, newTo)};
        }
    }

    public Range[] getDifference(Range object2) {
        if (this.to <= object2.from || this.from >= object2.to) { // нет пересечения, в остальных случаях есть пересечение или касание
            return new Range[]{new Range(this.from, this.to)};
        } else if (this.from >= object2.from && this.to <= object2.to) {
            return new Range[]{};
        } else if (this.from < object2.from && this.to <= object2.to) {
            return new Range[]{new Range(this.from, object2.from)};
        } else if (this.from < object2.from) {
            Range range1 = new Range(this.from, object2.from);
            Range range2 = new Range(object2.to, this.to);
            Range[] array = new Range[2];
            array[0] = range1;
            array[1] = range2;

            return array;
        }

        return new Range[]{new Range(object2.to, this.to)};
    }
}