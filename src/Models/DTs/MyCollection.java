package Models.DTs;

/**
 * <p><b>MyCollection</b> is the root interface of the Model.DTs MyCollection hierarchy.</p>
 *
 * @author JorgeCFedz
 * @version 1.1
 * @since 1.1
 */
public interface MyCollection<T>
        extends Iterable<T> {

    /**
     * checks if this MyCollection contains no elements
     *
     * @return whether this MyCollection is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this MyCollection
     *
     * @return the number of elements in this MyCollection
     */
    int size();

    /**
     * Removes all elements from this MyCollection
     */
    void clear();

    /**
     * Adds a new node to the end of this MyCollection
     *
     * @param element the new element to add to this MyCollection
     * @throws IllegalArgumentException if the given element is null
     */
    void add(T element);

    /**
     * removes the first occurrence of the given element in this MyCollection
     *
     * @param element the given element to remove from this list
     * @return true if the element was contained in this MyCollection
     * @throws IllegalArgumentException if the given element is null
     */
    boolean remove(T element);

    /**
     * Checks if this MyCollection contains the specified element
     *
     * @param otherData the given element to search for in this MyCollection
     * @return true if this MyCollection contains the specified element
     * @throws IllegalArgumentException if the given element is null
     */
    boolean contains(T otherData);

    /**
     * Appends all the elements in the other MyCollection to this MyCollection
     *
     * @param other the list to add to this collection
     */
    void addAll(MyCollection<? extends T> other);

    /**
     * Removes all the elements in this MyCollection
     */
    void removeAll();

    /**
     * Checks whether this MyCollection contains all the elements from the given MyCollection
     *
     * @param other the MyCollection containing the elements we check
     * @return whether this MyCollection contains all the elements fro the given MyCollection
     */
    boolean containsAll(MyCollection<T> other);

    /**
     * Discard any element not contained in the given MyCollection
     *
     * @param other the given MyCollection containing the elements to retain
     */
    void retainAll(MyCollection<T> other);

    /**
     * Returns an array containing all the elements in this MyCollection
     *
     * @return an array containing all the elements in this MyCollection
     */
    Object[] toArray();
}
