package Models.DTs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p><b>MyLinkedList</b> is an implementation of the LinkedList ADT/ DT using a mutable LinkedList
 * Data Structure where each node is linked to the next one by pointers.</p>
 * <p>It uses an anonymous MyLinkedListNode to store each of the elements held in the list</p>
 * <p>It emulates part of the Collection and Object inheritance from Oracle java API.</p>
 *
 * @param <T> The type of elements held in this LinkedList
 * @author JorgeCFedz
 * @version 1.1
 * @since 1.0
 */
public class MyLinkedList<T extends Comparable<? super T>>
        extends MyObject
        implements MyCollection<T> {

    private MyLinkedListNode head;

    /**
     * Constructs an empty list
     */
    public MyLinkedList() {
        head = null;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence
     *
     * @return an iterator over this collection
     */
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * Compares the given list with this list for equality
     *
     * @param other the LinkedList to be compared with this list for equality
     * @return whether both lists are equal
     */
    public boolean equals(MyLinkedList other) {
        if (this.size() != other.size()) {
            return false;
        }
        MyLinkedListNode curr = head;
        for (Object x : other) {
            if (curr.data != x) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    // METHODS OF INTERFACE ITERABLE ALSO PART OF THE COLLECTION INTERFACE)

    /**
     * @return the hash code value for this list
     */
    public int hashCode() {
        int result = 6;
        for (Object x : this) {
            result = 37 * result + x.hashCode();
        }
        return result;
    }

    // METHODS OF JAVA OBJECT ROOT CLASS (SUPERCLASS FOR ALL CLASSES)

    /**
     * @return a string representation of this object
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        int i = 0;
        for (Object x : this) {
            String element = (i == 0) ? "" + x : ", " + x;
            result.append(element);
            i++;
        }
        result.append(']');
        return result.toString();
    }

    /**
     * checks if the list contains no elements
     *
     * @return whether this list is empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    public int size() {
        int size = 0;
        MyLinkedListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            size++;
        }
        return size;
    }

    // METHODS OF JAVA COLLECTION INTERFACE
    // COMMON TO SUB-INTERFACES: LIST, SET, QUEUE; CLASS: STACK
    // COMMON TO SUB-INTERFACE: MAP

    /**
     * Removes all elements from this list
     */
    public void clear() {
        head = null;
    }

    /**
     * Adds a new node to the end of this list
     *
     * @param data the new element to add to this list
     * @throws IllegalArgumentException if the given element is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The given element is null");
        }
        if (head == null) {
            head = new MyLinkedListNode(data, null);
        } else {
            MyLinkedListNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new MyLinkedListNode(data, null);
        }
    }

    /**
     * removes the head of this list
     *
     * @throws NoSuchElementException if the list is empty
     */
    public void remove() throws NoSuchElementException {
        if (this.head == null) {
            throw new NoSuchElementException("The list is already empty");
        }
        this.head = this.head.next;
    }

    // METHODS OF JAVA COLLECTION INTERFACE (COMMON TO INTERFACES: LIST, SET, QUEUE; CLASS: STACK)

    /**
     * removes the element at the given index from this collection
     *
     * @param index the index of the value to remove.
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || &gt;= size())
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("The given index is negative or exceeds " +
                    "the limits for this list");
        }
        if (index == 0) {
            head = head.next;
        } else {
            MyLinkedListNode curr = head;
            int i = 1;
            while (curr.next != null && i != index) {
                curr = curr.next;
                i++;
            }
            curr.next = curr.next.next;
        }
    }

    /**
     * removes the given element from this collection
     *
     * @param elem the value to remove from this list
     * @return whether this list contains the given element
     * @throws IllegalArgumentException if the given element is null
     */
    public boolean remove(T elem) throws IllegalArgumentException {
        if (elem == null) {
            throw new IllegalArgumentException("the given element is null");
        }
        if (head == null) {
            return false;
        } else {
            if (head.data == elem) {
                head = head.next;
            } else {
                MyLinkedListNode curr = head;
                while (curr.next != null && curr.next.data != elem) {
                    curr = curr.next;
                }
                if (curr.next != null) {
                    curr.next = curr.next.next;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if this list contains the specified element
     *
     * @param otherData the given element to search for in this list
     * @return true if this list contains the specified element
     * @throws IllegalArgumentException if the given element is null
     */
    public boolean contains(T otherData) throws IllegalArgumentException {
        if (otherData == null) {
            throw new IllegalArgumentException("The given element is null");
        }
        MyLinkedListNode curr = this.head;
        while (curr != null) {
            if (curr.data.compareTo(otherData) == 0) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * Appends all the elements in the other list to this list
     *
     * @param other the list to add to this collection
     */
    public void addAll(MyCollection<? extends T> other) {
        for (T x : other) {
            this.add((x));
        }
    }

    /**
     * Removes all the elements in this list
     */
    public void removeAll() {
        this.head = null;
    }

    /**
     * Checks whether this list contains all the elements from the given collection
     *
     * @param other the collection containing the elements to look for
     * @return whether this MyCollection contains all the elements from the given collection
     */
    public boolean containsAll(MyCollection<T> other) {
        for (T x : other) {
            if (!this.contains(x)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Discard any element not contained in the given collection
     *
     * @param other the given collection containing the elements to retain
     */
    public void retainAll(MyCollection<T> other) {
        for (T x : this) {
            if (!other.contains(x)) {
                this.remove(x);
            }
        }
    }

    /**
     * Returns an array containing all the elements in this collection
     *
     * @return an array containing all the elements in this collection
     */
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        int i = 0;
        for (Object x : this) {
            result[i] = x;
            i++;
        }
        return result;
    }

    /**
     * <p><b>MyLinkedListNode</b> represents a node in the MyLinkedList Data Structure</p>
     */
    private class MyLinkedListNode {
        private T data;
        private MyLinkedListNode next;

        /**
         * Class constructor
         */
        MyLinkedListNode() {
            this.data = null;
            this.next = null;
        }

        /**
         * Class constructor
         *
         * @param data the element to store in this node
         */
        MyLinkedListNode(T data) {
            this.data = data;
            this.next = null;
        }

        MyLinkedListNode(T data, MyLinkedListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private class MyLinkedListIterator implements Iterator {
        private MyLinkedListNode curr;

        public MyLinkedListIterator() {
            curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = curr.data;
            curr = curr.next;
            return data;
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}
