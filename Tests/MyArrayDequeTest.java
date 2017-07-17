import Models.DTs.MyArrayDeque;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * <p>This class contains a set of tests that can be used to test the
 * implementation of the MyArrayDeque class</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyArrayDequeTest {
    private MyArrayDeque<Integer> myQueueTest;
    private Queue<Integer> queueTest;

    @Before
    public void setUp() {
        myQueueTest = new MyArrayDeque<>();
        queueTest = new ArrayDeque<>();
    }

    @After
    public void tearDown() {
        myQueueTest = null;
        queueTest = null;
    }

    @Test(expected = NoSuchElementException.class)
    public void removeEmptyQueue() {
        myQueueTest.remove();
    }

    @Test
    public void peekEmptyQueue() {
        assertNull("peek should return null for an empty myQueue", myQueueTest.peek());
    }

    @Test
    public void addRemoveQueue() {
        for (int i = 0; i < 5; i++) {
            myQueueTest.add(i);
            queueTest.add(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals("remove should return same value for both queue" +
                    "implementations", queueTest.remove(), myQueueTest.remove());
        }
    }

    @Test
    public void addSizeStack() {
        int elements = 0;
        for (int i = 0; i < 5; i++) {
            elements++;
            myQueueTest.add(i);
            assertEquals("The size of the queue should increment after every add",
                    elements, myQueueTest.size());
        }
    }

    @Test
    public void addRemoveSizeStack() {
        int queueSize = 0;
        for (int i = 0; i < 5; i++) {
            myQueueTest.add(i);
            queueSize++;
            assertEquals("The size of the queue should increment after every add",
                    queueSize, myQueueTest.size());
        }
        for (int i = myQueueTest.size() - 1; i == 0; i--) {
            myQueueTest.remove();
            queueSize--;
            assertEquals("The size of the queue must decrease after every remove",
                    queueSize, myQueueTest.size());
        }
    }

    @Test
    public void iteratorQueue() {
        int queueSize = 0;
        for (int i = 0; i < 10; i++) {
            myQueueTest.add(i);
            queueSize++;
        }
        Iterator<Integer> iterator = myQueueTest.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            int valueRemoved = iterator.next();
            iterator.remove();
            queueSize--;
            assertEquals("The size of the queue must decrease by 1 after removing" +
                    "an element using the iterator", queueSize, myQueueTest.size());
            assertEquals("The value removed should be " + i, i, valueRemoved);
            i++;
        }
    }

    @Test
    public void foreachStack() throws Exception {
        for (int i = 0; i < 5; i++) {
            myQueueTest.add(i);
            queueTest.add(i);
        }
        for (Integer currQueueElement : myQueueTest) {
            assertEquals("The next value should be " + queueTest.peek(), queueTest.remove(), currQueueElement);
        }
    }
}