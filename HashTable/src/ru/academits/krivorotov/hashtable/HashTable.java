package ru.academits.krivorotov.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final T[] items;
    private final int length;
    private int modCount = 0;

    public HashTable() {
        //noinspection unchecked
        items = (T[]) new Object[10];
        length = 10;
    }

    public T set(int index, T item) {
        modCount++;

        return items[index] = item;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
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

    private int calculateIndex(T object) {
        return Math.abs(object.hashCode() % items.length);
    }

    @Override
    public boolean add(T item) {
        int index = calculateIndex(item);

        //noinspection unchecked
        ArrayList<T> arrayList = (ArrayList<T>) items[index];

        if (items[index] == null) {
            arrayList = new ArrayList<>();
        }

        //noinspection unchecked
        items[index] = (T) arrayList;
        arrayList.add(item);
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object item) {
        //noinspection unchecked
        int index = calculateIndex((T) item);

        if (items[index] == null) {
            System.out.println("Элемент не найден");
            return false;
        }

        //noinspection unchecked
        ArrayList<T> arrayList = (ArrayList<T>) items[index];

        //noinspection unchecked
        int itemIndex = indexOf((T) item);

        if (itemIndex != -1) {
            arrayList.remove(itemIndex);
            modCount++;

            return true;
        }

        return false;
    }

    private int indexOf(T item) {
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

    @Override
    public boolean contains(Object item) {
        //noinspection unchecked
        return indexOf((T) item) > -1;
    }

    @Override
    public void clear() {
        for (int i = length - 1; i >= 0; i--) {
            set(i, null);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean modified = false;

        for (T item : collection) {
            add(item);
            modified = true;
        }

        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (var item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;

        for (var item : collection) {
            if (contains(item)) {
                remove(item);
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;

        if (collection.size() == 0) {
            this.clear();
        }

        for (T item : items) {
            if (item != null) {
                //noinspection unchecked
                ArrayList<T> arrayList = (ArrayList<T>) item;

                for (int j = 0; j < arrayList.size(); j++) {
                    if (!collection.contains(arrayList.get(j))) {
                        remove(arrayList.get(j));
                        modified = true;
                        j--;
                    }
                }
            }
        }

        return modified;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <E> E[] toArray(E[] array) {
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, length);

        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int startModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("Изменение коллекции недопустимо");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }
}