package ru.academits.krivorotov.temperature.model.scales;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}