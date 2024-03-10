package hexlet.code.relevance;

import hexlet.code.util.SearchEngineUtils;

import java.util.List;
import java.util.regex.Pattern;

public class RudeRelevance implements Relevance {
    @Override
    public double calculate(String text, List<String> targets) {
        Pattern pattern = SearchEngineUtils.getTokensPattern(targets);

        return pattern.matcher(text)
                .results()
                .count();
    }
}
