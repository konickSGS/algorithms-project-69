package hexlet.code;

import java.util.List;
import java.util.Map;

/**
 * Класс для быстрых тестов. Опционально не нужен
 */
public class App {
    public static void main(String[] args) {
        var doc1 = "I can't shoot straight unless I've had a pint!";
        var doc2 = "Don't shoot shoot shoot that thing at me.";
        var doc3 = "I'm your shooter.";

        List<Map<String, String>> docs = List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3)
        );

        String text = "shoot at me";

        System.out.println(SearchEngine.search(docs, text));
    }
}
