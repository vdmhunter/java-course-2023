package edu.hw5;

import edu.hw5.task3.DateParser;
import edu.hw5.task3.handlers.AgoHandler;
import edu.hw5.task3.handlers.DateFormatHandler;
import edu.hw5.task3.handlers.Handler;
import edu.hw5.task3.handlers.SequentialDayHandler;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for Homework 5, Task 3
 */
class Task3Test {
    @ParameterizedTest(name = "Test {index} - Parsing \"{0}\" should return {1}")
    @MethodSource("provideDataForHappyPaths")
    @DisplayName("Happy paths")
    void parseDate_HappyPaths(String str, LocalDate expected) {
        // Arrange
        var clock = Clock.fixed(Instant.parse("2023-11-02T00:00:00Z"), ZoneId.systemDefault());

        List<Handler> handlers = new ArrayList<>();

        DateFormatHandler handler1 = new DateFormatHandler();
        SequentialDayHandler handler2 = new SequentialDayHandler(clock);
        AgoHandler handler3 = new AgoHandler(clock);

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        handlers.add(handler1);
        handlers.add(handler2);
        handlers.add(handler3);

        DateParser dateParser = new DateParser(handlers);

        // Act
        Optional<LocalDate> actual = dateParser.parseDate(str);

        // Assert
        Assertions.assertEquals(Optional.of(expected), actual);
    }

    @ParameterizedTest(name = "Test {index} - Parsing \"{0}\" should return Optional.empty()")
    @MethodSource("provideBoardsForFailPaths")
    @DisplayName("Fail Paths")
    void parseDate_FailPaths(String str, Object expected) {
        // Arrange
        var clock = Clock.fixed(Instant.parse("2023-11-02T00:00:00Z"), ZoneId.systemDefault());

        List<Handler> handlers = new ArrayList<>();

        DateFormatHandler handler1 = new DateFormatHandler();
        SequentialDayHandler handler2 = new SequentialDayHandler(clock);
        AgoHandler handler3 = new AgoHandler(clock);

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        handlers.add(handler1);
        handlers.add(handler2);
        handlers.add(handler3);

        DateParser dateParser = new DateParser(handlers);

        // Act
        Optional<LocalDate> actual = dateParser.parseDate(str);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test AgoHandler default constructor")
    void AgoHandler_TestDefaultConstructor() {
        // Arrange
        String dateStr = "3 days ago";
        LocalDate expectedDate = LocalDate.now().minusDays(3);

        var agoHandler = new AgoHandler();

        // Act
        Optional<LocalDate> actualDate = agoHandler.handle(dateStr);

        // Assert
        Assertions.assertEquals(Optional.of(expectedDate), actualDate);
    }

    @Test
    @DisplayName("Test SequentialDayHandler default constructor")
    void SequentialDayHandler_TestDefaultConstructor() {
        // Arrange
        String dateStr = "yesterday";
        LocalDate expectedDate = LocalDate.now().minusDays(1);

        var sequentialDayHandler = new SequentialDayHandler();

        // Act
        Optional<LocalDate> actualDate = sequentialDayHandler.handle(dateStr);

        // Assert
        Assertions.assertEquals(Optional.of(expectedDate), actualDate);
    }

    private static Stream<Arguments> provideDataForHappyPaths() {
        return Stream.of(
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.of(2023, 11, 3)),
            Arguments.of("today", LocalDate.of(2023, 11, 2)),
            Arguments.of("yesterday", LocalDate.of(2023, 11, 1)),
            Arguments.of("1 day ago", LocalDate.of(2023, 11, 1)),
            Arguments.of("2234 days ago", LocalDate.of(2017, 9, 20))
        );
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static Stream<Arguments> provideBoardsForFailPaths() {
        return Stream.of(
            Arguments.of("2020-13-77", Optional.empty()),
            Arguments.of("2020-32-2", Optional.empty()),
            Arguments.of("0/31/1976", Optional.empty()),
            Arguments.of("99/3/20", Optional.empty()),
            Arguments.of("next tomorrow", Optional.empty()),
            Arguments.of("not today", Optional.empty()),
            Arguments.of("yesterdey", Optional.empty()),
            Arguments.of("1 dey ago", Optional.empty()),
            Arguments.of("2234 dayz ago", Optional.empty())
        );
    }
}
