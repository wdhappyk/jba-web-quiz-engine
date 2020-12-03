import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        StringReverser reverser = new StringReverser() {
            @java.lang.Override
            public String reverse(String str) {
                final StringBuilder sb = new StringBuilder();
                for (char c : str.toCharArray()) {
                    sb.insert(0, c);
                }
                return sb.toString();
            }
        };

        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);
    }

}