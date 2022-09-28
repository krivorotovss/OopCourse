package ru.academits.krivorotov.temperature_converter_main;

import ru.academits.krivorotov.temperature_converter.*;
import ru.academits.krivorotov.view.View;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scales = Arrays.asList(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());

        IConverter converter = new Converter(scales);
        View view = new View(converter);
        view.initialization();
    }
}