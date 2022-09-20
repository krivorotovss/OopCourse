package ru.academits.krivorotov.converter;

import javax.swing.*;

public record Converter(View view) {
    public void initConverter() {
        view.okButton.addActionListener(e -> {
            try {
                double inputTemperature = Double.parseDouble(view.inputTemperatureTextField.getText());

                Scale inputScale = (Scale) view.inputScalesComboBox.getSelectedItem();
                Scale outputScale = (Scale) view.outputScalesComboBox.getSelectedItem();

                assert inputScale != null;
                assert outputScale != null;
                double outputTemperature = convertTemperature(inputScale, outputScale, inputTemperature);

                view.outputResultLabel.setText(String.format("%.2f", outputTemperature));
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(view.frame, "Значение температуры должно быть числом",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        return outputScale.convertFromCelsius(inputScale.convertToCelsius(inputTemperature));
    }
}