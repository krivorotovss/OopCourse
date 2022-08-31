package ru.academits.krivorotov.converter;

public record Controller(View view) {
    public void initController() {
        view.getOutputCelsiusButton().addActionListener(e -> convertToCelsius());
        view.getOutputFahrenheitButton().addActionListener(e -> convertToFahrenheit());
        view.getOutputKelvinButton().addActionListener(e -> convertToKelvin());
    }

    private void convertToCelsius() {
        try {
            String temperatureScale = view.getInputScaleTextField().getText(); // прочитали букву
            char scaleChar = Character.toUpperCase(temperatureScale.charAt(0));

            double inputTemperature = Double.parseDouble(view.getInputTemperatureTextField().getText());

            if (scaleChar == 'C' || scaleChar == 'F' || scaleChar == 'K') {
                if (scaleChar == 'C') {
                    view.setOutputResultLabel(inputTemperature + " C");
                } else if (scaleChar == 'K') {
                    double result = inputTemperature + 273.15;

                    view.setOutputResultLabel(result + " C");
                } else {
                    double result = (inputTemperature - 32) * 5 / 9;

                    view.setOutputResultLabel(result + " C");
                }
            } else {
                System.out.println("Нужно указать шкалу: C, F, K, введено: " + temperatureScale);
            }
        } catch (NumberFormatException e) {
            System.out.println("В поле температуры нужно ввести число");
        }
    }

    private void convertToFahrenheit() {
        try {
            String temperatureScale = view.getInputScaleTextField().getText();
            char scaleChar = Character.toUpperCase(temperatureScale.charAt(0));

            double inputTemperature = Double.parseDouble(view.getInputTemperatureTextField().getText());

            if (scaleChar == 'C' || scaleChar == 'F' || scaleChar == 'K') {
                if (scaleChar == 'F') {
                    view.setOutputResultLabel(inputTemperature + " F");
                } else if (scaleChar == 'K') {
                    double result = (inputTemperature - 273.15) * 1.8 + 32;

                    view.setOutputResultLabel(result + " F");
                } else {
                    double result = (inputTemperature - 32.0) / 1.8;

                    view.setOutputResultLabel(result + " F");
                }
            } else {
                System.out.println("Нужно указать шкалу: C, F, K, введено: " + temperatureScale);
            }
        } catch (NumberFormatException e) {
            System.out.println("В поле температуры нужно ввести число");
        }
    }

    private void convertToKelvin() {
        try {
            String temperatureScale = view.getInputScaleTextField().getText();
            char scaleChar = Character.toUpperCase(temperatureScale.charAt(0));

            double inputTemperature = Double.parseDouble(view.getInputTemperatureTextField().getText());

            if (scaleChar == 'C' || scaleChar == 'F' || scaleChar == 'K') {
                if (scaleChar == 'K') {
                    view.setOutputResultLabel(inputTemperature + " K");
                } else if (scaleChar == 'F') {
                    double result = (inputTemperature - 32) * 5 / 9 + 273.15;

                    view.setOutputResultLabel(result + " K");
                } else {
                    double result = inputTemperature + 273.15;

                    view.setOutputResultLabel(result + " K");
                }
            } else {
                System.out.println("Нужно указать шкалу: C, F, K, введено: " + temperatureScale);
            }
        } catch (NumberFormatException e) {
            System.out.println("В поле температуры нужно ввести число");
        }
    }
}