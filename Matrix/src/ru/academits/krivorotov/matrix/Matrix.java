package ru.academits.krivorotov.matrix;

import ru.academits.krivorotov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Значение размерности rows <= 0: rows = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Значение размерности columns <= 0: columns = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0: размер = " + array.length);
        }

        int columnsCount = 0;

        for (double[] row : array) {
            if (row.length > columnsCount) {
                columnsCount = row.length;
            }
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Число столбцов - columnsCount, должно быть > 0, columnsCount = " + columnsCount +
                    ", массив пустой.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0, размер = " + vectors.length);
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
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размерность вектора = " + vector.getSize() +
                    ", должна быть равна количеству столбцов в матрице = " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
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
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
        return this;
    }

    public void multiplyByScalar(double multiplier) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(multiplier);
        }
    }

    //    При умножении матрицы на вектор-столбец число столбцов в матрице должно совпадать с числом строк в векторе-столбце.
//    Результатом умножения матрицы на вектор-столбец есть вектор-столбец
    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора = " + vector.getSize() +
                    " и количество столбцов матрицы = " + getColumnsCount() + ", должны быть равны.");
        }

        Vector resultVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    //Складывать и вычитать можно матрицы одного размера, в результате получается матрица того же размера.
    public void add(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizes(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    private static void checkMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть одинаковы: число строк matrix1 = "
                    + matrix1.getRowsCount() + ", число строк matrix2 = " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть одинаковы: число столбцов matrix1 = "
                    + matrix1.getColumnsCount() + ", число столбцов matrix2 = " + matrix2.getColumnsCount());
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizes(matrix1, matrix2);

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
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("Матрица неквадратная: число строк = " + getRowsCount() +
                    ", число столбцов = " + getColumnsCount());
        }

        return getDeterminant(this);
    }

    private static double getDeterminant(Matrix matrix) {
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

    private static Matrix getMinor(int deletedColumnIndex, Matrix matrix) {
        int matrixRowsCount = matrix.getRowsCount();
        int resultMatrixRowsCount = matrixRowsCount - 1;

        Matrix resultMatrix = new Matrix(resultMatrixRowsCount, resultMatrixRowsCount);

        for (int i = 1; i < matrixRowsCount; i++) {
            double[] numbers = new double[resultMatrixRowsCount];
            int k = 0;

            for (int j = 0; j < matrixRowsCount; j++) {
                if (j == deletedColumnIndex) {
                    continue;
                }

                numbers[k] = matrix.rows[i].getComponent(j);
                k++;
            }

            resultMatrix.rows[i - 1] = new Vector(resultMatrixRowsCount, numbers);
        }

        return resultMatrix;
    }
}