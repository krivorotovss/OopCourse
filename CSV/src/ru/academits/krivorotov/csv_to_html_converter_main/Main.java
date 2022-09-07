package ru.academits.krivorotov.csv_to_html_converter_main;

import ru.academits.krivorotov.csv_to_html_converter.CsvToHtmlConverter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Необходимо передать 2 аргумента: " + System.lineSeparator() +
                    "1.Путь к файлу с расширением csv, который нужно конвертировать; " + System.lineSeparator() +
                    "2.Путь к файлу с расширением html, для вывода результата.");
            return;
        }

        String pathToInputFile = args[0];
        String pathToOutputFile = args[1];

        CsvToHtmlConverter converter = new CsvToHtmlConverter();
        converter.convertAndWriteHtmlFile(pathToInputFile, pathToOutputFile);
    }
}