package ru.academits.krivorotov.list_main;

import ru.academits.krivorotov.singlyLinkedList.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.addFirst(1);
        list1.addFirst(2);
        list1.addFirst(3);
        list1.addFirst(4);
        list1.addFirst(5);
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.print("Удаление первого элемента: ");
        list1.removeFirst();
        System.out.println();

        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.print("Элемент по индексу (printByIndex): ");
        list1.printByIndex(1);
        System.out.println();

        System.out.println("Значение первого элемента: " + list1.getValueFirst());
        System.out.println();

        System.out.println("Вставка элемента по индексу (addByIndex):");
        list1.addByIndex(22, 2);
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("Элемент по индексу (getValueByIndex): " + list1.getValueByIndex(0));
        System.out.println();

        System.out.print("Изменение элемента по индексу (setValueByIndex), вывод предыдущего значения: ");
        list1.setValueByIndex(1, 8);
        System.out.println();
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.print("Удаление элемента по индексу (removeByIndex): ");
        list1.removeByIndex(4);
        System.out.println();
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.print("Удаление элемента по значению (removeByData): ");
        list1.removeByData(22);
        System.out.println();
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("ReverseList: ");
        list1.reverse();
        list1.print();
        System.out.println("Размер list1 = " + list1.getSize());
        System.out.println();

        System.out.println("list2: ");
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list1.copy(list2);
        list2.print();
        System.out.println("Размер list2 = " + list2.getSize());

    }
}