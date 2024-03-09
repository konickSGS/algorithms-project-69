package hexlet.code.relevance;

import hexlet.code.util.SearchEngineUtills;

import java.util.regex.Pattern;

public class RudeRelevance implements Relevance {
    @Override
    public double calculate(String text, String target) {
        target = SearchEngineUtills.processText(target);
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(target) + "\\b", Pattern.CASE_INSENSITIVE);

        return pattern.matcher(text)
                .results()
                .count();
    }
}
