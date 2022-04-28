package ru.academits.krivorotov.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("{");

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            builder.append(currentItem.getData()).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length() - 1);
        builder.setCharAt(builder.length() - 1, '}');

        return builder.toString();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(T data) {
        addByIndex(size, data);
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index должен быть: 0 <= index <= size,  size = " + size + ", index = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        size++;
    }

    public T removeFirst() {
        checkSize();

        T removedData = head.getData();
        head = head.getNext();
        size--;

        return removedData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        T removedData = currentItem.getData();
        previousItem.setNext(currentItem.getNext());

        size--;

        return removedData;
    }

    public boolean removeByData(T data) {
        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem == null) {
                    removeFirst();
                    return true;
                }

                previousItem.setNext(currentItem.getNext());
                size--;

                return true;
            }
        }

        return false;
    }

    public T getFirst() {
        checkSize();

        return head.getData();
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> currentItem = getItem(index);
        T oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    public void reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();
        listCopy.size = size;

        ListItem<T> currentItem = head;
        ListItem<T> currentItemCopy;
        ListItem<T> previousItemCopy;

        currentItemCopy = new ListItem<>(currentItem.getData());

        listCopy.head = currentItemCopy;
        currentItem = currentItem.getNext();
        previousItemCopy = currentItemCopy;

        while (currentItem != null) {
            currentItemCopy = new ListItem<>(currentItem.getData());

            previousItemCopy.setNext(currentItemCopy);
            currentItem = currentItem.getNext();
            previousItemCopy = currentItemCopy;
        }

        return listCopy;
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    private void checkSize() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст, size = 0");
        }
    }

    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Список пуст, size = 0, index = " + index);
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index должен быть: 0 <= index < size,  size = " + size + ", index = " + index);
        }
    }
}