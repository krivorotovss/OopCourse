package ru.academits.krivorotov.arraylisthome;

import java.io.*;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome/src/ru/academits/krivorotov/input.txt"))) {
            String line;
            ArrayList<String> linesList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }

            System.out.println("arrayList из файла: " + linesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла.");
        }

        ArrayList<Integer> numbersList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 1, 4, 6, 8, 10, 1, 3, 5, 1));
        System.out.println("Исходный arrayList: " + numbersList1);

        for (int i = 0; i < numbersList1.size(); ) {
            if (numbersList1.get(i) % 2 == 0) {
                numbersList1.remove(i);
            } else {
                i++;
            }
        }

        System.out.println("Удалены четные числа из arrayList: " + numbersList1);

        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 1, 4, 6, 8, 10, 1, 3, 5, 1));
        System.out.println("Исходный arrayList: " + numbersList2);

        ArrayList<Integer> numbersListWithOutDuplicates = new ArrayList<>(numbersList2.size());

        for (Integer number : numbersList2) {
            if (!numbersListWithOutDuplicates.contains(number)) {
                numbersListWithOutDuplicates.add(number);
            }
        }

        System.out.println("Новый arrayList без дубликатов: " + numbersListWithOutDuplicates);
    }
}