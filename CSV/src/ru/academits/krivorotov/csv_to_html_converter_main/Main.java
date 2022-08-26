package ru.academits.krivorotov.csv_to_html_converter_main;

import ru.academits.krivorotov.csv_to_html_converter.CsvToHtmlConverter;

public class Main {
    public static void main(String[] args) {
        String pathToInputFile = "CSV/src/example.csv";
        String pathToOutputFile = "CSV/src/output.html";

        CsvToHtmlConverter converter = new CsvToHtmlConverter();
        converter.convertAndWriteHtmlFile(pathToInputFile, pathToOutputFile);
    }
}