package ru.academits.krivorotov.array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private int modCount = 0;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Значение initialCapacity должно быть не меньше 0, initialCapacity= " + initialCapacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
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
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        modCount++;

        return items[index] = item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked") ArrayList<T> arrayList = (ArrayList<T>) o;

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

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) > -1;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index <= length, length = " + length);
        }

        if (length == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = item;
        length++;
        modCount++;
    }

    @Override
    public boolean add(T item) {
        add(length, item);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return addAll(length, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index <= length, length = " + length);
        }

        ensureCapacity(length + collection.size());

        boolean modified = false;

        System.arraycopy(items, index, items, index + collection.size(), length - index);

        for (T item : collection) {
            items[index] = item;
            length++;
            index++;

            modified = true;
        }

        return modified;
    }

    @Override
    public void clear() {
        if (length == 0) {
            return;
        }

        for (int i = length - 1; i >= 0; i--) {
            set(i, null);
        }

        length = 0;
    }

    // Сохраняет только те элементы в этой коллекции, которые содержатся в указанной коллекции (необязательная операция).
    // Другими словами, удаляет из этой коллекции все ее элементы, которые не содержатся в указанной коллекции.
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;

        for (int i = 0; i < length; i++) {
            if (!collection.contains(items[i])) {
                remove(items[i]);
                modified = true;
                i--;
            }
        }

        return modified;
    }

    // Удаляет из этого списка все его элементы, содержащиеся в указанной коллекции
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;

        for (int i = 0; i < length; i++) {
            if (collection.contains(items[i])) {
                remove(items[i]);
                modified = true;
                i--;
            }
        }

        return modified;
    }

    // Возвращает значение true, если этот список содержит все элементы указанной коллекции
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (var item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    //    <T> T[] toArray(T[] a)
    //    Возвращает массив, содержащий все элементы в этом списке в правильной последовательности (от первого до последнего элемента);
    //    тип возвращаемого массива во время выполнения соответствует указанному массиву.
    @Override
    public <E> E[] toArray(E[] array) {
        @SuppressWarnings("unchecked") E[] arrayCopy = (E[]) new Object[length];
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, arrayCopy, 0, length);

        return arrayCopy;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T deletedItem = items[index];

        System.arraycopy(items, index + 1, items, index, length - index - 1);

        set(length - 1, null);
        length--;
        modCount++;

        return deletedItem;
    }

    @Override
    public boolean remove(Object item) {
        int itemIndex = indexOf(item);

        if (itemIndex != -1) {
            remove(itemIndex);

            return true;
        }

        return false;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2 + 1);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (length < items.length) {
            items = Arrays.copyOf(items, length);
        }
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

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<T> listIterator() { //не нужен
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) { //не нужен
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) { //не нужен
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public Spliterator<T> spliterator() { //не нужен
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) { //не нужен
    }

    @Override
    public void sort(Comparator<? super T> c) { //не нужен
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index < length, length = " + length);
        }
    }
}