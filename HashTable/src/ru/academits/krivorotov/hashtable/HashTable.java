package ru.academits.krivorotov.hashtable;

import java.util.ArrayList;
import java.util.Objects;

public class HashTable<T> {
    private final T[] items;
    private final int length;

    public HashTable() {
        //noinspection unchecked
        items = (T[]) new Object[10];
        length = 10;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked") HashTable<T> arrayList = (HashTable<T>) o;

        if (length == arrayList.length) {
            int count = 0;

            for (int i = 0; i < length; i++) {
                if (items[i].equals(arrayList.items[i])) {
                    count++;
                }
            }

            return count == length;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;

        for (int i = 0; i < length; i++) {
            if (items[i] == null) {
                continue;
            }

            hash += items[i].hashCode() * prime;
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (length == 0) {
            builder.append("[]");
        }

        for (int i = 0; i < length; i++) {
            builder.append(" ").append(items[i]).append(",");
        }

        builder.setCharAt(0, '[');
        builder.setCharAt(builder.length() - 1, ']');

        return builder.toString();
    }

    public int calculateIndex(T object) {
        return Math.abs(object.hashCode() % items.length);
    }

    public void add(T item) {
        int index = calculateIndex(item);

        //noinspection unchecked
        ArrayList<T> arrayList = (ArrayList<T>) items[index];

        if (items[index] == null) {
            arrayList = new ArrayList<>();
        }

        //noinspection unchecked
        items[index] = (T) arrayList;
        arrayList.add(item);
    }

    public void remove(T item) {
        int index = calculateIndex(item);

        if (items[index] == null) {
            System.out.println("Элемент не найден");
            return;
        }

        //noinspection unchecked
        ArrayList<T> arrayList = (ArrayList<T>) items[index];
        arrayList.remove(indexOf(item));
    }

    public int indexOf(T item) {
        int index = calculateIndex(item);

        //noinspection unchecked
        ArrayList<T> arrayList = (ArrayList<T>) items[index];

        for (int i = 0; i < arrayList.size(); i++) {
            if (Objects.equals(arrayList.get(i), item)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }
}