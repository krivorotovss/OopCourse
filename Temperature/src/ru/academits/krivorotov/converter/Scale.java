package ru.academits.krivorotov.converter;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
