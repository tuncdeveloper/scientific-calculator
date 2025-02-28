package org.example.gui;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.CalculatorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleInputPanel extends JPanel {
    private JTextField textFieldSingle;
    private JTextField textFieldResult;
    private CalculatorService calculatorService;

    public SingleInputPanel(JTextField textFieldResult, CalculatorService calculatorService) {
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

        JLabel labelSingle = new JLabel("Input A:");
        labelSingle.setFont(new Font("Arial", Font.BOLD, 14));

        textFieldSingle = new JTextField();
        textFieldSingle.setPreferredSize(new Dimension(100, 30));
        textFieldSingle.setFont(new Font("Arial", Font.BOLD, 16));
        textFieldSingle.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2)); // Adding border for input field

        // Modern combo box design with FlatLaf styling
        JComboBox<String> singleOperationBox = new JComboBox<>(new String[] {
                "√", "log", "ln", "sin", "cos", "tan", "cot", "sec", "csc", "!"
        });
        singleOperationBox.setFont(new Font("Arial", Font.BOLD, 16));
        singleOperationBox.setBackground(new Color(30, 144, 255));  // Modern blue background
        singleOperationBox.setForeground(Color.WHITE);  // White text color
        singleOperationBox.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));  // Border color

        JButton calculateSingleButton = new JButton("Calculate");
        calculateSingleButton.setBackground(new Color(0, 123, 255));
        calculateSingleButton.setForeground(Color.white);
        calculateSingleButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateSingleButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2)); // Adding border for the button

        calculateSingleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textFieldSingle.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Input A must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double a = parseDoubleOrShowError(textFieldSingle.getText());

                    if (a == Double.MIN_VALUE) {
                        return;
                    }

                    String operation = (String) singleOperationBox.getSelectedItem();
                    double result = calculatorService.calculateSingle(operation, a);
                    textFieldResult.setText(String.valueOf(result));
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid operation: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    textFieldResult.setText("Error");
                }
            }
        });

        inputPanel.add(labelSingle);
        inputPanel.add(textFieldSingle);
        inputPanel.add(new JLabel("Operation:"));
        inputPanel.add(singleOperationBox);

        add(inputPanel);
        add(calculateSingleButton);
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