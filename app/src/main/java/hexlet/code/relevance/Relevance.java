package hexlet.code.relevance;

import java.util.List;

public interface Relevance {
    double calculate(String text, List<String> targets);
}
