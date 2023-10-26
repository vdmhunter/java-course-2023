package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code BackwardIterator} class is an iterator that allows iterating over a collection in a backward direction.
 *
 * @param <T> the type of elements in the collection
 */
public class BackwardIterator<T> implements Iterator<T> {
    private final Collection<T> collection;
    private int index;

    /**
     * Constructs a new {@code BackwardIterator} for the given collection.
     *
     * @param collection the collection to iterate over
     */
    public BackwardIterator(Collection<T> collection) {
        this.collection = collection;
        this.index = collection.size() - 1;
    }

    /**
     * Checks if there are more elements to iterate in the backward direction.
     *
     * @return `true` if there are more elements, `false` otherwise
     */
    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    /**
     * Returns the next element in the backward iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if there are no more elements to return
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        @SuppressWarnings("unchecked")
        T element = (T) collection.toArray()[index];
        index--;

        return element;
    }
}
