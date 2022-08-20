package ru.academits.krivorotov.matrix;

import ru.academits.krivorotov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Значение размерности rows || columns <= 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = matrix.getStringVector(i);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0");
        }

        int columnsCount = 0;

        for (double[] row : array) {
            if (row.length > columnsCount) {
                columnsCount = row.length;
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount);

            for (int j = 0; j < array[i].length; j++) {
                rows[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0");
        }

        int columnsCount = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnsCount) {
                columnsCount = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(columnsCount);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                rows[i].setComponent(j, vectors[i].getComponent(j));
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getStringVector(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setStringVector(int index, Vector vector) {
        checkRowIndex(index);
        checkDimensionsVectorAndMatrix(this, vector);

        rows[index] = new Vector(vector);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс = " + index +
                    ". Индекс должен быть в диапазоне [0, " + (getColumnsCount() - 1) + "].");
        }

        double[] array = new double[getRowsCount()];

        for (int i = 0; i < getRowsCount(); i++) {
            array[i] = rows[i].getComponent(index);
        }

        return new Vector(array);
    }

    private void checkDimensionsVectorAndMatrix(Matrix matrix, Vector vector) {
        if (vector.getSize() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора = " + vector.getSize() +
                    ", должна быть равна количеству столбцов в матрице = " + matrix.getColumnsCount());
        }
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс = " + index +
                    ". Индекс должен быть в диапазоне [0, " + (getRowsCount() - 1) + "].");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        for (Vector vector : rows) {
            builder.append(vector).append(", ");
        }

        builder.replace(builder.length() - 2, builder.length(), "}");

        return builder.toString();
    }

    public Matrix transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedRows[i] = getColumnVector(i);
        }

        rows = transposedRows;
        return this;
    }

    public void multiplyByScalar(double multiplier) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(multiplier);
        }
    }

    //Умножение матрицы на вектор-строку (1*n), возможно в том случае, если матрица имеет 1 столбец (m*1),
    //произведением будет матрица (m*n) m-строк и n-столбцов.
    public Matrix multiplyByVector(Vector vector) {
        if (getColumnsCount() > 1) {
            throw new IllegalArgumentException("Матрица должна быть размерностью в 1 столбец, при умножении на вектор-строку");
        }

        Vector[] vectors = new Vector[1];
        vectors[0] = new Vector(vector);

        return new Matrix(getProduct(this, new Matrix(vectors)));
    }

    //Складывать и вычитать можно матрицы одного размера, в результате получается матрица того же размера.
    public void add(Matrix matrix) {
        checkSizesMatrices(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizesMatrices(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static void checkSizesMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть одинаковы.");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkSizesMatrices(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizesMatrices(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // Произведение матриц (С= АВ) — операция только для согласованных матриц А и В, у которых число столбцов матрицы А
    // равно числу строк матрицы В.
    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы = " + matrix1.getColumnsCount() +
                    ", должно быть равно числу строк второй матрицы = " + matrix2.getRowsCount());
        }

        Vector[] resultMatrixRows = new Vector[matrix1.getRowsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            resultMatrixRows[i] = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double component = 0;

                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    component += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                resultMatrixRows[i].setComponent(j, component);
            }
        }

        return new Matrix(resultMatrixRows);
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount() || getRowsCount() == 0) {
            throw new UnsupportedOperationException("Матрица неквадратная");
        }

        return getDeterminant(this);
    }

    private double getDeterminant(Matrix matrix) {
        if (matrix.getRowsCount() == 1) {
            return matrix.rows[0].getComponent(0);
        }

        if (matrix.getRowsCount() == 2) {
            return matrix.rows[0].getComponent(0) * matrix.rows[1].getComponent(1) -
                    matrix.rows[1].getComponent(0) * matrix.rows[0].getComponent(1);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            determinant += Math.pow(-1, i) * matrix.rows[0].getComponent(i) * getDeterminant(getMinor(i, matrix));
        }

        return determinant;
    }

    private Matrix getMinor(int deletedColumnIndex, Matrix matrix) {
        int matrixRowsCount = matrix.getRowsCount();
        Matrix resultMatrix = new Matrix(matrixRowsCount - 1, matrixRowsCount - 1);

        for (int i = 1; i < matrixRowsCount; i++) {
            double[] numbers = new double[matrixRowsCount - 1];
            int k = 0;

            for (int j = 0; j < matrixRowsCount; j++) {
                if (j == deletedColumnIndex) {
                    continue;
                }

                numbers[k] = matrix.rows[i].getComponent(j);
                k++;
            }

            resultMatrix.rows[i - 1] = new Vector(matrixRowsCount - 1, numbers);
        }

        return resultMatrix;
    }
}