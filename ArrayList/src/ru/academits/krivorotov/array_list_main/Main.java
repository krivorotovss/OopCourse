package ru.academits.krivorotov.array_list_main;

import ru.academits.krivorotov.array_list.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(10);

        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        list1.add("hello");
        list1.add("hello1");
        list1.add("hello2");
        list1.add(3, "hello3");
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        System.out.println("Метод set, вставка значения вместо: " + list1.set(1, "element"));
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        list1.add("hello10");
        System.out.println("Добавление нового элемента: " + list1.add("newHello"));
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        System.out.println("Массив содержит, указанный элемент: " + list1.contains("hello2"));
        System.out.println();

        list1.trimToSize();
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        list1.add("hello4");
        list1.add("hello5");
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();
        list1.trimToSize();

        System.out.println("Удаление элемента по индексу, удаленный элемент: " + list1.remove(2));
        list1.trimToSize();
        System.out.println("Вывод списка list1, после удаления по индексу: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        System.out.println("Удаление элемента: " + list1.remove("hello4"));
        System.out.println("Вывод списка list1, после удаления по data: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        list2.add("hello1");
        list2.add("hello2");
        list2.add("hello3");

        System.out.println("Вывод списка list2: " + list2);
        System.out.println("Размер списка list2: " + list2.size());
        System.out.println();

        System.out.println("Элемент по индексу = " + list2.get(1));
        System.out.println();

        list2.set(1, "newElement");
        System.out.println("Элемент по индексу = " + list2.get(1));
        System.out.println();

        list2.trimToSize();
        System.out.println("Вывод списка list2: " + list2);
        System.out.println("Размер списка list2: " + list2.size());

        list2.add(2, "1");
        System.out.println();
        System.out.println("Вывод списка list2: " + list2);
        System.out.println("Размер списка list2: " + list2.size());

        System.out.println("Первое вхождение: " + list2.indexOf("1"));
        list2.add("hello");
        System.out.println("Первое вхождение: " + list2.indexOf("2"));
        System.out.println("Вывод списка list2: " + list2);
        System.out.println("Размер списка list2: " + list2.size());
        System.out.println("Последнее вхождение: " + list2.lastIndexOf("newElement"));
        System.out.println();

        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println("Вывод списка list2: " + list2);
        System.out.println("Размер списка list2: " + list2.size());
        System.out.println();

        System.out.println("toArray (list1): " + Arrays.toString(list1.toArray()));
        Object[] array = new Object[2];
        System.out.println("toArray (list2): " + Arrays.toString(list2.toArray(array)));
        System.out.println();

        System.out.println("Удаление всех значений clear:");
        list1.clear();
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        ArrayList<String> list3 = new ArrayList<>(8);
        list3.add("list31");
        list3.add("list32");
        list3.add("list33");
        list3.add("list34");
        list3.add("list35");
        list3.add("list36");
        list3.add("list37");

        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println("Вывод списка list3: " + list3);
        System.out.println("Размер списка list3: " + list3.size());
        System.out.println();

        list1.add("list31");
        list1.add("list32");
        list1.add("list33");
        list1.add("list31");

        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();
        System.out.println("Вывод списка list3: " + list3);
        System.out.println("Размер списка list3: " + list3.size());
        System.out.println();

        System.out.println("Метод removeAll по ArrayList: " + list1.removeAll(list3));
        System.out.println("Вывод списка list1, после removeAll: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        list1.add("hello");
        list1.add("hello1");
        list1.add("hello2");
        list1.add("hello3");

        System.out.println("Вывод списка list1: " + list1);
        System.out.println();

        System.out.println("Метод addAll: " + list1.addAll(list3));
        System.out.println("Вывод списка list1, после addAll: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        System.out.println("Метод addAll по индексу: " + list1.addAll(2, list3));
        System.out.println("Вывод списка list1, после addAll по индексу: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println();

        System.out.println("Вывод списка list3: " + list3);
        System.out.println();

        System.out.println("Метод retainAll: " + list1.retainAll(list3));
        System.out.println("Вывод списка list1, после retainAll: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println("Вывод списка list3: " + list3);
        System.out.println();

        //noinspection SlowListContainsAll
        System.out.println("Метод containsAll: " + list1.containsAll(list3));
        System.out.println("Вывод списка list1: " + list1);
        System.out.println("Размер списка list1: " + list1.size());
        System.out.println("Вывод списка list3: " + list3);
        System.out.println("Размер списка list3: " + list3.size());
        System.out.println();

        ArrayList<String> list4 = new ArrayList<>(0);

        System.out.println("Вывод списка list4: " + list4);
        System.out.println("Размер списка list4: " + list4.size());
        System.out.println();

        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");
        list4.add("hello");

        System.out.println("Вывод списка list4: " + list4);
        System.out.println("Размер списка list4: " + list4.size());
        System.out.println();
    }
}