package ru.academits.krivorotov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;
        return x1 == t.x1 && y1 == t.y1 && x2 == t.x2 && y2 == t.y2 && x3 == t.x3 && y3 == t.y3;
    }

    @Override
    public String toString() {
        return "Треугольник: координаты вершин: (" + x1 + "; " + y1 + "), (" + x2 + "; " + y2 + "), (" + x3 + "; " + y3 + "), площадь = " +
                getArea() + ", периметр = " + getPerimeter() + ", ширина = " + getWidth() + ", высота = " + getHeight();
    }

    @Override
    public double getWidth() {
        if (isStraightLine()) {
            return 0;
        }

        return getMaxNumber(x1, x2, x3) - getMinNumber(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        if (isStraightLine()) {
            return 0;
        }

        return getMaxNumber(y1, y2, y3) - getMinNumber(y1, y2, y3);
    }

    @Override
    public double getArea() {
        if (isStraightLine()) {
            return 0;
        }

        double side1Length = getSideLength(x1, y1, x2, y2);
        double side2Length = getSideLength(x1, y1, x3, y3);
        double side3Length = getSideLength(x2, y2, x3, y3);
        double halfPerimeter = (side1Length + side2Length + side3Length) / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - side1Length) * (halfPerimeter - side2Length) * (halfPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        if (isStraightLine()) {
            return 0;
        }

        return getSideLength(x1, y1, x2, y2) + getSideLength(x1, y1, x3, y3) + getSideLength(x2, y2, x3, y3);
    }

    private static double getMaxNumber(double number1, double number2, double number3) {
        return Math.max(Math.max(number1, number2), number3);
    }

    private static double getMinNumber(double number1, double number2, double number3) {
        return Math.min(Math.min(number1, number2), number3);
    }

    private boolean isStraightLine() {
        double epsilon = 1.0e-10;

        return Math.abs((y1 - y2) * (x1 - x3) - (x1 - x2) * (y1 - y3)) <= epsilon; // Точки на одной прямой
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}