package ru.academits.krivorotov.temperature_converter;

import java.util.List;

public class Converter implements IConverter {
    private final List<Scale> scales;

    public Converter(List<Scale> listScale) {
        scales = listScale;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(inputTemperature));
    }
}