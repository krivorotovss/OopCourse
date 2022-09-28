package ru.academits.krivorotov.temperature_converter;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}