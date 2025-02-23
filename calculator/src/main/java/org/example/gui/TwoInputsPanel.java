package org.example.gui;

import org.example.CalculatorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoInputsPanel extends JPanel {
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldResult;
    private CalculatorService calculatorService;

    public TwoInputsPanel(JTextField textFieldResult, CalculatorService calculatorService) {
        this.textFieldResult = textFieldResult;
        this.calculatorService = calculatorService;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 10));
        inputPanel.setBackground(Color.white);

        JLabel labelA = new JLabel("Input A:");
        textFieldA = new JTextField();
        textFieldA.setPreferredSize(new Dimension(100, 30));
        JLabel labelB = new JLabel("Input B:");
        textFieldB = new JTextField();
        textFieldB.setPreferredSize(new Dimension(100, 30));

        JComboBox<String> operationBox = new JComboBox<>(new String[]{
                "+", "-", "*", "/", "^", "%"
        });
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(0, 123, 255));
        calculateButton.setForeground(Color.white);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textFieldA.getText().isEmpty() || textFieldB.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Both inputs must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double a = parseDoubleOrShowError(textFieldA.getText());
                    double b = parseDoubleOrShowError(textFieldB.getText());

                    if (a == Double.MIN_VALUE || b == Double.MIN_VALUE) {
                        return;
                    }

                    String operation = (String) operationBox.getSelectedItem();
                    double result = calculatorService.calculate(operation, a, b);
                    textFieldResult.setText(String.valueOf(result));
                } catch (Exception ex) {
                    textFieldResult.setText("Error");
                }
            }
        });

        inputPanel.add(labelA);
        inputPanel.add(textFieldA);
        inputPanel.add(labelB);
        inputPanel.add(textFieldB);
        inputPanel.add(new JLabel("Operation:"));
        inputPanel.add(operationBox);

        add(inputPanel);
        add(calculateButton);
    }

    private double parseDoubleOrShowError(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return Double.MIN_VALUE;
        }
    }
}
