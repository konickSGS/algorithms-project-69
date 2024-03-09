package hexlet.code;

import hexlet.code.relevance.RudeRelevance;
import hexlet.code.util.SearchEngineUtills;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngine {
    /**
     * Чёткий поиск, в котором искомое слово содержится в тексте.
     *
     * @param docs   List документов
     * @param target строка, которую мы ищем
     * @return список с id документов, в которых эта строка встречается
     */
    public static List<String> search(List<Map<String, String>> docs, String target) {
        // Сначала обрабатываем строку, чтобы отчистить ее от знаков препинания
        // \b - Промежуток между символом, совпадающим с \w и символом, не совпадающим с \w в любом порядке.
        target = SearchEngineUtills.processText(target);
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(target) + "\\b", Pattern.CASE_INSENSITIVE);

        RudeRelevance relevance = new RudeRelevance();
        String finalTarget = target;
        return docs.stream()
                .filter(m -> pattern.matcher(m.get("text")).find())
                .sorted((a, b) -> Double.compare(
                        relevance.calculate(b.get("text"), finalTarget),
                        relevance.calculate(a.get("text"), finalTarget)))
                .map(m -> m.get("id"))
                .collect(Collectors.toList());
    }
}
