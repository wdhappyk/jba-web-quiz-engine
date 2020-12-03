import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String firstName = scanner.nextLine();
        final byte age = scanner.nextByte();
        scanner.nextLine();
        final String stageOfEducation = scanner.nextLine();
        final byte yearsOfExperience = scanner.nextByte();
        scanner.nextLine();
        final String cuisinePreference = scanner.nextLine();

        System.out.printf(
                "The form for %s is completed. We will contact you if we need a chef that cooks %s dishes.",
                firstName,
                cuisinePreference
        );
    }
}