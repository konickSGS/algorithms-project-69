package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class SearchEngineTest {
    @DisplayName("Проверка search engine")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideSearchEngine")
    public void searchTest(List<Map<String, String>> docs, String target, List<String> expected) {
        List<String> actual = SearchEngine.search(docs, target);
        Assertions.assertEquals(actual, expected);
    }

    private static Stream<Arguments> provideSearchEngine() {
        var doc1 = "I can't shoot straight unless I've had a pint!";
        var doc2 = "Don't shoot shoot shoot that thing at me.";
        var doc3 = "I'm your shooter.";
        var docN = "Hello guys";

        List<Map<String, String>> docs = List.of(
                Map.of("id", "doc1", "text", doc1),
                Map.of("id", "doc2", "text", doc2),
                Map.of("id", "doc3", "text", doc3),
                Map.of("id", "docN", "text", docN)
        );

        return Stream.of(
                Arguments.of(docs, "shoot", List.of("doc2", "doc1")),
                Arguments.of(new ArrayList<Map<String, String>>(), "shoot", List.of()),
                Arguments.of(docs, "pint", List.of("doc1")),
                Arguments.of(docs, "pint!", List.of("doc1")),
                Arguments.of(docs, "shoot at me", List.of("doc2", "doc1"))
        );
    }
}
