package hexlet.code.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocsIndex {
    public static Map<String, List<String>> makeIndex(List<Map<String, String>> docs) {
        Map<String, List<String>> indexes = new HashMap<>();

        for (var doc : docs) {
            String id = doc.get("id");
            // Создаем лист токенов и очищаем его от повторений
            List<String> tokens = SearchEngineUtils.processText(doc.get("text"))
                    .stream()
                    .distinct()
                    .toList();
            // Для каждого токена создаем мапу с value: list of ids
            for (String token: tokens) {
                if (!indexes.containsKey(token)) {
                    indexes.put(token, new ArrayList<>());
                }
                indexes.get(token).add(id);
            }
        }
        return indexes;
    }
}
