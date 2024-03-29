package ru.academits.krivorotov.tree_main;

import ru.academits.krivorotov.tree.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
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

        Tree<Integer> numbers1 = new Tree<>(comparator);

        numbers1.add(8);
        numbers1.add(3);
        numbers1.add(2);
        numbers1.add(7);
        numbers1.add(5);
        numbers1.add(9);
        numbers1.add(12);
        numbers1.add(10);
        numbers1.add(15);
        numbers1.add(16);

        System.out.println("Количество элементов numbers1 = " + numbers1.getCount());
        System.out.println();

        System.out.println("Обход в ширину numbers1:");
        Consumer<Integer> consumer = number -> System.out.print(number + " ");
        numbers1.widthTraversal(consumer);
        System.out.println();
        System.out.println();

        System.out.println("Поиск 8: " + numbers1.contains(8));
        System.out.println("Удалить 8: " + numbers1.remove(8));
        System.out.println("Поиск 8: " + numbers1.contains(8));
        System.out.println("Количество элементов numbers1 = " + numbers1.getCount());
        System.out.println();

        System.out.println("Обход в ширину numbers1:");
        numbers1.widthTraversal(consumer);
        System.out.println();

        System.out.println("Поиск 15: " + numbers1.contains(15));
        System.out.println("Удалить 15: " + numbers1.remove(15));
        System.out.println("Поиск 15: " + numbers1.contains(15));
        System.out.println("Удалить 15: " + numbers1.remove(15));

        System.out.println("Количество элементов numbers 1 = " + numbers1.getCount());
        System.out.println();

        System.out.println("Обход в ширину numbers1:");
        numbers1.widthTraversal(consumer);
        System.out.println();

        System.out.println("Обход в глубину с рекурсией numbers1:");
        numbers1.depthTraversalWithRecursion(consumer);
        System.out.println();

        System.out.println("Обход в глубину numbers1:");
        numbers1.depthTraversal(consumer);
        System.out.println();
        System.out.println();

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
        numbers2.add(16);

        System.out.println("Обход в глубину numbers2:");
        numbers2.depthTraversal(consumer);
        System.out.println();
        System.out.println("Обход в ширину numbers2:");
        numbers2.widthTraversal(consumer);
        System.out.println();

        System.out.println("Количество элементов numbers2 = " + numbers2.getCount());

        System.out.println("numbers2 поиск 15: " + numbers2.contains(15));
        System.out.println("numbers2 удалить 15: " + numbers2.remove(15));
        System.out.println("numbers2 поиск 15: " + numbers2.contains(15));
        System.out.println();

        System.out.println("Обход в ширину numbers2:");
        numbers2.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers2 = " + numbers2.getCount());
        System.out.println();

        Tree<Integer> numbers3 = new Tree<>(comparator);

        System.out.println("numbers3 поиск 15: " + numbers3.contains(15));
        System.out.println("numbers3 удалить 15: " + numbers3.remove(15));
        System.out.println("numbers3 поиск 15: " + numbers3.contains(15));
        System.out.println();

        numbers3.add(8);
        numbers3.add(3);
        numbers3.add(2);
        numbers3.add(7);
        numbers3.add(13);
        numbers3.add(10);
        numbers3.add(11);
        numbers3.add(14);
        numbers3.add(12);

        System.out.println("Обход в ширину numbers3:");
        numbers3.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers3 = " + numbers3.getCount());
        System.out.println();

        System.out.println("Удалить 8: " + numbers3.remove(8));
        System.out.println("Обход в ширину numbers3:");
        numbers3.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers3 = " + numbers3.getCount());
        System.out.println();

        System.out.println("Обход в ширину numbers2:");
        numbers2.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers2 = " + numbers2.getCount());
        System.out.println();

        System.out.println("Удалить 12: " + numbers2.remove(12));
        System.out.println("Обход в ширину numbers2:");
        numbers2.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers2 = " + numbers2.getCount());
        System.out.println();

        System.out.println("Удалить 5: " + numbers2.remove(5));
        System.out.println("Обход в ширину numbers2:");
        numbers2.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers2 = " + numbers2.getCount());

        Tree<Integer> numbers4 = new Tree<>(comparator);

        numbers4.add(20);
        numbers4.add(2);
        numbers4.add(18);
        numbers4.add(10);
        numbers4.add(4);
        numbers4.add(14);
        numbers4.add(12);
        numbers4.add(16);
        numbers4.add(13);

        System.out.println("Обход в ширину numbers4:");
        numbers4.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers4 = " + numbers4.getCount());
        System.out.println();

        System.out.println("numbers4 удалить 10: " + numbers4.remove(10));
        System.out.println("numbers4 поиск 10: " + numbers4.contains(10));
        System.out.println();

        System.out.println("Обход в ширину numbers4:");
        numbers4.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers4 = " + numbers4.getCount());
        System.out.println();

        Tree<Integer> numbers5 = new Tree<>();

        numbers5.add(20);
        numbers5.add(2);
        numbers5.add(18);
        numbers5.add(10);

        System.out.println("Обход в ширину numbers5:");
        numbers5.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers5 = " + numbers5.getCount());
        System.out.println();

        Tree<Integer> numbers6 = new Tree<>();

        numbers6.add(20);
        numbers6.add(21);
        numbers6.add(22);
        numbers6.add(20);

        numbers6.remove(20);

        System.out.println("Обход в ширину numbers6:");
        numbers6.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers6 = " + numbers6.getCount());
        System.out.println();

        Tree<Integer> numbers7 = new Tree<>();

        numbers7.add(20);
        numbers7.add(18);
        numbers7.add(17);
        numbers7.add(19);

        numbers7.remove(20);

        System.out.println("Обход в ширину numbers7:");
        numbers7.widthTraversal(consumer);
        System.out.println();
        System.out.println("Количество элементов numbers7 = " + numbers7.getCount());
        System.out.println();
    }
}