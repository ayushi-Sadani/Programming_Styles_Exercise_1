import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("pride-and-prejudice.txt");
            return;
        }

        Set<String> stopWords = new HashSet<>();
        try (Scanner sw = new Scanner(new File("../stop_words.txt"))) {
            for (String w : sw.nextLine().split(",")) {
                stopWords.add(w);
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        try (Scanner sc = new Scanner(new File(args[0]))) {
            sc.useDelimiter("[^a-zA-Z]+"); 
            while (sc.hasNext()) {
                String word = sc.next().toLowerCase();
                if (word.length() > 1 && !stopWords.contains(word)) {
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
            }
        }

        freq.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(25)
            .forEach(e -> System.out.println(e.getKey() + "  -  " + e.getValue()));
    }
}
