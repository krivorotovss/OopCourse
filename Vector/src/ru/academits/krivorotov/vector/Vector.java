package ru.academits.krivorotov.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int size;
    protected double[] array;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Значение размерности <= 0");
        }

        this.size = size;
        this.array = new double[size];
        Arrays.fill(array, 0.0);
    }

    public int getSize() {
        return size;
    }

    public Vector(Vector vector) {
        this.size = vector.getSize();
        this.array = vector.getArray().clone();
    }

    public Vector(double[] array) {
        this.array = array;
    }

    public double[] getArray() {
        return array;
    }

    public Vector(int size, double[] array) {
        this.size = size;
        this.array = new double[size];

        if (size <= 0) {
            throw new IllegalArgumentException("Значение размерности <= 0");
        }

        for (int i = 0; i < array.length; i++) {
            if (this.array[i] != array[i]) {
                this.array[i] = array[i];
            } else {
                this.array[i] = 0;
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public Vector getVectorsSum(Vector vector) {
        double[] resultArray = new double[Math.max(array.length, vector.array.length)];

        for (int i = 0; i < Math.min(array.length, vector.array.length); i++) {
            resultArray[i] = array[i] + vector.array[i];
        }

        return new Vector(resultArray);
    }

    public Vector getVectorsSubtracting(Vector vector) {
        double[] resultArray = new double[Math.max(array.length, vector.array.length)];

        for (int i = 0; i < Math.min(array.length, vector.array.length); i++) {
            resultArray[i] = array[i] - vector.array[i];
        }

        return new Vector(resultArray);
    }

    public Vector getVectorMultiplication(int multiplier) {
        double[] resultArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            resultArray[i] = array[i] * multiplier;
        }

        return new Vector(resultArray);
    }

    public Vector reverseVector() {
        double[] resultArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                resultArray[i] = array[i] * (-1);
            }
        }

        return new Vector(resultArray);
    }

    public int getVectorLength() {
        int result = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = Math.sqrt(array[i] * array[i]);
            result += array[i];
        }

        return result;
    }

    public double getVectorComponent(int index) {
        double component = 0;

        for (int i = 0; i < index; i++) {
            component = array[index];
        }

        return component;
    }

    public void setVectorComponent(int index, double component) {
        for (int i = 0; i < index; i++) {
            array[index] = component;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return size == vector.size && Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(new double[Math.max(vector1.array.length, vector2.array.length)]);

        for (int i = 0; i < Math.min(vector1.array.length, vector2.array.length); i++) {
            resultVector.array[i] = vector1.array[i] + vector2.array[i];
        }

        return resultVector;
    }

    public static Vector getVectorsSubtracting(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(new double[Math.max(vector1.array.length, vector2.array.length)]);

        for (int i = 0; i < Math.min(vector1.array.length, vector2.array.length); i++) {
            resultVector.array[i] = vector1.array[i] - vector2.array[i];
        }

        return resultVector;
    }

    public static double getVectorsScalarProduct(Vector vector1, Vector vector2) {
        double componentMultiplicationSum = 0;

        for (int i = 0; i < Math.min(vector1.array.length, vector2.array.length); i++) {
            componentMultiplicationSum += vector1.array[i] * vector2.array[i];
        }

        return componentMultiplicationSum;
    }
}