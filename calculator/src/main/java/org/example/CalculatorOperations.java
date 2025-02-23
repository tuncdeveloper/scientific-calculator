package org.example;

public class CalculatorOperations {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Bölen 0 olamaz!");
        }
        return a / b;
    }

    // Üs alma
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Karekök alma
    public double sqrt(double number) {
        if (number < 0) {
            throw new ArithmeticException("Negatif sayıların karekökü alınamaz!");
        }
        return Math.sqrt(number);
    }

    // Yüzdelik hesaplama (x'in %y'si)
    public double percentage(double x, double y) {
        return (x * y) / 100.0;
    }

    // Logaritma (taban 10)
    public double log(double number) {
        if (number <= 0) {
            throw new ArithmeticException("Logaritma sıfır veya negatif değerler için tanımsızdır!");
        }
        return Math.log10(number);
    }

    // Doğal logaritma (ln)
    public double ln(double number) {
        if (number <= 0) {
            throw new ArithmeticException("Doğal logaritma sıfır veya negatif değerler için tanımsızdır!");
        }
        return Math.log(number);
    }

    // Faktöriyel hesaplama
    public long factorial(int number) {
        if (number < 0) {
            throw new ArithmeticException("Negatif sayıların faktöriyeli alınamaz!");
        }
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }


    // Trigonometrik fonksiyonlar (Derece cinsinden hesaplama)
    public double sin(double degree) {
        // Dereceyi direk kullanarak sin fonksiyonu
        return Math.sin(degree);
    }

    public double cos(double degree) {
        // Dereceyi direk kullanarak cos fonksiyonu
        return Math.cos(degree);
    }

    public double tan(double degree) {
        if (degree == 90 || degree == 270) {
            throw new ArithmeticException("Tan 90° ve 270° için tanımsızdır!");
        }
        // Dereceyi direk kullanarak tan fonksiyonu
        return Math.tan(degree);
    }

    public double cot(double degree) {
        if (degree == 0 || degree == 180) {
            throw new ArithmeticException("Cot 0° ve 180° için tanımsızdır!");
        }
        // Cot için tan fonksiyonunun tersini alıyoruz
        return 1.0 / Math.tan(degree);
    }

    public double sec(double degree) {
        if (degree == 90 || degree == 270) {
            throw new ArithmeticException("Sec 90° ve 270° için tanımsızdır!");
        }
        // Cos fonksiyonunun tersini alıyoruz
        return 1.0 / Math.cos(degree);
    }

    public double csc(double degree) {
        if (degree == 0 || degree == 180) {
            throw new ArithmeticException("Csc 0° ve 180° için tanımsızdır!");
        }
        // Sin fonksiyonunun tersini alıyoruz
        return 1.0 / Math.sin(degree);
    }
}
