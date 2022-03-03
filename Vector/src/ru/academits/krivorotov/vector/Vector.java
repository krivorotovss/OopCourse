package ru.academits.krivorotov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Значение размерности (int size) <= 0,  int size = " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Значение размерности (components.length) = 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Значение размерности (int size) <= 0,  int size = " + size);
        }

        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(components)
                .replace("[", "{")
                .replace("]", "}");
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

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public Vector add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);

            for (int i = 0; i < components.length; i++) {
                components[i] += vector.components[i];
            }

        } else {
            Vector vectorCopy = new Vector(Arrays.copyOf(vector.components, components.length));

            for (int i = 0; i < components.length; i++) {
                components[i] += vectorCopy.components[i];
            }
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);

            for (int i = 0; i < components.length; i++) {
                components[i] -= vector.components[i];
            }

        } else {
            Vector vectorCopy = new Vector(Arrays.copyOf(vector.components, components.length));

            for (int i = 0; i < components.length; i++) {
                components[i] -= vectorCopy.components[i];
            }
        }

        return this;
    }

    public Vector multiplyByScalar(double multiplier) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= multiplier;
        }

        return this;
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Arrays.copyOf(vector1.components, Math.max(vector1.components.length, vector2.components.length)));
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Arrays.copyOf(vector1.components, Math.max(vector1.components.length, vector2.components.length)));
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minVectorsLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minVectorsLength; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}