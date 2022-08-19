package ru.academits.krivorotov.csv_to_html_converter_main;

import ru.academits.krivorotov.csv_to_html_converter.CsvToHtmlConverter;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("CSV/src/example.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("CSV/src/output.txt"))) {
            CsvToHtmlConverter converter = new CsvToHtmlConverter();
            converter.convertAndWriteHtmlFile(reader, writer);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
}