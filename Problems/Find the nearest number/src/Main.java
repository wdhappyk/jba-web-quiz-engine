import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Integer> numbers = new ArrayList<>();

        final String[] nums = scanner.nextLine().split(" ");
        for (String n : nums) {
            numbers.add(Integer.parseInt(n));
        }

        final int i = scanner.nextInt();
        int range = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();

        for (int n : numbers) {
            int r = Math.abs(i - n);
            if (r < range) {
                range = r;
                result.clear();
                result.add(n);
            } else if (r == range) {
                result.add(n);
            }
        }

        result.sort(Comparator.comparingInt(m -> m));
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}