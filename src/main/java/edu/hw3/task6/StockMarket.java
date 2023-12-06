package edu.hw3.task6;

import java.util.PriorityQueue;

/**
 * The {@code StockMarket} class represents a market for trading stocks.
 * It maintains a collection of stocks and provides methods for adding, removing, and querying stocks.
 */
public final class StockMarket {
    private final PriorityQueue<Stock> stockQueue;

    /**
     * Constructs a new {@code StockMarket} with an empty stock collection.
     * Stocks will be sorted by descending price order.
     */
    public StockMarket() {
        stockQueue = new PriorityQueue<>((stock1, stock2) -> Double.compare(stock2.price(), stock1.price()));
    }

    /**
     * Adds a stock to the market.
     *
     * @param stock the stock to add to the market
     */
    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    /**
     * Removes a stock from the market.
     *
     * @param stock the stock to remove from the market
     */
    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    /**
     * Retrieves the most valuable stock in the market.
     *
     * @return the most valuable stock in the market
     */
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
