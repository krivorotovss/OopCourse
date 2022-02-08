package ru.academits.krivorotov.vector_main;

import ru.academits.krivorotov.vector.Vector;

import static ru.academits.krivorotov.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(5);
        Vector vector1 = new Vector(new double[]{1.0, 4.0, 1.0, 0});
        Vector vector2 = new Vector(5, new double[]{5.0, 3.0, 4.0});
        Vector vector3 = new Vector(vector1);

        System.out.println("vector = " + vector);
        System.out.println("vector1 = " + vector1);
        System.out.println("vector2 = " + vector2);
        System.out.println("vector3 = " + vector3);

        vector3.setVectorComponent(3, 10.0);
        System.out.println("vector3 после изменения компоненты = " + vector3);
        System.out.println("vector1 = " + vector1);

        System.out.println("Размерность вектора = " + vector.getSize());
        System.out.println("Прибавление к вектору другого вектора " + vector2.getVectorsSum(vector1));
        System.out.println("Вычитание из вектора другого вектора " + vector1.getVectorsSubtracting(vector2));
        System.out.println("Умножение вектора на скаляр " + vector2.getVectorMultiplication(3));
        System.out.println("Разворот вектора " + vector2.reverseVector());
        System.out.println("Получение длины вектора " + vector1.getVectorLength());
        System.out.println("Получение компоненты вектора по индексу " + vector2.getVectorComponent(2));
        vector2.setVectorComponent(3, 10.0);
        System.out.println("Установлена новое значение компоненты вектора по индексу " + vector2);

        System.out.println("Сложение двух векторов " + getVectorsSum(vector1, vector2));
        System.out.println("Вычитание векторов " + getVectorsSubtracting(vector1, vector2));
        System.out.println("Скалярное произведение векторов " + getVectorsScalarProduct(vector1, vector2));
    }
}