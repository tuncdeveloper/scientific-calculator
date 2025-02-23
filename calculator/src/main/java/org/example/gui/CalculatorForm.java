package org.example.gui;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.CalculatorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm {
    private JFrame frame;
    private JTextField textFieldResult;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldSingle;
    private CalculatorService calculatorService;

    public CalculatorForm() {
        // Set the look and feel to FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        calculatorService = new CalculatorService();
        frame = new JFrame("Modern Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);  // Center the window

        // Result TextField with rounded corners and shadow
        textFieldResult = new JTextField();
        textFieldResult.setEditable(false);
        textFieldResult.setFont(new Font("Arial", Font.BOLD, 24));
        textFieldResult.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldResult.setPreferredSize(new Dimension(600, 50));
        textFieldResult.setBackground(new Color(230, 230, 230));
        frame.add(textFieldResult, BorderLayout.NORTH);

        // Main panel with left and right sections
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 10, 0));  // Adding spacing between columns

        // Panel for two-input operations (on the left side)
        JPanel panelTwoInputs = createTwoInputsPanel();
        mainPanel.add(panelTwoInputs);

        // Panel for single-input operations (on the right side)
        JPanel panelSingleInput = createSingleInputPanel();
        mainPanel.add(panelSingleInput);

        // Adding the main panel to the frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createTwoInputsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);  // Set panel background color

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 10));  // Add spacing between elements
        inputPanel.setBackground(Color.white);

        JLabel labelA = new JLabel("Input A:");
        textFieldA = new JTextField();
        textFieldA.setPreferredSize(new Dimension(100, 30));  // Smaller input size
        JLabel labelB = new JLabel("Input B:");
        textFieldB = new JTextField();
        textFieldB.setPreferredSize(new Dimension(100, 30));  // Smaller input size

        JComboBox<String> operationBox = new JComboBox<>(new String[] {
                "+", "-", "*", "/", "^", "%" // Add operations
        });
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(0, 123, 255));
        calculateButton.setForeground(Color.white);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if the fields are empty
                    if (textFieldA.getText().isEmpty() || textFieldB.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Both inputs must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validate input
                    double a = parseDoubleOrShowError(textFieldA.getText());
                    double b = parseDoubleOrShowError(textFieldB.getText());

                    if (a == Double.MIN_VALUE || b == Double.MIN_VALUE) {
                        return; // Return if there's an invalid number
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

        panel.add(inputPanel);
        panel.add(calculateButton);

        return panel;
    }

    private JPanel createSingleInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);  // Set panel background color

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 10));  // Add spacing between elements
        inputPanel.setBackground(Color.white);

        JLabel labelSingle = new JLabel("Input A:");
        textFieldSingle = new JTextField();
        textFieldSingle.setPreferredSize(new Dimension(100, 30));  // Smaller input size

        JComboBox<String> singleOperationBox = new JComboBox<>(new String[] {
                "√", "log", "ln", "sin", "cos", "tan", "cot", "sec", "csc", "!"
        });
        JButton calculateSingleButton = new JButton("Calculate");
        calculateSingleButton.setBackground(new Color(0, 123, 255));
        calculateSingleButton.setForeground(Color.white);

        calculateSingleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validate input
                    if (textFieldSingle.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Input A must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(frame, "Invalid operation: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
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

        panel.add(inputPanel);
        panel.add(calculateSingleButton);

        return panel;
    }

    private double parseDoubleOrShowError(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return Double.MIN_VALUE;
        }
    }

    public static void main(String[] args) {
        new CalculatorForm();
    }
}
