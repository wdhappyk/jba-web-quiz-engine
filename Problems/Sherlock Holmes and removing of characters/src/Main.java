import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String fstWord = scanner.next().toLowerCase();
        final String sndWord = scanner.next().toLowerCase();
        final Map<Character, Integer> fstCounts = getCharCounts(fstWord);
        final Map<Character, Integer> sndCounts = getCharCounts(sndWord);
        int result = 0;

        Set<Character> keys = Stream.concat(
                fstCounts.keySet().stream(),
                sndCounts.keySet().stream()
        ).collect(Collectors.toSet());

        for (Character k : keys) {
            result += Math.abs(fstCounts.getOrDefault(k, 0) - sndCounts.getOrDefault(k, 0));
        }

        System.out.println(result);
    }

    static Map<Character, Integer> getCharCounts(String word) {
        final Map<Character, Integer> counts = new HashMap<>();

        for (char c : word.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        return counts;
    }
}