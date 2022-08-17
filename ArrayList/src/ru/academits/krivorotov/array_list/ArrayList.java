package ru.academits.krivorotov.array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Значение initialCapacity должно быть не меньше 0, initialCapacity = " + initialCapacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
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
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

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

        if (size == arrayList.size) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null && items[i] != arrayList.items[i]) {
                    return false;
                } else if (items[i] == null && items[i] == arrayList.items[i]) {
                    continue;
                }

                if (!items[i].equals(arrayList.items[i])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;

        for (T element : this)
            hash = hash * prime + (element == null ? 0 : element.hashCode());

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (size == 0) {
            return "[]";
        }


        for (int i = 0; i < size; i++) {
            builder.append(" ").append(items[i]).append(",");
        }

        builder.setCharAt(0, '[');
        builder.setCharAt(builder.length() - 1, ']');

        return builder.toString();
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = size - 1; i >= 0; i--) {
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index <= length, index = " + index);
        }

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public boolean add(T item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index <= length, index = " + index);
        }

        if (collection.size() == 0) {
            return false;
        }

        ensureCapacity(size + collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        size = size + collection.size();
        int i = index;

        for (T item : collection) {
            items[i] = item;
            i++;
        }

        modCount++;

        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        modCount++;

        size = 0;
    }

    // Сохраняет только те элементы в этой коллекции, которые содержатся в указанной коллекции (необязательная операция).
    // Другими словами, удаляет из этой коллекции все ее элементы, которые не содержатся в указанной коллекции.
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isModified = false;

        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                isModified = true;
                i--;
            }
        }

        return isModified;
    }

    // Удаляет из этого списка все его элементы, содержащиеся в указанной коллекции
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isModified = false;

        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                isModified = true;
                i--;
            }
        }

        return isModified;
    }

    // Возвращает значение true, если этот список содержит все элементы указанной коллекции
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
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
        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(items, size);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;

        size--;
        modCount++;

        return removedItem;
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
        if (size == 0) {
            items = Arrays.copyOf(items, 10);
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity > size) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int startModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index < length, index = " + index);
        }
    }
}