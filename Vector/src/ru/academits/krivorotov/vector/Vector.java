package ru.academits.krivorotov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorComponents;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Значение размерности (size) <= 0");
        }

        vectorComponents = new double[size];
    }

    public Vector(Vector vector) {
        if (vector.vectorComponents.length <= 0) {
            throw new IllegalArgumentException("Значение размерности (vector.vectorComponents.length) <= 0");
        }

        this.vectorComponents = Arrays.copyOf(vector.vectorComponents, vector.vectorComponents.length);
    }

    public Vector(double[] vectorComponents) {
        if (vectorComponents.length <= 0) {
            throw new IllegalArgumentException("Значение размерности (vectorComponents.length) <= 0");
        }

        this.vectorComponents = Arrays.copyOf(vectorComponents, vectorComponents.length);
    }

    public Vector(int size, double[] vectorComponents) {
        if (size <= 0 || vectorComponents.length <= 0) {
            throw new IllegalArgumentException("Значение размерности (size || vectorComponents.length) <= 0");
        }

        this.vectorComponents = new double[size];

        for (int i = 0; i < vectorComponents.length; i++) {
            if (this.vectorComponents[i] != vectorComponents[i]) {
                this.vectorComponents[i] = vectorComponents[i];
            } else {
                this.vectorComponents[i] = 0;
            }
        }
    }

    public int getSize() {
        return vectorComponents.length;
    }

    @Override
    public String toString() {
        System.out.print("{");
        for (int i = 0; i < vectorComponents.length; i++) {
            if (i == vectorComponents.length - 1) {
                System.out.print(vectorComponents[i] + "}");
            } else {
                System.out.print(vectorComponents[i] + ", ");
            }
        }

        return "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(vectorComponents, vector.vectorComponents);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorComponents);
    }

    public Vector getAddition(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] = vectorComponents[i] + vector.vectorComponents[i];
        }

        return new Vector(vectorComponents);
    }

    public Vector getDifference(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] = vectorComponents[i] - vector.vectorComponents[i];
        }

        return new Vector(vectorComponents);
    }

    public Vector getMultiplicationByScalar(int multiplier) {
        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] = vectorComponents[i] * multiplier;
        }

        return new Vector(vectorComponents);
    }

    public Vector reverse() {
        getMultiplicationByScalar(-1);

        return new Vector(vectorComponents);
    }

    public double getLength() {
        double result = 0;

        for (double component : vectorComponents) {
            result += component * component;
        }

        result = Math.sqrt(result);

        return result;
    }

    public double getComponent(int index) {
        return vectorComponents[index];
    }

    public void setComponent(int index, double component) {
        vectorComponents[index] = component;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Arrays.copyOf(vector1.vectorComponents, Math.max(vector1.vectorComponents.length, vector2.vectorComponents.length)));
        resultVector.getAddition(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Arrays.copyOf(vector1.vectorComponents, Math.max(vector1.vectorComponents.length, vector2.vectorComponents.length)));
        resultVector.getDifference(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double sum = 0;
        int minVectorsLength = Math.min(vector1.vectorComponents.length, vector2.vectorComponents.length);
        
        for (int i = 0; i < minVectorsLength; i++) {
            sum += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return sum;
    }
}