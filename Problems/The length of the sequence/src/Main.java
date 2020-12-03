import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int countPositiveNumbers = 0;
        while (true) {
            final int n = scanner.nextInt();
            if (n > 0) {
                countPositiveNumbers++;
            } else {
                break;
            }
        }
        System.out.println(countPositiveNumbers);
    }
}