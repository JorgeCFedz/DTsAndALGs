package Models.DTs;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p><b>myStack</b> represents a mutable stack ADT (Abstract Data Type) using a
 * mutable stack Data Structure</p>
 * <p>Adds/ removes elements from the front of the list</p>
 *
 * @param <T> The type of elements held in this LinkedList
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyStack<T> implements Iterable<T>, Comparable<MyStack> {
    private MyStackNode top;
    private int total;

    /**
     * Constructs an empty stack
     */
    public MyStack() {
        //top = null;
//        this.top = null;
//        this.stackList = new LinkedList<>();
        this.top = null;
        total = 0;
    }

    /**
     * checks if the Stack contains no elements
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * checks the number of elements in the stack
     *
     * @return the size of the stack
     */
    public int size() {
        return total;
    }

    /**
     * looks at the top or most recent element of the stack
     *
     * @return the value at the top of the stack or null if the stack is empty
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return this.top.data;
    }

    /**
     * adds a new element to the top of the stack
     *
     * @param value the value to push into the stack
     */
    public void push(T value) {
        MyStackNode current = top;
        top = new MyStackNode();
        top.data = value;
        top.next = current;
        total++;
    }

    /**
     * @return the value at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T result = top.data;
        if (size() == 1)
            this.top = null;
        else
            this.top = top.next;
        total--;
        return result;
    }

    /**
     * Creates an iterator object over this stack
     * Allows the use of foreach loops (class needs to implement iterable)
     * warning: The iteration occurs over the internal implementation of
     * the Stack, thus no LIFO ordering is guaranteed
     *
     * @return an iterator object to iterate over the stack
     */
    @Override
    public Iterator<T> iterator() {
        return new MyStackIterator();
    }

    /**
     * Placeholder for the compareTo method
     * NOTE: the method compareTo makes no sense for a Stack
     *
     * @param o the stack to compare this one with
     * @return whether this stack is smaller (-), bigger (+), or equal (0)
     * than the given stack
     */
    @Override
    public int compareTo(MyStack o) {
        return 0;
    }

    private class MyStackNode {
        T data;
        MyStackNode next;
    }

    private class MyStackIterator implements Iterator {
        MyStackNode cursor;
        MyStackNode next;

        MyStackIterator() {
            cursor = top;
            next = null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("The iterator has no more elements");
            }
            next = cursor;
            cursor = cursor.next;
            return next.data;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("The next method has not been called yet or " +
                        "the remove method has already been called after the last call to next");
            }
            if (next == top) {
                top = top.next;
            } else {
                MyStackNode prevNode = top;
                while (!prevNode.next.equals(next)) {
                    prevNode = prevNode.next;
                }
                prevNode.next = prevNode.next.next;
            }
            next = null;
            total--;
        }
    }
}