package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

/**
 * Tests for Homework 4, Task 20
 */
class Task20Test {
    private AutoCloseable closeable;

    @Mock Animal a1, a2;

    @BeforeEach void open() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach void close() throws Exception {
        closeable.close();
    }

    @Test @DisplayName("Get validation errors for a list of animals") void task19_GetValidationErrors() {
        // Arrange
        when(a1.name()).thenReturn("Tiger");
        when(a1.age()).thenReturn(3);
        when(a1.height()).thenReturn(4);
        when(a1.weight()).thenReturn(56);

        when(a2.name()).thenReturn("Cat");
        when(a2.age()).thenReturn(-2);
        when(a2.height()).thenReturn(-1);
        when(a2.weight()).thenReturn(-2);

        List<Animal> animals = List.of(a1, a2);
        Map<String, String> expected = new HashMap<>() {{
            put("Tiger", "");
            put("Cat",
                "Age must be greater than zero" + "Height must be greater than zero" +
                    "Weight must be greater than zero"
            );
        }};

        // Act
        Map<String, String> actual = AnimalHelper.task20GetValidationErrorMessages(animals);

        // Assert
        Assertions.assertEquals(expected.keySet(), actual.keySet());
    }
}
