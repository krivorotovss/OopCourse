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

        System.out.println(persons);

//        Collections.sort(persons, (a, b) -> a.compareTo(b));

//        persons.stream()

        List<Person> filtered = persons.stream() // работает пример, фильтр по имени
                .filter(p -> p.getName().equals("Сергей"))
                .collect(Collectors.toList());

        System.out.println(filtered);


        Map<Object, List<Person>> personsByAge = // работает пример, сортировка по возрасту
                persons.stream().collect(Collectors.groupingBy(person -> person.getAge()));

        personsByAge.forEach((age, person) ->
                System.out.printf("age %s: %s%n", age, person));


        List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 4, 4); // работает пример, уникальные числа

        List<Integer> uniqueNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueNumbers);



        System.out.println("==============================================");
        //ЭТО ДЛЯ ЗАДАЧИ

        //А
        List<Person> uniqueNames1 = persons.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Список уникальных имен:");

        uniqueNames1.forEach((person) ->
                System.out.printf("%s%n", person));

        System.out.println();

        //=================================================

        //Б
        String uniqueNames3 = persons.stream()
                .sorted()
                .distinct()
                .map(person -> person.getName())
                .collect(Collectors.joining(", " ));

        System.out.println("Имена: " + uniqueNames3);
        System.out.println();

        //================================================


////        List<Person> listNames = persons.stream()
//            List<Person> listNames = persons.stream()
//                .filter(p -> p.getAge() < 18)
//
////                .mapToInt((s) -> Integer.parseInt(s)).average()
//                    .mapToInt(p -> p.getAge()).average()
//                    .sorted()
//                .collect(Collectors.toList());
////                    .forEach(System.out::println);
//
////        listNames.getAverage();

        System.out.println("Имена из списка, отсортированы по возрасту < 18:");

//        listNames.forEach((person) ->
//                System.out.printf("%s%n", person));

        String uniqueNames3 = persons.stream()
                .sorted()
                .distinct()
                .map(person -> person.getName())
                .collect(Collectors.joining(", " ));

        System.out.println("Имена: " + uniqueNames3);
        System.out.println();

        int sumAge = persons.stream()
                .mapToInt(age -> age.getAge())
                .filter(age -> age < 18)
//                .average()
                .collect(Collectors.toList());



    }


}
