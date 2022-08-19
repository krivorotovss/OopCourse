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
                new Person("Казимир", 45),
                new Person("Петр", 24)
        );

        System.out.println("Вывод списка: " + persons);
        System.out.println();

        //А
        System.out.println("А");
        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        System.out.println("Список уникальных имен:");
        System.out.println(uniqueNamesList);
        System.out.println();

        //Б
        System.out.println("Б");
        String uniqueNamesString = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);
        System.out.println();

        // В
        System.out.println("В");
        System.out.println("Список персон возрастом < 18:");

        List<Person> personsListBefore18Years = persons.stream()
                .filter(p -> p.getAge() < 18).toList();

        System.out.println(personsListBefore18Years);
        System.out.println();

        double averageAgesPersonsListBefore18Years = personsListBefore18Years.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0.0);

        System.out.println("Средний возраст людей, возрастом до 18: " + averageAgesPersonsListBefore18Years);
        System.out.println();

        // Г
        System.out.println("Г");
        Map<String, Double> personsByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println("Средний возраст персон по имени: ");
        System.out.println(personsByName);
        System.out.println();

        // Д
        System.out.println("Д");
        System.out.println("Имена из списка, отсортированы по возрасту от 20 до 45, в порядке убывания возраста:");

        List<Person> sortedNamesList = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted(Collections.reverseOrder(Comparator.comparingInt(Person::getAge)))
                .toList();

        sortedNamesList.forEach((person) -> System.out.printf("%s%n", person));
        System.out.println();
    }
}