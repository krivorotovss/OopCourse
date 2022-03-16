package ru.academits.krivorotov.arrayListHome;

import java.io.*;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome/src/ru/academits/krivorotov/input.txt"))) {
            String line;
            ArrayList<Integer> numbers1 = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                numbers1.add(Integer.parseInt(line));
            }

            System.out.println("arrayList из файла: " + numbers1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 1, 4, 6, 8, 10, 1, 3, 5, 1));
        System.out.println("Исходный arrayList: " + numbers1);

        for (int i = 0; i < numbers1.size(); ) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
            } else {
                i++;
            }
        }

        System.out.println("Удалены четные числа из arrayList: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(numbers1.size());

        for (Integer number : numbers1) {
            if (!numbers2.contains(number)) {
                numbers2.add(number);
            }
        }

        System.out.println("Новый arrayList без дубликатов: " + numbers2);
    }
}