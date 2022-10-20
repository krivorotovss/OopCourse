package ru.academits.krivorotov.temperature.view;

import ru.academits.krivorotov.temperature.model.converter.Converter;
import ru.academits.krivorotov.temperature.model.scales.Scale;

import javax.swing.*;
import java.awt.*;

public class View {
    private JTextField inputTemperatureTextField;
    private JComboBox<Object> inputScalesComboBox;
    private JComboBox<Object> outputScalesComboBox;
    private JLabel outputResultLabel;
    private JFrame frame;
    private final Converter converter;

    public View(Converter converter) {
        this.converter = converter;
    }

    public void initialization() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            // Create the principal frame
            frame = new JFrame("Конвертер температуры");
            frame.getContentPane().setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 250);
            frame.setMinimumSize(new Dimension(450, 250));
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);

            // Create UI elements (здесь элементы по порядку их отображения на форме)
            JLabel inputTemperatureLabel = new JLabel("Введите температуру: ");
            JLabel inputScaleLabel = new JLabel("Выберите исходную шкалу: ");
            JLabel outputScaleLabel = new JLabel("Выберите шкалу для перевода: ");
            JLabel outputTemperatureLabel = new JLabel("Результат: ");

            Object[] items = converter.getScales().toArray();

            inputTemperatureTextField = new JTextField("0");
            inputScalesComboBox = new JComboBox<>(items);
            outputScalesComboBox = new JComboBox<>(items);
            outputResultLabel = new JLabel();

            JButton okButton = new JButton("Перевести");
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

            okButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(inputTemperatureTextField.getText());

                    Scale inputScale = (Scale) inputScalesComboBox.getSelectedItem();
                    Scale outputScale = (Scale) outputScalesComboBox.getSelectedItem();

                    assert inputScale != null;
                    assert outputScale != null;
                    double outputTemperature = converter.convertTemperature(inputScale, outputScale, inputTemperature);

                    outputResultLabel.setText(String.format("%.2f", outputTemperature));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Значение температуры должно быть числом",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}