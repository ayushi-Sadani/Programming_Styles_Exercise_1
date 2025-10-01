import java.io.*;
import java.util.*;

public class StopWords {
    public static void main(String[] args) throws Exception {
        
        Set<String> stopWords = new HashSet<>();
        try (Scanner sw = new Scanner(new File("../stop_words.txt"))) {
            for (String w : sw.nextLine().split(",")) {
                stopWords.add(w);
            }
        }

        Map<String, Integer> frequency = new HashMap<>();
        try (Scanner sc = new Scanner(new File(args[0]))) {
            sc.useDelimiter("[^a-zA-Z]+"); 
            while (sc.hasNext()) {
                String word = sc.next().toLowerCase();
                if (word.length() > 1 && !stopWords.contains(word)) {
                    frequency.put(word, frequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        frequency.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(25)
            .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}