package hexlet.code.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DocsIndexTest {
    @DisplayName("Проверка makeIndex")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideMakeIndex")
    public void makeIndexTest(List<Map<String, String>> docs, Map<String, List<String>> expected) {
        Map<String, List<String>> actual = DocsIndex.makeIndex(docs);

        Assertions.assertEquals(actual, expected);
    }

    private static Stream<Arguments> provideMakeIndex() {
        List<Map<String, String>> docs = List.of(
                Map.of("id", "doc1", "text", "some text"),
                Map.of("id", "doc2", "text", "some text too")
        );

        Map<String, List<String>> index = Map.of(
                "some", List.of("doc1", "doc2"),
                "text", List.of("doc1", "doc2"),
                "too", List.of("doc2")
        );

        return Stream.of(
                Arguments.of(docs, index)
        );
    }
}
