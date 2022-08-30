package ru.academits.krivorotov.matrix_main;

import ru.academits.krivorotov.matrix.Matrix;
import ru.academits.krivorotov.vector.Vector;

import static ru.academits.krivorotov.matrix.Matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2, 2);
        Matrix matrix2 = new Matrix(new double[2][3]);
        Matrix matrix3 = new Matrix(matrix1);
        Matrix matrix4 = new Matrix(new double[][]{{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}});
        Matrix matrix5 = new Matrix(new double[][]{{5, 4, 2, 4}, {1}, {1, 2, 3, 4}});

        Vector[] vectors = new Vector[3];
        vectors[0] = new Vector(new double[]{0, 1, 2, 3});
        vectors[1] = new Vector(new double[]{4, 5, 6, 7});
        vectors[2] = new Vector(new double[]{8, 9, 0});
        Matrix matrix6 = new Matrix(vectors);

        Matrix matrix7 = new Matrix(new double[][]{{5, 4}, {1, 2}});
        Matrix matrix8 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {8, 9, 10}, {1, 2, 3}});
        Matrix matrix9 = new Matrix(new double[][]{{15, 14, 12}, {6, 7, 1}, {54, 5, 7}});
        Matrix matrix10 = new Matrix(new double[][]{{0, 1, 2}, {4, 5, 6}, {8, 9, 10}});

        System.out.println("matrix1 = " + matrix1);
        System.out.println("matrix2 = " + matrix2);
        System.out.println("matrix3 = " + matrix3);
        System.out.println("matrix4 = " + matrix4);
        System.out.println("matrix5 = " + matrix5);
        System.out.println("matrix6 = " + matrix6);
        System.out.println();

        Vector vector2 = new Vector(new double[]{0, 1, 2});
        System.out.println("Вставка вектора-строки по индексу: ");
        matrix2.setRow(1, vector2);
        System.out.println("matrix2 = " + matrix2);
        System.out.println();

        System.out.println("Получение вектора-строки по индексу = " + matrix1.getRow(0));
        System.out.println();

        System.out.println("matrix7 = " + matrix7);
        matrix7.multiplyByScalar(2);
        System.out.println("Умножение на скаляр: " + matrix7);

        System.out.println("Транспонирование матрицы: " + matrix7.transpose());
        System.out.println();

        System.out.println("matrix5 = " + matrix5);
        System.out.println("matrix6 = " + matrix6);
        matrix5.add(matrix6);
        System.out.println("Сложение матриц: " + matrix5);
        System.out.println();

        System.out.println("matrix5 = " + matrix5);
        System.out.println("matrix6 = " + matrix6);
        matrix5.subtract(matrix6);
        System.out.println("Вычитание матриц: " + matrix5);
        System.out.println();

        Vector vector = new Vector(new double[]{1, 2, 3});
        System.out.println("matrix10 = " + matrix10);
        System.out.println("vector = " + vector);
        System.out.println("Умножение на вектор: " + matrix10.multiplyByVector(vector));
        System.out.println();

        System.out.println("Static сложение матриц: " + getSum(matrix5, matrix6));
        System.out.println("Static вычитание матриц: " + getDifference(matrix5, matrix6));
        System.out.println();
        System.out.println("matrix5 = " + matrix5);
        System.out.println("matrix8 = " + matrix8);
        System.out.println("Static умножение матриц: " + getProduct(matrix5, matrix8));
        System.out.println();

        System.out.println("Определитель матрицы: " + matrix9.getDeterminant());
        System.out.println("Определитель матрицы: " + matrix1.getDeterminant());
    }
}