import Models.DTs.MyLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * <p>This class contains a set of tests that can be used to test the
 * implementation of the MyLinkedList class</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyLinkedListTest {
    private MyLinkedList<Integer> myLinkedListExample;
    private MyLinkedList<Integer> myLinkedListExample2;
    private LinkedList<Integer> linkedListExample;

    @Before
    public void setUp() {
        myLinkedListExample = new MyLinkedList<>();
        myLinkedListExample2 = new MyLinkedList<>();
        linkedListExample = new LinkedList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	isEmpty Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void isEmptyListWhenTrue() {
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
        myLinkedListExample.add(5);
        myLinkedListExample.remove((Integer) 5);
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
    }

    @Test
    public void isEmptyListWhenFalse() {
        myLinkedListExample.add(5);
        assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
        myLinkedListExample.add(3);
        myLinkedListExample.remove((Integer) 5);
        assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	size Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sizeListWhenEmpty() {
        assertEquals("The size should be 0", 0, myLinkedListExample.size());
    }

    @Test
    public void sizeListWhenAdding() {
        myLinkedListExample.add(5);
        assertEquals("The size should be 1", 1, myLinkedListExample.size());
        myLinkedListExample.add(3);
        assertEquals("The size should be 2", 2, myLinkedListExample.size());
    }

    @Test
    public void sizeListWhenRemoving() {
        myLinkedListExample.add(5);
        myLinkedListExample.add(3);
        assertEquals("The size should be 2", 2, myLinkedListExample.size());
        myLinkedListExample.remove((Integer) 5);
        assertEquals("The size should be 1", 1, myLinkedListExample.size());
        myLinkedListExample.remove((Integer) 3);
        assertEquals("The size should be 0", 0, myLinkedListExample.size());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	clear Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void clearList() {
        myLinkedListExample.clear();
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
        myLinkedListExample.add(3);
        myLinkedListExample.add(6);
        myLinkedListExample.clear();
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	contains Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void containsEmptyList() {
        assertFalse("The list should not contain any element", myLinkedListExample.contains(5));
    }

    @Test
    public void containsWhenTrue() {
        myLinkedListExample.add(5);
        assertTrue("The list should contain the element " + 5, myLinkedListExample.contains(5));
        myLinkedListExample.add(3);
        assertTrue("The list should contain the element " + 3, myLinkedListExample.contains(3));
    }

    @Test
    public void containsWhenFalse() {
        myLinkedListExample.add(5);
        assertFalse("The list should not contain the element " + 3, myLinkedListExample.contains(3));
        myLinkedListExample.add(3);
        assertFalse("The list should not contain the element " + 8, myLinkedListExample.contains(8));
        myLinkedListExample.remove((Integer) 3);
        assertFalse("The list should not contain the element " + 3, myLinkedListExample.contains(3));
        myLinkedListExample.remove((Integer) 5);
        assertFalse("The list should not contain the element " + 5, myLinkedListExample.contains(5));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	add Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void addNode() {
        myLinkedListExample.add(0);
        assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
        assertEquals("The size should be 1", 1, myLinkedListExample.size());
        assertTrue("The list should contain the element " + 0, myLinkedListExample.contains(0));
    }

    @Test
    public void addNodes() {
        for (int i = 0; i < 5; i++) {
            myLinkedListExample.add(i);
        }
        assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
        assertEquals("The size should be 5", 5, myLinkedListExample.size());
        for (int i = 0; i < 5; i++) {
            assertTrue("The list should contain the element " + i, myLinkedListExample.contains(i));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	remove() Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = NoSuchElementException.class)
    public void removeHeadNodeEmptyList() {
        myLinkedListExample.remove();
    }

    @Test
    public void removeHeadNode() {
        myLinkedListExample.add(5);
        myLinkedListExample.add(3);
        myLinkedListExample.remove();
        assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
        assertEquals("The size should be 1", 1, myLinkedListExample.size());
        assertTrue("The list should still contain the element 3", myLinkedListExample.contains(3));
        assertFalse("The list should not contain the element 5", myLinkedListExample.contains(5));
        myLinkedListExample.remove();
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
        assertEquals("The size should be 0", 0, myLinkedListExample.size());
        assertFalse("The list should not contain the element 3", myLinkedListExample.contains(3));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	remove(int index) Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexNodeEmptyList() {
        myLinkedListExample.remove(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexNodeWhenNegativeIndex() {
        myLinkedListExample.remove(-2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexNodeWhenOutOfRange() {
        myLinkedListExample.add(3);
        myLinkedListExample.add(5);
        myLinkedListExample.remove(2);
    }

    @Test
    public void removeIndexNode() {
        for (int i = 3; i <= 12; i += 3) {
            myLinkedListExample.add(i);
        }
        int size = 4;
        for (int i = 3; i >= 0; i -= 3) {
            myLinkedListExample.remove(i);
            size--;
            assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
            assertEquals("The size should be " + size, size, myLinkedListExample.size());
            for (int j = 3 * i + 3; j >= 3; j -= 3) {
                if (j == 3 * i + 3) {
                    assertFalse("The list should not contain the element " + j,
                            myLinkedListExample.contains(j));
                } else {
                    assertTrue("The list should still contain the element " + j,
                            myLinkedListExample.contains(j));
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	remove(T Element) Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void removeElementNodeEmptyList() {
        Integer element = 3;
        assertFalse("The list should not contain the element 3", myLinkedListExample.remove(element));
    }

    @Test
    public void removeElementNode() {
        for (int i = 0; i < 5; i++) {
            myLinkedListExample.add(i);
        }
        int size = 5;
        for (Integer i = 0; i < 5; i += 2) {
            myLinkedListExample.remove(i);
            size--;
            assertFalse("The list should not be empty", myLinkedListExample.isEmpty());
            assertEquals("The size should be " + size, size, myLinkedListExample.size());
            for (int j = i; j < 5; j++) {
                if (j == i) {
                    assertFalse("The list should not contain the element " + i,
                            myLinkedListExample.contains(i));
                } else {
                    assertTrue("The list should contain the element " + j,
                            myLinkedListExample.contains(j));
                }
            }

        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	addAll Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void toStringComparisonEmptyLists() {
        assertEquals("The values stored in the list are " + myLinkedListExample.toString(),
                myLinkedListExample.toString(), linkedListExample.toString());
    }

    @Test
    public void toStringComparison() {
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
            linkedListExample.add(i);
        }
        myLinkedListExample.remove((Integer) 3);
        linkedListExample.remove((Integer) 3);
        myLinkedListExample.addAll(myLinkedListExample2);
        assertEquals("The values stored in the list are " + myLinkedListExample.toString(),
                myLinkedListExample.toString(), linkedListExample.toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	addAll Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void addAllNodes() {
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
            linkedListExample.add(i);
        }
        for (int i = 6; i <= 10; i++) {
            myLinkedListExample2.add(i);
            linkedListExample.add(i);
        }
        myLinkedListExample.addAll(myLinkedListExample2);
        for (int i = 1; i <= 10; i++) {
            assertTrue("The list should contain the element " + i, myLinkedListExample.contains(i));
        }
        assertEquals("The values stored in the list are " + myLinkedListExample.toString(),
                myLinkedListExample.toString(), linkedListExample.toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	removeAll Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void removeAllNodes() {
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
        }
        myLinkedListExample.removeAll();
        assertTrue("The list should be empty", myLinkedListExample.isEmpty());
        assertEquals("The values stored in the list should be the same " + myLinkedListExample.toString(),
                myLinkedListExample.toString(), linkedListExample.toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	containsAll Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void containsAllNodes() {
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
        }
        assertTrue("The list should contain all elements from the empty list",
                myLinkedListExample.containsAll(myLinkedListExample2));
        for (int i = 1; i <= 3; i++) {
            myLinkedListExample2.add(i);
        }
        assertTrue("The list should not contain all the elements from the partially filled list",
                myLinkedListExample.containsAll(myLinkedListExample2));
        for (int i = 3; i <= 5; i++) {
            myLinkedListExample2.add(i);
        }
        assertTrue("The list should contain all the elements from the copy list",
                myLinkedListExample.containsAll(myLinkedListExample2));
        for (int i = 6; i <= 8; i++) {
            myLinkedListExample2.add(i);
        }
        assertFalse("The list should not contain all the elements from the expanded list",
                myLinkedListExample.containsAll(myLinkedListExample2));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	retainAll Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void retainAllNodes() {
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
        }
        myLinkedListExample.retainAll(myLinkedListExample2);
        assertTrue("The list should contain no elements from the empty list",
                myLinkedListExample.isEmpty());
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
            if (i <= 3) {
                myLinkedListExample2.add(i);
            }
        }
        myLinkedListExample.retainAll(myLinkedListExample2);
        assertEquals("The list should contain only 3 elements from the partially filled list",
                3, myLinkedListExample.size());
        assertTrue("The list should contain all elements from the partially " +
                "filled list", myLinkedListExample.containsAll(myLinkedListExample2));
        for (int i = 4; i <= 5; i++) {
            myLinkedListExample.add(i);
            myLinkedListExample2.add(i);
        }
        assertEquals("The list should contain all 5 elements from the copied list",
                5, myLinkedListExample.size());
        assertTrue("Both lists should be equal", myLinkedListExample.equals(myLinkedListExample2));
        assertEquals("The values stored in the list should be the same " + myLinkedListExample.toString(),
                myLinkedListExample.toString(), myLinkedListExample2.toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	toArray Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testToArray() {
        Object[] resultArray = myLinkedListExample.toArray();
        assertEquals("The array should be empty", 0, resultArray.length);
        for (int i = 1; i <= 5; i++) {
            myLinkedListExample.add(i);
        }
        resultArray = myLinkedListExample.toArray();
        assertEquals("The array should contain 5 elements", 5, resultArray.length);

        Integer[] expectedArray = {1, 2, 3, 4, 5};
        for (int i = 0; i < resultArray.length; i++) {
            assertEquals("The element " + i + " of the array should be " + i,
                    expectedArray[i], resultArray[i]);
        }
    }
}
