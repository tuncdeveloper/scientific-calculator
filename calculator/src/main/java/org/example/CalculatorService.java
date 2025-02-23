package org.example;

public class CalculatorService {
    private final CalculatorOperations operations;

    public CalculatorService() {
        this.operations = new CalculatorOperations();
    }

    public double calculate(String operation, double a, double b) {
        switch (operation) {
            case "+":
                return operations.add(a, b);
            case "-":
                return operations.subtract(a, b);
            case "*":
                return operations.multiply(a, b);
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Sıfıra bölme hatası!");
                }
                return operations.divide(a, b);
            case "^":
                return operations.power(a, b);
            case "%":
                return operations.percentage(a, b);
            default:
                throw new IllegalArgumentException("Geçersiz işlem: " + operation);
        }
    }

    public double calculateSingle(String operation, double a) {
        if (a < 0 && (operation.equals("sqrt") || operation.equals("log") || operation.equals("ln"))) {
            throw new IllegalArgumentException("Negatif sayılar bu işlem için geçerli değil: " + operation);
        }

        switch (operation) {
            case "√":
                return operations.sqrt(a);
            case "log":
                return operations.log(a);
            case "ln":
                return operations.ln(a);
            case "sin":
                return operations.sin(a);
            case "cos":
                return operations.cos(a);
            case "tan":
                return operations.tan(a);
            case "cot":
                return operations.cot(a);
            case "sec":
                return operations.sec(a);
            case "csc":
                return operations.csc(a);
            case "!":
                if (a < 0 || a != (int) a) {
                    throw new IllegalArgumentException("Faktöriyel sadece pozitif tam sayılar için hesaplanabilir.");
                }
                return operations.factorial((int) a);
            default:
                throw new IllegalArgumentException("Geçersiz işlem: " + operation);
        }
    }
}
