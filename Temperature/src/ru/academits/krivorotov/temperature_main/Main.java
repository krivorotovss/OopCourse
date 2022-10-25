package ru.academits.krivorotov.temperature_main;

import ru.academits.krivorotov.temperature.model.converter.*;
import ru.academits.krivorotov.temperature.model.scales.CelsiusScale;
import ru.academits.krivorotov.temperature.model.scales.FahrenheitScale;
import ru.academits.krivorotov.temperature.model.scales.KelvinScale;
import ru.academits.krivorotov.temperature.model.scales.Scale;
import ru.academits.krivorotov.temperature.view.View;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scales = Arrays.asList(new CelsiusScale(), new FahrenheitScale(), new KelvinScale());

        Converter converter = new TemperatureConverter(scales);
        View view = new View(converter);
        view.start();
    }
}