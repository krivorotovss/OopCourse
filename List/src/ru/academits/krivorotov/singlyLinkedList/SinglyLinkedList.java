package ru.academits.krivorotov.singlyLinkedList;

import ru.academits.krivorotov.listItem.ListItem;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int count; // храним длину списка

    public SinglyLinkedList() {
        head = null;
    }

    public int getSize() {
        return count;
    }

    public void addFirst(T data) {
        ListItem<T> p = new ListItem<>(data);
        p.setNext(head);
        head = p;
        count++;
    }

    public void addByIndex(T data, int index) {
        ListItem<T> p = head;

        if (index > 0) {
            if (index > getSize()) {
                throw new IllegalArgumentException("addByIndex, index > size,  size = " + getSize());
            }

            for (int i = 1; i < index; i++) {
                p = p.getNext();
            }

            p.setNext(new ListItem<>(data, p.getNext()));
        } else {
            p = new ListItem<>(data);
            p.setNext(head);
            head = p;
        }

        count++;
    }

    public void removeFirst() {
        ListItem<T> p = head;
        System.out.println(p.getData());
        head = head.getNext();
        count--;
    }

    public void removeByIndex(int index) {
        ListItem<T> p = head;
        ListItem<T> prev = null;

        if (index > getSize()) {
            throw new IllegalArgumentException("removeByIndex, index > size,  size = " + getSize());
        }

        if (index > 0) {
            for (int i = 0; i < index; i++) {
                prev = p;
                p = p.getNext();
            }

            System.out.println(p.getData());
            prev.setNext(p.getNext());
        } else {
            removeFirst();
        }

        count--;
    }

    public void removeByData(T data) {
        int index = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (data.equals(p.getData())) {
                removeByIndex(index);
                System.out.println("true");
            }

            index++;
        }
    }

    public void print() {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }

    public void printByIndex(int index) {
        ListItem<T> p = head;

        if (index > getSize()) {
            throw new IllegalArgumentException("printByIndex, index > size,  size = " + getSize());
        }

        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }

        System.out.println(p.getData());
    }

    public T getValueFirst() {
        ListItem<T> p = head;

        return p.getData();
    }

    public T getValueByIndex(int index) {
        ListItem<T> p = head;

        if (index > getSize()) {
            throw new IllegalArgumentException("getValueByIndex, index > size,  size = " + getSize());
        }

        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }

        return p.getData();
    }

    public void setValueByIndex(int index, T data) {
        ListItem<T> p = head;

        if (index > getSize()) {
            throw new IllegalArgumentException("setValueByIndex, index > size,  size = " + getSize());
        }

        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }

        printByIndex(index);
        p.setData(data);
    }

    public void reverse() {
        ListItem<T> prev = null;
        ListItem<T> p = head;
        ListItem<T> next;

        while (p != null) {
            next = p.getNext();
            p.setNext(prev);
            prev = p;
            p = next;
        }

        head = prev;
    }

    public void add(T data) {
        ListItem<T> newItem = new ListItem<>(data);
        if (isEmpty()) {
            head = newItem;
        } else {
            tail.setNext(newItem);
        }

        tail = newItem;
        count++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void copy(SinglyLinkedList<T> list) {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            list.add(p.getData());
        }
    }
}