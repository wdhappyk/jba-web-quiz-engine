// Don't delete this import statement

import java.text.DecimalFormat;
import java.util.Scanner;

class SimpleCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long num1 = scanner.nextLong();
        String operator = scanner.next();
        long num2 = scanner.nextLong();

        switch (operator) {
            case "-":
                subtractTwoNumbers(num1, num2);
                break;
            case "+":
                sumTwoNumbers(num1, num2);
                break;
            case "/":
                divideTwoNumbers(num1, num2);
                break;
            case "*":
                multiplyTwoNumbers(num1, num2);
                break;
            default:
                System.out.println("Invalid operation!");
                break;
        }
    }

    // Implement your methods here
    public static void subtractTwoNumbers(double x, double y) {
        System.out.println(formatDouble(x - y));
    }


    public static void sumTwoNumbers(double x, double y) {
        System.out.println(formatDouble(x + y));
    }


    public static void divideTwoNumbers(double x, double y) {
        if (y == 0) {
            System.out.println("Division by 0!");
        } else {
            System.out.println(formatDouble(x / y));
        }
    }


    public static void multiplyTwoNumbers(double x, double y) {
        System.out.println(formatDouble(x * y));
    }

    private static String formatDouble(double n) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(n);
    }
}