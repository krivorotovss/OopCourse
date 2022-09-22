package ru.academits.krivorotov.converter;

import javax.swing.*;
import java.awt.*;

public class View {
    private final JButton okButton;
    private final JTextField inputTemperatureTextField;
    private final JComboBox<Scale> inputScalesComboBox;
    private final JComboBox<Scale> outputScalesComboBox;
    private final JLabel outputResultLabel;
    private final JFrame frame;

    public View(String title) {
        // Create the principal frame
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 250);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        CelsiusScale celsiusScale = new CelsiusScale();
        FahrenheitScale fahrenheitScale = new FahrenheitScale();
        KelvinScale kelvinScale = new KelvinScale();

        Scale[] items = {
                celsiusScale,
                fahrenheitScale,
                kelvinScale
        };

        // Create UI elements (здесь элементы по порядку их отображения на форме)
        JLabel inputTemperatureLabel = new JLabel("Введите температуру: ");
        JLabel inputScaleLabel = new JLabel("Выберите исходную шкалу: ");
        JLabel outputScaleLabel = new JLabel("Выберите шкалу для перевода: ");
        JLabel outputTemperatureLabel = new JLabel("Результат: ");

        inputTemperatureTextField = new JTextField("0");
        inputScalesComboBox = new JComboBox<>(items);
        outputScalesComboBox = new JComboBox<>(items);
        outputResultLabel = new JLabel("zero");

        okButton = new JButton("Перевести");
        okButton.setPreferredSize(new Dimension(150, 30));

        // Add UI elements to frame
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputTemperatureLabel)
                                .addComponent(inputScaleLabel)
                                .addComponent(outputScaleLabel)
                                .addComponent(outputTemperatureLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputTemperatureTextField)
                                .addComponent(inputScalesComboBox)
                                .addComponent(outputScalesComboBox)
                                .addComponent(outputResultLabel)
                                .addComponent(okButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputTemperatureLabel)
                                .addComponent(inputTemperatureTextField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(inputScaleLabel)
                                .addComponent(inputScalesComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(outputScaleLabel)
                                .addComponent(outputScalesComboBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(outputTemperatureLabel)
                                .addComponent(outputResultLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(okButton))
        );

        frame.getContentPane().setLayout(layout);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JTextField getInputTemperatureTextField() {
        return inputTemperatureTextField;
    }

    public JComboBox<Scale> getInputScalesComboBox() {
        return inputScalesComboBox;
    }

    public JComboBox<Scale> getOutputScalesComboBox() {
        return outputScalesComboBox;
    }

    public JLabel getOutputResultLabel() {
        return outputResultLabel;
    }

    public JFrame getFrame() {
        return frame;
    }
}