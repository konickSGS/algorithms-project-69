package hexlet.code;

import hexlet.code.relevance.Relevance;
import hexlet.code.relevance.RudeRelevance;
import hexlet.code.util.SearchEngineUtills;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {
    /**
     * Основной метод для поискового движка.
     *
     * @param docs List документов
     * @param text строка, которую мы ищем
     * @return список с id документов, в которых эта строка встречается
     */
    public static List<String> search(List<Map<String, String>> docs, String text) {
        // Сначала обрабатываем строки, чтобы их преобразовать в список и отчистить их от знаков препинания
        List<String> tokens = SearchEngineUtills.processText(text);
        // Фильтруем документы от тех, которые нам точно не нужны
        List<Map<String, String>> filteredDocs = filterDocuments(docs, tokens);

        // Вызываем метод, который работает с очищенными данными и токенами
        return search(filteredDocs, tokens);
    }

    /**
     * Метод, который работает с уже очищенными (по какому-то правилу) данными.
     *
     * @param filteredDocs - очищенные данные
     * @param tokens       - токены, тоже очищенные
     * @return - отсортированные индексы документов.
     */
    public static List<String> search(List<Map<String, String>> filteredDocs, List<String> tokens) {
        Relevance relevance = new RudeRelevance();
        Comparator<Map<String, String>> byWordCount = Comparator
                .comparingDouble(doc -> relevance.calculate(doc.get("text"), tokens));

        return filteredDocs.stream()
                .sorted(byWordCount.reversed())
                .map(m -> m.get("id"))
                .collect(Collectors.toList());
    }

    private static List<Map<String, String>> filterDocuments(List<Map<String, String>> docs, List<String> targets) {
        Pattern pattern = SearchEngineUtills.getWordPattern(targets);

        return docs.stream()
                .filter(x -> pattern.matcher(x.get("text")).find())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}

