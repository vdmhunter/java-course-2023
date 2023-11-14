package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 3, Task 6
 */
class Task6Test {
    @Test()
    @DisplayName("Test adding a stock to an empty market")
    void stockMarket_addStock() {
        // Arrange
        var market = new StockMarket();
        var stock = new Stock("Apple", 120.0);

        // Act
        market.add(stock);
        Stock actual = market.mostValuableStock();

        // Assert
        Assertions.assertEquals(stock, actual);
    }

    @Test()
    @DisplayName("Test removing a stock from a market")
    void stockMarket_removeStock() {
        // Arrange
        var market = new StockMarket();
        var stock1 = new Stock("Apple", 120.0);
        var stock2 = new Stock("Google", 1800.0);

        // Act
        market.add(stock1);
        market.add(stock2);
        market.remove(stock2);
        Stock actual = market.mostValuableStock();

        // Assert
        Assertions.assertEquals(stock1, actual);
    }
}
