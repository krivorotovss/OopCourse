package ru.academits.krivorotov.tree_main;

import ru.academits.krivorotov.tree.Tree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbers1 = new Tree<>();

        numbers1.add(8);
        numbers1.add(3);
        numbers1.add(2);
        numbers1.add(5);
        numbers1.add(7);
        numbers1.add(9);
        numbers1.add(12);
        numbers1.add(10);
        numbers1.add(15);
        numbers1.add(16);

        System.out.println("Количество элементов numbers1= " + numbers1.getNodesCount());
        System.out.println();

        System.out.println("Обход в ширину numbers1:");
        numbers1.widthTraversalAndPrint();
        System.out.println();
        System.out.println();

        System.out.println("Поиск 8: " + numbers1.search(8));
        System.out.println("Удалить 8:");
        numbers1.remove(8);
        System.out.println("Поиск 8: " + numbers1.search(8));
        System.out.println();

        System.out.println("Поиск 15: " + numbers1.search(15));
        System.out.println("Удалить 15:");
        numbers1.remove(15);
        System.out.println("Поиск 15: " + numbers1.search(15));

        System.out.println("Количество элементов numbers1 = " + numbers1.getNodesCount());
        System.out.println();

        System.out.println("Обход в ширину numbers1:");
        numbers1.widthTraversalAndPrint();
        System.out.println();

        System.out.println("Обход в глубину с рекурсией numbers1:");
        numbers1.depthTraversalAndPrintWithRecursion();
        System.out.println();

        System.out.println("Обход в глубину numbers1:");
        numbers1.depthTraversalAndPrint();
        System.out.println();
        System.out.println();

        Comparator<Integer> comparator = (number1, number2) -> {
            if (number1 == null) {
                if (number2 == null) {
                    return 0;
                }

                return -1;
            }

            if (number2 == null) {
                return 1;
            }

            return number1.compareTo(number2);
        };

        Tree<Integer> numbers2 = new Tree<>(comparator);

        numbers2.add(8);
        numbers2.add(3);
        numbers2.add(2);
        numbers2.add(7);
        numbers2.add(5);
        numbers2.add(9);
        numbers2.add(12);
        numbers2.add(10);
        numbers2.add(6);
        numbers2.add(4);
        numbers2.add(15);
        numbers2.add(13);
        numbers2.add(14);

        System.out.println("Обход в глубину numbers2:");
        numbers2.depthTraversalAndPrint();
        System.out.println();
        System.out.println();

        System.out.println("Количество элементов numbers2 = " + numbers2.getNodesCount());
    }
}