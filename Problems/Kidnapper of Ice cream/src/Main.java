import java.util.*;

class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> availableWords = getWords();
        List<String> wordsInNote = getWords();
        if (availableWords.size() < wordsInNote.size()) {
            System.out.println("You are busted");
            return;
        }
        wordsInNote.removeAll(availableWords);
        System.out.println(wordsInNote.isEmpty() ? "You get money" : "You are busted");
    }

    static List<String> getWords() {
        List<String> result = new ArrayList<>();
        String[] words = scanner.nextLine().split("\\s+");
        Collections.addAll(result, words);
        return result;
    }
}