package ru.academits.krivorotov.singly_linked_list;

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
        StringBuilder builder = new StringBuilder();

        if (size == 0) {
            builder.append("[]");
            return builder.toString();
        }

        builder.append("{");
        for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            builder.append(currentNode.getData()).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length() - 1);
        builder.setCharAt(builder.length() - 1, '}');

        return builder.toString();
    }

    public boolean compare(T string1, T string2) {
        if (string1 == null || string2 == null) {
            return Objects.equals(string1, string2);
        }

        return string1.equals(string2);
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("addByIndex, index должен быть: 0 >= index <= size,  size = " + size + ", index = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> currentNode = searchNode(index - 1);
        currentNode.setNext(new ListItem<>(data, currentNode.getNext()));

        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("removeFirst, Список пуст, size = 0");
        }

        ListItem<T> currentNode = head;
        T deletedNodeData = currentNode.getData();
        head = head.getNext();
        size--;

        return deletedNodeData;
    }

    public T removeByIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("removeByIndex, список пуст, size = 0");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("removeByIndex, index должен быть: 0 >= index < size,  size = " + size + ", index = " + index);
        }

        ListItem<T> currentNode = head;
        ListItem<T> previousNode = null;

        if (index == 0) {
            return removeFirst();
        }

        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        T deletedNodeData = currentNode.getData();
        previousNode.setNext(currentNode.getNext());

        size--;

        return deletedNodeData;
    }

    public boolean removeByData(T data) {
        for (ListItem<T> currentNode = head, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (compare(data, currentNode.getData())) {
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
        if (size == 0) {
            throw new IndexOutOfBoundsException("getFirst, список пуст, size = 0");
        }

        ListItem<T> currentNode = head;

        return currentNode.getData();
    }

    public T getByIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("getByIndex, список пуст, size = 0");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("getByIndex, index должен быть: 0 >= index < size,  size = " + size + ", index = " + index);
        }

        ListItem<T> currentNode = searchNode(index);

        return currentNode.getData();
    }

    public T setByIndex(int index, T data) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("setByIndex, список пуст, size = 0");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("setByIndex, index должен быть: 0 >= index < size,  size = " + size + ", index = " + index);
        }

        ListItem<T> currentNode = searchNode(index);
        T previousData = currentNode.getData();
        currentNode.setData(data);

        return previousData;
    }

    public void reverse() {
        ListItem<T> previousNode = null;
        ListItem<T> currentNode = head;
        ListItem<T> next;

        while (currentNode != null) {
            next = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = next;
        }

        head = previousNode;
    }

    public void add(T data) {
        ListItem<T> currentNode = head;

        if (head == null) {
            addFirst(data);
        } else {
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            ListItem<T> newItem = new ListItem<>(data);
            currentNode.setNext(newItem);

            size++;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();
        for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            list.add(currentNode.getData());
        }

        return list;
    }

    public ListItem<T> searchNode(int index) {
        ListItem<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
}