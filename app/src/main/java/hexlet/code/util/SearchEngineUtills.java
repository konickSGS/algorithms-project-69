package hexlet.code.util;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngineUtills {
    /**
     * Обработчик строки: очищение от знаков препинания.
     *
     * @param token - входная строка
     * @return - очищенная строка
     */
    public static String processText(String token) {
        return Pattern.compile("\\w+")
                .matcher(token)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining());
    }
}
