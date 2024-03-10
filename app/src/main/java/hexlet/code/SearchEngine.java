package hexlet.code;

import hexlet.code.relevance.Relevance;
import hexlet.code.relevance.RudeRelevance;
import hexlet.code.util.SearchEngineUtils;

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
        List<String> tokens = SearchEngineUtils.processText(text);
        // Фильтруем документы от тех, которые нам точно не нужны
        List<Map<String, String>> filteredDocs = filterDocuments(docs, tokens);

        // Работаем с грубым перебором
        Relevance relevance = new RudeRelevance();

        // Вызываем метод, который работает с очищенными данными и токенами
        return search(filteredDocs, tokens, relevance);
    }

    /**
     * Метод, который работает с уже очищенными (по какому-то правилу) данными.
     *
     * @param filteredDocs - очищенные данные
     * @param tokens       - токены, тоже очищенные
     * @param relevance - Правило для релевантности.
     * @return - отсортированные индексы документов.
     */
    private static List<String> search(
            List<Map<String, String>> filteredDocs,
            List<String> tokens,
            Relevance relevance) {
        Comparator<Map<String, String>> byWordCount = Comparator
                .comparingDouble(doc -> relevance.calculate(doc.get("text"), tokens));

        return filteredDocs.stream()
                .sorted(byWordCount.reversed())
                .map(m -> m.get("id"))
                .collect(Collectors.toList());
    }

    private static List<Map<String, String>> filterDocuments(List<Map<String, String>> docs, List<String> targets) {
        Pattern pattern = SearchEngineUtils.getTokensPattern(targets);

        return docs.stream()
                .filter(x -> pattern.matcher(x.get("text")).find())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}

