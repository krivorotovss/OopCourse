package ru.academits.krivorotov.converter;

import javax.swing.*;

public record Converter(View view) {
    public void initConverter() {
        view.getOkButton().addActionListener(e -> {
            try {
                double inputTemperature = Double.parseDouble(view.getInputTemperatureTextField().getText());

                Scale inputScale = (Scale) view.getInputScalesComboBox().getSelectedItem();
                Scale outputScale = (Scale) view.getOutputScalesComboBox().getSelectedItem();

                assert inputScale != null;
                assert outputScale != null;
                double outputTemperature = convertTemperature(inputScale, outputScale, inputTemperature);

                view.getOutputResultLabel().setText(String.format("%.2f", outputTemperature));
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(view.getFrame(), "Значение температуры должно быть числом",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(inputTemperature));
    }
}