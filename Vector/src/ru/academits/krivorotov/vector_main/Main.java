package ru.academits.krivorotov.vector_main;

import ru.academits.krivorotov.vector.Vector;

import static ru.academits.krivorotov.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(3);
        Vector vector1 = new Vector(new double[]{1.0, 4.0, -10, 0});
        Vector vector2 = new Vector(5, new double[]{5.0, 3.0, 4.0, 0, 8});
        Vector vector3 = new Vector(vector1);

        System.out.printf("vector = %s", vector);
        System.out.printf("vector1 = %s", vector1);
        System.out.printf("vector2 = %s", vector2);
        System.out.printf("vector3 = %s", vector3);
        System.out.println();

        vector3.setComponent(3, 10.0);
        System.out.printf("vector3 после изменения компоненты = %s", vector3);
        System.out.printf("vector1 = %s", vector1);
        System.out.println("Размерность вектора = " + vector.getSize());
        System.out.printf("Прибавление к вектору другого вектора %s", vector1.getAddition(vector2));
        System.out.printf("Вычитание из вектора другого вектора %s", vector1.getDifference(vector2));
        System.out.printf("Умножение вектора на скаляр %s", vector2.getMultiplicationByScalar(3));
        System.out.printf("Разворот вектора %s", vector2.reverse());
        System.out.println("Получение длины вектора " + vector1.getLength());
        System.out.println("Получение компоненты вектора по индексу " + vector2.getComponent(2));
        vector2.setComponent(3, 10.0);
        System.out.printf("Установлено новое значение компоненты вектора по индексу %s", vector2);

        System.out.printf("Сложение двух векторов %s", getSum(vector1, vector2));
        System.out.printf("Вычитание векторов %s", getDifference(vector1, vector2));
        System.out.println("Скалярное произведение векторов " + getScalarProduct(vector1, vector2));
    }
}