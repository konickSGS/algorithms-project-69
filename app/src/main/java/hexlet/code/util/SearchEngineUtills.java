package hexlet.code.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchEngineUtills {
    /**
     * Обработчик строки: очищение от знаков препинания.
     *
     * @param text - входная строка
     * @return - очищенная строка
     */
    public static List<String> processText(String text) {
        List<String> words = Arrays.stream(text.split(" ")).toList();
        return words.stream()
                .map(SearchEngineUtills::processWord)
                .collect(Collectors.toList());
    }

    public static String processWord(String token) {
        return Pattern.compile("\\w+")
                .matcher(token)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.joining());
    }

    public static Pattern getWordPattern(List<String> tokens) {
        // \b - Промежуток между символом, совпадающим с \w и символом, не совпадающим с \w в любом порядке.
        String allTokens = tokens.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
        return Pattern.compile("\\b(" + allTokens + ")\\b", Pattern.CASE_INSENSITIVE);
    }
}
