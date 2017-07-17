package Models.DTs;

import java.util.LinkedList;

/**
 * <p><b>MyHashTable</b> represents a mutable HashTable ADT implemented
 * using separate chaining Hashtable DT</p>
 * <p>The table2 resizes when capacity * load factor = size</p>
 * <p>Load factor must be less than 1 for this implementation</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyHashTable {
    private int SIZE = 19;                          // use prime integer to avoid clustering
    private int elemCount = 0;
    private LinkedList<HashEntry>[] table;                                                                              // implement resizing by rehashing all keys (add remove)
    private HashEntry[] table2;
    private double LOAD_FACTOR = 0.75;                                   // fixed threshold

    public MyHashTable() {
//        table2 = new HashEntry[SIZE];
        table = new LinkedList[SIZE];
    }

    /**
     * @param key the hash key associated with the data item
     * @return the hash code for the given key
     */
    private int hashCode(int key) {
        return key % SIZE;
    }

    /**
     * @param key the key to look for
     * @return the item stored in the hash table associated with the given key
     * or null if there is no item associated with that key
     * @throws NullPointerException if the key given is null
     */
    public Integer get(Integer key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("The given key is null");
        }
        int hash = hashCode(key);
        LinkedList<HashEntry> lookUp = table[hash];
        if (lookUp == null) {
            return null;
        }
        for (HashEntry item : lookUp) {
            if (item.key == key) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * @param key,   the key to look for
     * @param value, the value associated with the given key
     * @return the previous value for the given key in this hash table or null if it did not have one
     * @throws NullPointerException if the key or value given are null
     */
    public Integer put(Integer key, Integer value) throws NullPointerException {
        if (key == null || value == null) {
            throw new NullPointerException("The key or data value given are null");
        }
        int hash = hashCode(key);
        LinkedList<HashEntry> lookUp = table[hash];
        if (lookUp == null) {
            lookUp = new LinkedList<>();
            lookUp.add(new HashEntry(key, value));
            table[hash] = lookUp;
            return null;
        }
        for (HashEntry item : lookUp) {
            if (item.key == key) {
                int result = item.value;
                item.value = value;
                return result;
            }
        }
        lookUp.add(new HashEntry(key, value));
        return null;
    }

    /**
     * Note: many implementations do not include the delete function
     * Option 1: Python implementation that inserts dummy item at the hash entry where the item was deleted
     * Option 2: Knuth recommends marking the entries as empty, full, or deleted
     * Option 3: Reorder any consecutive entry of the hash table2 until reaching an empty entry
     * used Option 3
     *
     * @param key the key to remove from this hash table2
     * @throws IllegalArgumentException if the item given is null
     * @return the value associated with the given key or null
     *          if there isn't a mapping for this key
     */
    public Integer remove(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("The item given is null");
        }
        int hash = hashCode(key);
        LinkedList<HashEntry> lookUp = table[hash];
        if (lookUp == null) {
            return null;
        }
        for (HashEntry item : lookUp) {
            if (item.key == key) {
                int result = item.value;
                lookUp.remove(item);
                return result;
            }
        }
        return null;
    }

    /**
     * Mutable (can be modified) inner class (nested and non-static) that
     * emulates a C struct using Java. This is appropriate according to Java guidelines.
     * Keys cannot be modified (would alter hash table2 integrity but values can)
     */
    private class HashEntry {
        private final Integer key;
        private Integer value;

        public HashEntry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return this.key;
        }

        public Integer getValue() {
            return this.value;
        }
    }
}
