package hexlet.code;

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
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(target) + "\\b", Pattern.CASE_INSENSITIVE);

        return docs.stream()
                .filter(m -> pattern.matcher(m.get("text")).find())
                .map(m -> m.get("id"))
                .collect(Collectors.toList());
    }
}
