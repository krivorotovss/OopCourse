package ru.academits.krivorotov.converter_main;

import ru.academits.krivorotov.converter.Converter;
import ru.academits.krivorotov.converter.View;

public class Main {
    public static void main(String[] args) {
        View view = new View("Конвертер температуры");
        Converter converter = new Converter(view);
        converter.initConverter();
    }
}