package ru.academits.krivorotov.vector_main;

import ru.academits.krivorotov.vector.Vector;

import static ru.academits.krivorotov.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);
        Vector vector2 = new Vector(new double[]{1.0, 4.0, -10});
        Vector vector3 = new Vector(5, new double[]{5.0, 3.0, 4.0, 0, 8});
        Vector vector4 = new Vector(vector2);

        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);
        System.out.println("vector3 = " + vector3);
        System.out.println("vector4 = " + vector4);

        vector4.setComponent(1, 10.0);
        System.out.println("vector4 после изменения компоненты = " + vector4);
        System.out.println("Размерность вектора vector1 = " + vector1.getSize());
        System.out.println("Прибавление к вектору другого вектора " + vector2.add(vector3));
        System.out.println("Вычитание из вектора другого вектора " + vector2.subtract(vector3));
        System.out.println("Умножение вектора на скаляр " + vector3.multiplyByScalar(3));
        System.out.println("Разворот вектора " + vector3.reverse());
        System.out.println("Получение длины вектора " + vector2.getLength());
        System.out.println("Получение компоненты вектора по индексу " + vector3.getComponent(2));
        vector3.setComponent(3, 10.0);
        System.out.println("Установлено новое значение компоненты вектора по индексу " + vector3);

        System.out.println("Сложение двух векторов " + getSum(vector2, vector3));
        System.out.println("Вычитание векторов " + getDifference(vector2, vector3));
        System.out.println("Скалярное произведение векторов " + getScalarProduct(vector2, vector3));
    }
}