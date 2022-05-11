package ru.academits.krivorotov.hashtable_main;

import ru.academits.krivorotov.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();

        System.out.println("Хэш-таблица: " + hashTable);

        System.out.println("Добавление элемента: " + hashTable.add("hello1"));
        hashTable.add("hello2");
        hashTable.add("hello3");
        hashTable.add("hello4");
        hashTable.add("hello5");
        hashTable.add("5");
        hashTable.add("5");

        System.out.println("Хэш-таблица: " + hashTable);

        System.out.println("Contains: " + hashTable.contains("hello1"));
        System.out.println();

        System.out.println("remove: ");
        System.out.println(hashTable.remove("5"));
        System.out.println(hashTable.remove("5"));
        System.out.println(hashTable.remove("hello20"));
        System.out.println();

        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        System.out.println("Clear: ");
        hashTable.clear();
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        ArrayList<String> list1 = new ArrayList<>(10);
        list1.add("list11");
        list1.add("list12");
        list1.add("list13");

        System.out.println("AddAll: ");
        System.out.println(hashTable.addAll(list1));
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        System.out.println("ContainsAll: ");
        System.out.println(hashTable.containsAll(list1));
        System.out.println();

        System.out.println("RemoveAll: ");
        System.out.println(hashTable.removeAll(list1));
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        hashTable.add("hello2");
        hashTable.add("hello3");
        hashTable.add("hello4");
        hashTable.add("hello5");
        hashTable.add("5");
        hashTable.add("5");

        System.out.println("AddAll: ");
        System.out.println(hashTable.addAll(list1));
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        System.out.println("RetainAll: ");
        System.out.println(hashTable.retainAll(list1));
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        System.out.println("toArray (hashTable): " + Arrays.toString(hashTable.toArray()));
        System.out.println();

        Object[] array = new Object[15];
        System.out.println("toArray (hashTable): " + Arrays.toString(hashTable.toArray(array)));

        System.out.println(hashTable.set(0, "1"));
        System.out.println("Хэш-таблица: " + hashTable);
        System.out.println();

        //noinspection rawtypes
        Iterator iterator = hashTable.iterator();
        //noinspection WhileLoopReplaceableByForEach
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}