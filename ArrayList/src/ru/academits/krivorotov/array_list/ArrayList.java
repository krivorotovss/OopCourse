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
        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
    }

    public ArrayList(Object[] array) {
        //noinspection unchecked
        items = (T[]) new Object[array.length];

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(array, 0, items, 0, array.length);
        length = items.length;
    }

    public int size() {
        return length;
    }

    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index < length, index = " + index);
        }

        return items[index];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Значение index, должно быть: 0 <= index < length, index = " + index);
        }

        if (items[index] == null) {
            length++;
        }

        modCount++;

        return items[index] = element;
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

        return Arrays.equals(items, arrayList.items) && length == arrayList.length;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(items) + length;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    @Override
    public int indexOf(Object element) {
        for (int index = 0; index < length; index++) {
            if (Objects.equals(items[index], element)) {

                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int index = length - 1; index > 0; index--) {
            if (Objects.equals(items[index], element)) {

                return index;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object element) {
        for (int index = 0; index < length; index++) {
            if (Objects.equals(items[index], element)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Значение index < 0 || index > size, size = " + size());
        }

        if (length == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        length++;
        modCount++;
    }

    @Override
    public boolean add(T element) {
        if (length >= items.length) {
            increaseCapacity();
        }

        items[length] = element;
        length++;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        Object[] array = collection.toArray();
        ArrayList<T> list = new ArrayList<>(array);

        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        if (list.length == 0) {
            throw new NoSuchElementException("Переданный список пуст, list.length = " + list.length);
        }

        int currentListLength = list.length;

        for (T t : list) {
            add(t);
        }

        return currentListLength != length;
    }

    @Override
    public boolean addAll(int startIndex, Collection collection) {
        Object[] array = collection.toArray();
        ArrayList<T> list = new ArrayList<>(array);

        int currentListLength = length;

        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        if (list.length == 0) {
            throw new NoSuchElementException("Переданный список пуст, list.length = " + list.length);
        }

        for (int i = 0; i < currentListLength; i++) {
            add(startIndex, list.items[i]);
            startIndex++;
        }

        return currentListLength != length;
    }

    @Override
    public void clear() {
        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        for (int index = length - 1; index >= 0; index--) {
            this.remove(index);
            modCount++;
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        Object[] array = collection.toArray();
        ArrayList<T> list = new ArrayList<>(array);

        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        if (list.length == 0) {
            throw new NoSuchElementException("Переданный список пуст, list.length = " + list.length);
        }

        if (length >= list.length) {
            while (list.items.length < items.length) {
                increaseCapacity(list);
            }

            System.arraycopy(list.items, 0, list.items, 0, length);
        }

        ArrayList<T> listCopy = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (Objects.equals(items[i], list.items[j])) {
                    listCopy.add(items[i]);
                }
            }
        }

        if (listCopy.length != 0) {
            this.items = listCopy.items;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        Object[] array = collection.toArray();
        ArrayList<T> list = new ArrayList<>(array);

        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        if (list.length == 0) {
            throw new NoSuchElementException("Переданный список пуст, list.length = " + list.length);
        }

        if (length >= list.length) {
            while (list.items.length < items.length) {
                increaseCapacity(list);
            }

            System.arraycopy(list.items, 0, list.items, 0, length);
        }

        int count = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (Objects.equals(items[i], list.items[j])) {
                    count++;
                }
            }
        }

        return list.length == count;
    }

    //    <T> T[] toArray(T[] a)
    //    Возвращает массив, содержащий все элементы в этом списке в правильной последовательности (от первого до последнего элемента);
    //    тип возвращаемого массива во время выполнения соответствует указанному массиву.
    @SuppressWarnings("TypeParameterHidesVisibleType")
    @Override
    public <T> T[] toArray(T[] array) {
        @SuppressWarnings("unchecked") T[] arrayCopy = (T[]) new Object[length];
        System.arraycopy(items, 0, arrayCopy, 0, length);

        return arrayCopy;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked") T[] arrayCopy = (T[]) new Object[length];
        System.arraycopy(items, 0, arrayCopy, 0, length);

        return arrayCopy;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Значение index < 0 || index > size, size = " + size());
        }

        T oldData = items[index];

        if (index < length) {
            if (length == items.length) {
                increaseCapacity();
            }

            System.arraycopy(items, index + 1, items, index, length - index);
        }

        length--;
        modCount++;

        return oldData;
    }

    @Override
    public boolean remove(Object element) {
        for (int index = 0; index < items.length; index++) {
            if (Objects.equals(items[index], element)) {
                if (length == items.length) {
                    increaseCapacity();
                }

                System.arraycopy(items, index + 1, items, index, length - index);
                length--;
                modCount++;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] array = collection.toArray();
        ArrayList<T> list = new ArrayList<>(array);

        if (length == 0) {
            throw new NoSuchElementException("Список пуст, length = " + length);
        }

        if (list.length == 0) {
            throw new NoSuchElementException("Переданный список пуст, list.length = " + list.length);
        }

        if (this.equals(list)) {
            clear();
            modCount++;

            return true;
        }

        int currentListLength = length;

        for (int i = 0; i < list.length; i++) {
            while (remove(list.items[i])) {
                modCount++;
                remove(list.items[i]);
            }
        }

        return length != currentListLength;
    }

    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void increaseCapacity(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    public void increaseCapacity(ArrayList<T> list) {
        list.items = Arrays.copyOf(list.items, list.items.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (capacity < size()) {
            increaseCapacity(capacity);
        }
    }

    public void trimToSize() {
        increaseCapacity(size());
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        int thisModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Элементов больше нет");
            }

            if (thisModCount != modCount) {
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
}