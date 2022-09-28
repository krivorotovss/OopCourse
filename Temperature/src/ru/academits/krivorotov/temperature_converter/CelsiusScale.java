package ru.academits.krivorotov.temperature_converter;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Шкала Цельсия";
    }
}