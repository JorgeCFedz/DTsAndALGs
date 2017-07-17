import Models.DTs.MyHashTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * <p>This class contains a set of tests that can be used to test the
 * implementation of the MyHashTable class</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyHashTableTest {
    private MyHashTable myHashTableTest;
    private Integer dummyValue;

    @Before
    public void setUp() {
        myHashTableTest = new MyHashTable();
    }

    @After
    public void tearDown() {
        myHashTableTest = null;
        dummyValue = null;
    }

    @Test(expected = NullPointerException.class)
    public void putNullKey() {
        Integer value = 5;
        myHashTableTest.put(null, value);
    }

    @Test(expected = NullPointerException.class)
    public void putNullValue() {
        Integer key = 5;
        myHashTableTest.put(key, null);
    }

    @Test
    public void putItemsNoCollisions() {
        for (int i = 0; i < 20; i++) {
            Integer key = i;
            Integer value = i * 2;
            myHashTableTest.put(key, value);
        }
        for (Integer i = 0; i < 20; i++) {
            Integer key = i;
            dummyValue = myHashTableTest.get(key);
            assertEquals("the value stored at the hashEntry " + i +
                    " must be equal to " + i * 2, (Integer) (i * 2), dummyValue);
        }
    }

    @Test
    public void putDuplicatedKeys() {
        // hashing
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            myHashTableTest.put(key, value);
        }

        // collisions
        for (int i = 18; i < 22; i++) {
            if (i % 2 == 0) {
                Integer key = i;
                Integer value = (i % 20) * 2;
                myHashTableTest.put(key, value);
            }
        }

        // verify values
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            if (i % 2 == 0) {
                value *= 2;
            }
            assertEquals("the value stored at the hashEntry " + key +
                    " must be equal to " + value, value, myHashTableTest.get(key));
        }
    }

    @Test
    public void putItemsCollisions() {
        // put consecutive items that rollup over the end of the table
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            myHashTableTest.put(key, value);
        }

        // put consecutive items with collisions that roll up over the end of the table
        for (int i = 18; i < 22; i++) {
            Integer key = i + 20;
            Integer value = (i % 20) * 2;
            myHashTableTest.put(key, value);
        }

        // verify the items puted without collision are stored in the hash table
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            assertEquals("the value stored at the hashEntry " + key % 20 +
                    " must be equal to " + value, value, myHashTableTest.get(key));
        }

        // verify the items puted with collision are stored in the hash table
        // using linear-probing
        for (int i = 18; i < 22; i++) {
            Integer key = i + 20;
            Integer value = (i % 20) * 2;
            assertEquals("the value stored at the hashEntry " + (i + 4) % 20 +
                    " must be equal to " + value, value, myHashTableTest.get(key));
        }

        // verify the rest of the hash entries have not been used
        // NOTE/ FIX: does not really check if any key is stored in those hash entries but just looks
        // for that key everywhere in the table
        // NOTE: It does check it because otherwise it would find a null value at the
        // correspondent hash entry and return a NULL value during the get
        for (int i = 6; i < 18; i++) {
            Integer key = i;
            assertNull("all the hash table entries with hash key between 6 and 17 should be empty",
                    myHashTableTest.get(key));
        }
    }

    @Test
    public void removeCollisionItems() {
        // put consecutive items with key value equal to hash entry
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            myHashTableTest.put(key, value);
        }

        // put consecutive items with collisions but different key value
        for (int i = 18; i < 22; i++) {
            Integer key = i + 20;
            Integer value = (i % 20) * 2;
            myHashTableTest.put(key, value);
        }

        // remove collision items
        for (int i = 18; i < 22; i++) {
            myHashTableTest.remove(i + 20);
        }

        // verify collision items have been removed but not the new ones
        // NOTE/ FIX: does not really check if the original keys are reordered into different
        // hash entries but just looks for that key everywhere in the table
        // NOTE: It does check it because otherwise it would find a null value at the
        // correspondent hash entry and return a NULL value during the get
        for (int i = 18; i < 22; i++) {
            Integer removedKey = i + 20;
            Integer originalKey = i;
            Integer originalValue = i % 20;
            assertNull(myHashTableTest.get(removedKey));
            assertEquals("the value stored at the hashEntry " + (i) % 20 +
                            " must be equal to " + originalValue, originalValue,
                    myHashTableTest.get(originalKey));
        }
    }

    @Test
    public void removeAndReorganizeItems() {
        // put consecutive items with key value equal to hash entry
        for (int i = 18; i < 22; i++) {
            Integer key = i;
            Integer value = (i % 20);
            myHashTableTest.put(key, value);
        }

        // put consecutive items with collisions but different key value
        for (int i = 18; i < 22; i++) {
            Integer key = i + 20;
            Integer value = (i % 20) * 2;
            myHashTableTest.put(key, value);
        }

        // remove original items
        for (int i = 18; i < 22; i++) {
//            dummyItem = MyHashTableTest.get(i);
            myHashTableTest.remove(i);
        }

        // verify original items have been removed but not collision ones
        // NOTE/ FIX: does not really check if the collision keys are reordered into their
        // correspondent hash entries but just looks for that key everywhere in the table
        // NOTE: It does check it because otherwise it would find a null value at the
        // correspondent hash entry and return a NULL value during the get
        for (int i = 18; i < 22; i++) {
            Integer removedKey = i;
            Integer collisionKey = i + 20;
            Integer collisionValue = (i % 20) * 2;
            assertNull(myHashTableTest.get(removedKey));
            assertEquals("the value stored at the hashEntry " + (i) % 20 +
                            " must be equal to " + collisionValue, collisionValue,
                    myHashTableTest.get(collisionKey));
        }
    }
}