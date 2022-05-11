package ru.academits.krivorotov.hashtable_main;

import ru.academits.krivorotov.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();

        System.out.println("Хэш-таблица: " + hashTable);

        hashTable.add("hello1");
        hashTable.add("hello2");
        hashTable.add("hello3");
        hashTable.add("hello4");
        hashTable.add("hello5");
        hashTable.add("5");
        hashTable.add("5");

        System.out.println("Хэш-таблица: " + hashTable);

        System.out.println("Contains: " + hashTable.contains("hello1"));
        System.out.println();

        System.out.println("indexOf: " + hashTable.indexOf("5"));
        System.out.println();

        System.out.println("remove: ");
        hashTable.remove("5");
        hashTable.remove("5");
        hashTable.remove("hello20");
        System.out.println();

        System.out.println("Хэш-таблица: " + hashTable);
    }
}