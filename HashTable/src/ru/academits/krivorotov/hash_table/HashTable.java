package ru.academits.krivorotov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        this(10);
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Значение initialCapacity должно быть больше 0, initialCapacity = " + initialCapacity);
        }

        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        for (ArrayList<T> list : lists) {
            builder.append(" ").append(list).append(",");
        }

        builder.setCharAt(0, '[');
        builder.setCharAt(builder.length() - 1, ']');

        return builder.toString();
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public boolean add(T item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (lists[index] != null && lists[index].remove(object)) {
            modCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean contains(Object object) {
        int index = getIndex(object);

        return lists[index] != null && lists[index].contains(object);
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        modCount++;
        size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (T item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isModified = false;

        for (Object object : collection) {
            while (remove(object)) {
                isModified = true;
            }
        }

        return isModified;
    }

    // Сохраняет только те элементы в этой коллекции, которые содержатся в указанной коллекции (необязательная операция).
    // Другими словами, удаляет из этой коллекции все ее элементы, которые не содержатся в указанной коллекции.
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            if (size == 0) {
                return false;
            }

            clear();
            return true;
        }

        boolean isModified = false;

        for (ArrayList<T> list : lists) {
            if (list != null) {
                int listSizeBefore = list.size();

                if (list.retainAll(collection)) {
                    isModified = true;
                    int listSizeAfter = list.size();
                    size -= listSizeBefore - listSizeAfter;
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];

        int i = 0;

        for (T item : this) {
            objects[i] = item;
            i++;
        }

        return objects;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private final int startModCount = modCount;
        private int arrayIndex;
        private int listIndex;
        private int passedElementsCount;

        public boolean hasNext() {
            return passedElementsCount < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Изменение коллекции недопустимо");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].isEmpty()) { //если лист null или размер 0, счетчик массива +1
                arrayIndex++;
            }

            T currentItem = lists[arrayIndex].get(listIndex);
            listIndex++;
            passedElementsCount++;

            if (listIndex == lists[arrayIndex].size()) { // после прохода листа до конца, сбрасываем счетчик листа, +1 счетчик массива
                arrayIndex++;
                listIndex = 0;
            }

            return currentItem;
        }
    }
}