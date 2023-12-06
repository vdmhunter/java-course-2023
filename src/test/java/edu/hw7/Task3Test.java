package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonServiceReadWriteLock;
import edu.hw7.task3.PersonServiceSynchronized;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 7, Task 3
 */
class Task3Test {
    @Test
    @DisplayName("PersonServiceSynchronized: Test add, delete and find methods with multiple threads")
    void personServiceSynchronized_TestMultiThreadedOperations() {
        int optimalThreadCount = Runtime.getRuntime().availableProcessors() - 1;

        try (ExecutorService executor = Executors.newFixedThreadPool(optimalThreadCount)) {
            // Arrange
            var service = new PersonServiceSynchronized();
            var latch = new CountDownLatch(3);
            var person1 = new Person(1, "John", "123 Street", "1234567890");
            var person2 = new Person(2, "Jane", "456 Avenue", "0987654321");
            var person3 = new Person(3, "Alex", "789 Boulevard", "0987654321");

            // Act
            executor.submit(() -> {
                service.add(person1);
                latch.countDown();
            });
            executor.submit(() -> {
                service.add(person2);
                latch.countDown();
            });
            executor.submit(() -> {
                service.add(person3);
                latch.countDown();
            });

            latch.await();

            executor.submit(() -> service.delete(1));
            Future<List<Person>> futureFindByName = executor.submit(() -> service.findByName("Jane"));
            Future<List<Person>> futureFindByAddress = executor.submit(() -> service.findByAddress("456 Avenue"));
            executor.submit(() -> service.delete(3));
            Future<List<Person>> futureFindByPhone = executor.submit(() -> service.findByPhone("0987654321"));

            executor.shutdown();
            boolean terminated = executor.awaitTermination(1, TimeUnit.MINUTES);

            // Assert
            Assertions.assertAll(
                () -> Assertions.assertTrue(terminated),
                () -> Assertions.assertTrue(futureFindByName.get().contains(person2)),
                () -> Assertions.assertTrue(futureFindByAddress.get().contains(person2)),
                () -> Assertions.assertTrue(futureFindByPhone.get().contains(person2))
            );
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    @DisplayName("PersonServiceReadWriteLock: Test add, delete and find methods with multiple threads")
    void personServiceReadWriteLock_TestMultiThreadedOperations() {
        int optimalThreadCount = Runtime.getRuntime().availableProcessors() - 1;

        try (ExecutorService executor = Executors.newFixedThreadPool(optimalThreadCount)) {
            // Arrange
            var service = new PersonServiceReadWriteLock();
            var latch = new CountDownLatch(3);
            var person1 = new Person(1, "John", "123 Street", "1234567890");
            var person2 = new Person(2, "Jane", "456 Avenue", "0987654321");
            var person3 = new Person(3, "Alex", "789 Boulevard", "0987654321");

            // Act
            executor.submit(() -> {
                service.add(person1);
                latch.countDown();
            });
            executor.submit(() -> {
                service.add(person2);
                latch.countDown();
            });
            executor.submit(() -> {
                service.add(person3);
                latch.countDown();
            });

            latch.await();

            executor.submit(() -> service.delete(1));
            Future<List<Person>> futureFindByName = executor.submit(() -> service.findByName("Jane"));
            Future<List<Person>> futureFindByAddress = executor.submit(() -> service.findByAddress("456 Avenue"));
            executor.submit(() -> service.delete(3));
            Future<List<Person>> futureFindByPhone = executor.submit(() -> service.findByPhone("0987654321"));

            executor.shutdown();
            boolean terminated = executor.awaitTermination(1, TimeUnit.MINUTES);

            // Assert
            Assertions.assertAll(
                () -> Assertions.assertTrue(terminated),
                () -> Assertions.assertTrue(futureFindByName.get().contains(person2)),
                () -> Assertions.assertTrue(futureFindByAddress.get().contains(person2)),
                () -> Assertions.assertTrue(futureFindByPhone.get().contains(person2))
            );
        } catch (InterruptedException ignored) {
        }
    }
}
