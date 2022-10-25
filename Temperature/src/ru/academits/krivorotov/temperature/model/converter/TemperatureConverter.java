package ru.academits.krivorotov.temperature.model.converter;

import ru.academits.krivorotov.temperature.model.scales.Scale;

import java.util.List;

public class TemperatureConverter implements Converter {
    private final List<Scale> scales;

    public TemperatureConverter(List<Scale> scalesList) {
        scales = scalesList;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(inputTemperature));
    }
}