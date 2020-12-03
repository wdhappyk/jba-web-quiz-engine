import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int wordsCount = scanner.nextInt();
        scanner.nextLine();

        final Collection<String> incorrectWords = new HashSet<>();
        final Collection<String> dictionary = new HashSet<>();

        for (int i = 0; i < wordsCount; ++i) {
            dictionary.add(scanner.nextLine().toLowerCase());
        }

        final int linesCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < linesCount; ++i) {
            final String line = scanner.nextLine().toLowerCase();
            final Collection<String> words = Arrays.stream(line.split("\\s+"))
                    .collect(Collectors.toSet());
            words.removeAll(dictionary);
            incorrectWords.addAll(words);
        }

        incorrectWords.forEach(System.out::println);
    }
}