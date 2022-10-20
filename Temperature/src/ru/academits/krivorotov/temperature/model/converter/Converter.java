package ru.academits.krivorotov.temperature.model.converter;

import ru.academits.krivorotov.temperature.model.scales.Scale;

import java.util.List;

public interface Converter {
    List<Scale> getScales();

    double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature);
}