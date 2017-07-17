package Models.DTs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p><b>MyArrayDeQueue</b> is an implementation of the queue ADT using a mutable ArrayQueue
 * Data Structure backed by an array</p>
 * <p>It Adds/ removes elements to the back/ front of the list</p>
 *
 * @param <T> The type of elements held in this MyArrayDeque
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyArrayDeque<T> implements Iterable<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private T[] arrayQueue;
    private int size;

    /**
     * Constructs an empty array deque initial capacity of 10 elements.
     */
    public MyArrayDeque() {
        arrayQueue = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty array deque with the given initial capacity
     *
     * @param capacity the initial number of elements this queue can store
     */
    public MyArrayDeque(int capacity) {
        arrayQueue = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * Returns whether this stack is empty
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the size of the stack
     *
     * @return the size of the stack
     */
    public int size() {
        return size;
    }

    /**
     * Inspects and return the current value at the front of the queue
     *
     * @return the value at the front of the queue or null if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return arrayQueue[0];
        }
    }

    /**
     * adds a new element to the front of the queue
     *
     * @param value the value to add to this queue
     */
    public void add(T value) {
        if (arrayQueue.length == size) {
            arrayQueue = Arrays.copyOf(arrayQueue, arrayQueue.length * 2);
        }
        arrayQueue[size] = value;
        size++;
    }

    /**
     * @return the value at the front of the queue
     * @throws NoSuchElementException if the stack is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is Empty");
        }
        T result = arrayQueue[0];
        for (int i = 0; i < size; i++) {
            arrayQueue[i] = arrayQueue[i + 1];
        }
        size--;
        return result;
    }

    /**
     * Creates an iterator object over this stack
     *
     * @return an iterator object to iterate over the stack
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayQueueIterator();
    }

    private class MyArrayQueueIterator implements Iterator {
        private int cursor;
        private T next;

        public MyArrayQueueIterator() {
            cursor = 0;
            next = null;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("The iterator has no more elements.");
            }
            next = arrayQueue[cursor];
            cursor++;
            return next;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("The next method has not been called yet or " +
                        "the remove method has already been called after the last call to next.");
            }
            for (int i = cursor - 1; i < size - 1; i++) {
                arrayQueue[i] = arrayQueue[i + 1];
            }
            cursor--;
            next = null;
            size--;
        }
    }
}
