package ru.academits.krivorotov.temperature_converter;

import java.util.List;

public interface IConverter {
    List<Scale> getScales();

    double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature);
}