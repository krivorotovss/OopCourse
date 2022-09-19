package ru.academits.krivorotov.converter;

import javax.swing.*;

public record Converter(View view) {
    public void initConverter() {
        view.okButton.addActionListener(e -> {
            try {
                double inputTemperature = Double.parseDouble(view.inputTemperatureTextField.getText());

                String inputScale = (String) view.inputScalesComboBox.getSelectedItem();
                String outputScale = (String) view.outputScalesComboBox.getSelectedItem();

                assert inputScale != null;
                double outputTemperature = convertTemperature(inputScale, outputScale, inputTemperature);

                view.outputResultLabel.setText(outputTemperature + " град");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view.frame, "Значение температуры должно быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private double convertTemperature(String inputScale, String outputScale, double inputTemperature) {
        if (inputScale.equals("Градусы Цельсия")) {
            if (outputScale.equals("Градусы Фаренгейта")) {
                return inputTemperature * 9 / 5 + 32;
            }

            if (outputScale.equals("Градусы Кельвина")) {
                return inputTemperature + 273.15;
            }

            return inputTemperature;
        }

        if (inputScale.equals("Градусы Фаренгейта")) {
            if (outputScale.equals("Градусы Цельсия")) {
                return (inputTemperature - 32) * 5 / 9;
            }

            if (outputScale.equals("Градусы Кельвина")) {
                return (inputTemperature - 32) * 5 / 9 + 273.15;
            }

            return inputTemperature;
        }

        if (inputScale.equals("Градусы Кельвина")) {
            if (outputScale.equals("Градусы Цельсия")) {
                return inputTemperature - 273.15;
            }

            if (outputScale.equals("Градусы Фаренгейта")) {
                return (inputTemperature - 273.15) * 9 / 5 + 32;
            }

            return inputTemperature;
        }

        return 0.0;
    }
}