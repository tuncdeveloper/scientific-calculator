package org.example.gui;

import com.formdev.flatlaf.FlatLightLaf;
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

        // Set the look and feel to FlatLaf for a modern UI
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 10));
        inputPanel.setBackground(Color.white);

        // Input A
        JLabel labelA = new JLabel("Input A:");
        labelA.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font for label
        textFieldA = new JTextField();
        textFieldA.setPreferredSize(new Dimension(80, 25)); // Smaller size
        textFieldA.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font
        textFieldA.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light gray border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside the field
        ));

        // Input B
        JLabel labelB = new JLabel("Input B:");
        labelB.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font for label
        textFieldB = new JTextField();
        textFieldB.setPreferredSize(new Dimension(80, 25)); // Smaller size
        textFieldB.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font
        textFieldB.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light gray border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside the field
        ));

        // Operation ComboBox
        JComboBox<String> operationBox = new JComboBox<>(new String[] {
                "+", "-", "*", "/", "^", "%"
        });
        operationBox.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font
        operationBox.setBackground(new Color(30, 144, 255)); // Modern blue background
        operationBox.setForeground(Color.WHITE); // White text color
        operationBox.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 1)); // Thinner border

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(0, 123, 255));
        calculateButton.setForeground(Color.white);
        calculateButton.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font
        calculateButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 1)); // Thinner border
        calculateButton.setFocusPainted(false); // Remove focus border for a cleaner look

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
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid operation: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textFieldResult.setText("Error");
                }
            }
        });

        // Adding components to the panel
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