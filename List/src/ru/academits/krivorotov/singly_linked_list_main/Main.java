package ru.academits.krivorotov.singly_linked_list_main;

import ru.academits.krivorotov.singly_linked_list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        System.out.println(list1);
        System.out.println();

        list1.addFirst(1);
        list1.addFirst(2);
        list1.addFirst(null);
        list1.addFirst(4);
        list1.addFirst(5);
        list1.add(6);
        list1.add(7);

        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Удаление первого элемента (removeFirst): " + list1.removeFirst());
        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Значение первого элемента (getFirst): " + list1.getFirst());
        System.out.println();

        System.out.println("Вставка элемента по индексу (addByIndex):");
        list1.addByIndex(3, 22);
        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Элемент по индексу (getByIndex): " + list1.getByIndex(0));
        System.out.println();

        System.out.println("Изменение элемента по индексу (setByIndex), вывод предыдущего значения: " + list1.setByIndex(0, 8));
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Удаление элемента по индексу (removeByIndex), значение: " + list1.removeByIndex(5));
        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Удаление элемента по значению (removeByData): " + list1.removeByData(33));
        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("ReverseList:");
        list1.reverse();
        System.out.println(list1);
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println(list1);
        System.out.println();

        System.out.println("Копирование list1 (copy): " + list1.copy());

        System.out.println("list1 = " + list1);
        System.out.println("Размер list1 = " + list1.getSize());
    }
}