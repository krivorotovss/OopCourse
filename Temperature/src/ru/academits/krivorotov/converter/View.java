package ru.academits.krivorotov.converter;

import javax.swing.*;
import java.awt.*;

public class View {
    public JLabel outputTemperatureLabel;
    private final JTextField inputTemperatureTextField;
    public JLabel outputResultLabel;
    private final JTextField inputScaleTextField;
    private final JButton outputCelsiusButton;
    private final JButton outputFahrenheitButton;
    private final JButton outputKelvinButton;

    public View(String title) {
        // Create the principal frame
        JFrame frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Create UI elements
        JLabel inputTemperatureLabel = new JLabel("Введите температуру: ");
        outputTemperatureLabel = new JLabel("Результат: ");
        JLabel inputScaleLabel = new JLabel("Выберите шкалу (C,F,K): ");
        inputTemperatureTextField = new JTextField();
        outputResultLabel = new JLabel("zero");
        inputScaleTextField = new JTextField();
        outputCelsiusButton = new JButton("Перевести в C");
        outputFahrenheitButton = new JButton("Перевести в F");
        outputKelvinButton = new JButton("Перевести в K");

        // Add UI elements to frame
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputTemperatureLabel)
                                .addComponent(inputScaleLabel)
                                .addComponent(outputTemperatureLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputTemperatureTextField)
                                .addComponent(inputScaleTextField)
                                .addComponent(outputResultLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(outputCelsiusButton)
                                .addComponent(outputFahrenheitButton)
                                .addComponent(outputKelvinButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputTemperatureLabel)
                                .addComponent(inputTemperatureTextField)
                                .addComponent(outputCelsiusButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputScaleLabel)
                                .addComponent(inputScaleTextField)
                                .addComponent(outputFahrenheitButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(outputTemperatureLabel)
                                .addComponent(outputResultLabel)
                                .addComponent(outputKelvinButton))
        );

        frame.getContentPane().setLayout(layout);
    }

    public JTextField getInputTemperatureTextField() {
        return inputTemperatureTextField;
    }

    public void setOutputResultLabel(String outputResultLabel) {
        this.outputResultLabel.setText(outputResultLabel);
    }

    public JButton getOutputCelsiusButton() {
        return outputCelsiusButton;
    }

    public JButton getOutputFahrenheitButton() {
        return outputFahrenheitButton;
    }

    public JButton getOutputKelvinButton() {
        return outputKelvinButton;
    }

    public JTextField getInputScaleTextField() {
        return inputScaleTextField;
    }
}