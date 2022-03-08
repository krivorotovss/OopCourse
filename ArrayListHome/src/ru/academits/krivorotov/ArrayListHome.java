package ru.academits.krivorotov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> numbers1 = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("ArrayListHome/src/ru/academits/krivorotov/input.txt"))) {
            while (scanner.hasNextInt()) {
                numbers1.add(scanner.nextInt());
            }

            System.out.println("Исходный arrayList: " + numbers1);

            numbers1.removeIf(integer -> integer % 2 == 0);
            System.out.println("Удалены четные числа: " + numbers1);

            ArrayList<Integer> numbers2 = new ArrayList<>();

            for (Integer number : numbers1) {
                if (!numbers2.contains(number)) {
                    numbers2.add(number);
                }
            }

            System.out.println("Новый arrayList без дубликатов: " + numbers2);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден");
        }
    }
}