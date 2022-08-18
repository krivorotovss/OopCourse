package ru.academits.krivorotov.hash_table_main;

import ru.academits.krivorotov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable1 = new HashTable<>(5);
        HashTable<String> hashTable2 = new HashTable<>();

        System.out.println("Размер хеш-таблицы = " + hashTable1.size());
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();
        hashTable2.add("55");
        System.out.println("Дефолтная Хэш-таблица: " + hashTable2);
        System.out.println();

        System.out.println("Добавление элемента: " + hashTable1.add("hello1"));
        hashTable1.add("hello2");
        hashTable1.add("hello3");
        hashTable1.add("hello4");
        hashTable1.add("hello5");
        hashTable1.add("5");
        hashTable1.add("5");
        hashTable1.add(null);
        hashTable1.add(null);
        hashTable1.add(null);

        System.out.println("Хэш-таблица: " + hashTable1);

        System.out.println("Contains hello1: " + hashTable1.contains("hello1"));
        System.out.println();

        System.out.println("remove: ");
        System.out.println(hashTable1.remove("5"));
        System.out.println(hashTable1.remove("6"));
        System.out.println(hashTable1.remove("hello20"));
        System.out.println();

        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        System.out.println("Clear: ");
        hashTable1.clear();
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        ArrayList<String> list1 = new ArrayList<>(10);
        list1.add("list11");
        list1.add("list12");
        list1.add("list13");
        ArrayList<String> list2 = new ArrayList<>(10);
        list2.add("list11");
        list2.add("list12");
        list2.add("list13");

        System.out.println("AddAll: ");
        System.out.println(hashTable1.addAll(list1));
        System.out.println(hashTable1.addAll(list2));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        System.out.println("ContainsAll1: ");
        System.out.println(hashTable1.containsAll(list1));
        System.out.println();

        System.out.println("RemoveAll1: ");
        System.out.println(hashTable1.removeAll(list1));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        System.out.println("RemoveAll2: ");
        System.out.println(hashTable1.removeAll(list1));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        hashTable1.add("hello2");
        hashTable1.add("hello3");
        hashTable1.add("hello4");
        hashTable1.add("hello5");
        hashTable1.add("5");
        hashTable1.add("5");

        System.out.println("list1 = " + list1);
        System.out.println("AddAll: ");
        System.out.println(hashTable1.addAll(list1));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println("list1 = " + list1);
        System.out.println("RetainAll1: ");
        System.out.println(hashTable1.retainAll(list1));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        ArrayList<String> list3 = new ArrayList<>(10);
        System.out.println("list3 = " + list3);
        System.out.println("RetainAll2: ");
        System.out.println(hashTable1.retainAll(list3));
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println("hashTable.size() = " + hashTable1.size());
        System.out.println();

        hashTable1.add("hello2");
        hashTable1.add("hello3");
        hashTable1.add("hello4");
        hashTable1.add("hello5");
        hashTable1.add("5");
        hashTable1.add("5");
        hashTable1.add(null);
        hashTable1.add(null);
        hashTable1.add(null);
        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println();

        System.out.println("Итератор: ");

        //noinspection rawtypes
        Iterator iterator = hashTable1.iterator();
        //noinspection WhileLoopReplaceableByForEach
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Хэш-таблица: " + hashTable1);
        System.out.println("hashTable.size() = " + hashTable1.size());
        System.out.println();

        System.out.println("toArray (hashTable): " + Arrays.toString(hashTable1.toArray()));
        System.out.println();

        Object[] array = new Object[15];
        System.out.println("toArray (hashTable): " + Arrays.toString(hashTable1.toArray(array)));
        System.out.println();
    }
}