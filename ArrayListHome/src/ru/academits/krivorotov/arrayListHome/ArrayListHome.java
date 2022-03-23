package ru.academits.krivorotov.arrayListHome;

import java.io.*;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome/src/ru/academits/krivorotov/input.txt"))) {
            String line;
            ArrayList<String> arrayList1 = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                arrayList1.add(line);
            }

            System.out.println("arrayList из файла: " + arrayList1);
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }

        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 1, 4, 6, 8, 10, 1, 3, 5, 1));
        System.out.println("Исходный arrayList: " + arrayList1);

        for (int i = 0; i < arrayList1.size(); ) {
            if (arrayList1.get(i) % 2 == 0) {
                arrayList1.remove(i);
            } else {
                i++;
            }
        }

        System.out.println("Удалены четные числа из arrayList: " + arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 1, 4, 6, 8, 10, 1, 3, 5, 1));
        System.out.println("Исходный arrayList: " + arrayList2);

        ArrayList<Integer> arrayList3 = new ArrayList<>(arrayList2.size());

        for (Integer number : arrayList2) {
            if (!arrayList3.contains(number)) {
                arrayList3.add(number);
            }
        }

        System.out.println("Новый arrayList без дубликатов: " + arrayList3);
    }
}