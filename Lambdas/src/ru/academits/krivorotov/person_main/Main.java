package ru.academits.krivorotov.person_main;

import ru.academits.krivorotov.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Иван", 17),
                new Person("Федор", 16),
                new Person("Сергей", 25),
                new Person("Петр", 30),
                new Person("Сергей", 18),
                new Person("Петр", 24));


        System.out.println("Вывод списка: " + persons);
        System.out.println();

        //А
        List<Person> uniqueNames1 = persons.stream()
                .distinct().toList();

        System.out.println("Список уникальных имен:");

        uniqueNames1.forEach((person) ->
                System.out.printf("%s%n", person));

        System.out.println();

        //Б
        String uniqueNames2 = persons.stream()
                .distinct()
                .sorted()
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Имена: " + uniqueNames2);
        System.out.println();

        // В
        System.out.println("Имена из списка, отсортированы по возрасту < 18:");

        List<Person> listNames1 = persons.stream()
                .filter(p -> p.getAge() < 18).toList();

        listNames1.forEach((person) ->
                System.out.printf("%s%n", person));
        System.out.println();

        OptionalDouble listAges1 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average();

        System.out.println("Средний возраст людей, возрастом до 18: " + listAges1);
        System.out.println();

        // Г
        Map<Object, List<Person>> personsByName = // работает пример, сортировка по возрасту
                persons.stream().collect(Collectors.groupingBy(Person::getName));

        OptionalDouble listAges2 = persons.stream()
                .mapToDouble(Person::getAge)
                .average();

        personsByName.forEach((person, age) ->
                System.out.printf("%s: %s%n", person, listAges2));
        System.out.println();

        // Д
        System.out.println("Имена из списка, отсортированы по возрасту от 20 до 45, в порядке убывания возраста:");

        List<Person> listNames2 = persons.stream()
                .filter(p -> p.getAge() > 20 && p.getAge() < 45)
                .sorted(Collections.reverseOrder(Comparator.comparingInt(Person::getAge))).toList();

        listNames2.forEach((person) ->
                System.out.printf("%s%n", person));
        System.out.println();
    }
}