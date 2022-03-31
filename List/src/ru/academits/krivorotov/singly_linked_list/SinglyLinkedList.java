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

        for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            builder.append(currentNode.getData()).append(", ");
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
        if (head == null) {
            addFirst(data);
        } else {
            int index = 1;

            for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
                if (currentNode.getNext() == null) {
                    addByIndex(index, data);
                    break;
                }

                index++;
            }
        }
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index должен быть: 0 >= index <= size,  size = " + size + ", index = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousNode = getNode(index - 1);
        previousNode.setNext(new ListItem<>(data, previousNode.getNext()));

        size++;
    }

    public T removeFirst() {
        checkIndex();

        T removedData = head.getData();
        head = head.getNext();
        size--;

        return removedData;
    }

    public T removeByIndex(int index) {
        checkSize(index);
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousNode = getNode(index - 1);
        ListItem<T> currentNode = getNode(index - 1).getNext();

        T removedData = currentNode.getData();
        previousNode.setNext(currentNode.getNext());

        size--;

        return removedData;
    }

    public boolean removeByData(T data) {
        for (ListItem<T> currentNode = head, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(data, currentNode.getData())) {
                if (previousNode == null) {
                    removeFirst();
                    return true;
                }

                previousNode.setNext(currentNode.getNext());
                size--;

                return true;
            }
        }

        return false;
    }

    public T getFirst() {
        checkIndex();

        return head.getData();
    }

    public T getByIndex(int index) {
        checkSize(index);
        checkIndex(index);

        return getNode(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkSize(index);
        checkIndex(index);

        ListItem<T> currentNode = getNode(index);
        T oldData = currentNode.getData();
        currentNode.setData(data);

        return oldData;
    }

    public void reverse() {
        ListItem<T> previousNode = null;
        ListItem<T> currentNode = head;

        while (currentNode != null) {
            ListItem<T> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();

        int index = 0;

        for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext(), index++) {
            listCopy.addByIndex(index, currentNode.getData());
        }

        return listCopy;
    }

    private ListItem<T> getNode(int index) {
        ListItem<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    private void checkSize(int index) {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст, size = 0, index = " + index);
        }
    }

    private void checkIndex() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст, size = 0");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index должен быть: 0 >= index < size,  size = " + size + ", index = " + index);
        }
    }
}