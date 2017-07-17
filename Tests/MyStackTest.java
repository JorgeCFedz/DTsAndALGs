import Models.DTs.MyStack;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>This class contains a set of tests that can be used to test the
 * implementation of the MyStack class</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyStackTest {
    private MyStack<Integer> myStackTest;
    private Stack<Integer> stackTest;

    @Before
    public void setUp() {
        myStackTest = new MyStack<>();
        stackTest = new Stack<>();
    }

    @Test(expected = EmptyStackException.class)
    public void popEmptyStack() {
        myStackTest.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void peekEmptyStack() {
        myStackTest.peek();
    }

    @Test
    public void pushPopStack() {
        for (int i = 0; i < 5; i++) {
            myStackTest.push(i);
            stackTest.push(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals("pop should return same value for both stack " +
                    "implementations", stackTest.pop(), myStackTest.pop());
        }
    }

    @Test
    public void pushSizeStack() {
        int elements = 0;
        for (int i = 0; i < 5; i++) {
            elements++;
            myStackTest.push(i);
            assertEquals("The size of the stack should increment after every push",
                    elements, myStackTest.size());
        }
    }

    @Test
    public void pushPopSizeStack() {
        int stackSize = 0;
        for (int i = 0; i < 5; i++) {
            myStackTest.push(i);
            stackSize++;
            assertEquals("The size of the stack should increment after every push",
                    stackSize, myStackTest.size());
        }
        for (int i = myStackTest.size() - 1; i == 0; i--) {
            myStackTest.pop();
            stackSize--;
            assertEquals("The size of the stack must decrease after every pop",
                    stackSize, myStackTest.size());
        }
    }

    @Test
    public void iteratorStack() {
        int stackSize = 0;
        for (int i = 0; i < 5; i++) {
            myStackTest.push(i);
            stackSize++;
        }
        Iterator<Integer> iterator = myStackTest.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            stackSize--;
            assertEquals("The size of the stack must decrease by 1 after removing" +
                    "an element using the iterator", stackSize, myStackTest.size());
        }
    }

    @Test
    public void foreachStack() throws Exception {
        List<Integer> stackElements = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            myStackTest.push(i);
            stackElements.add(i);
        }

        for (Integer currStackElement : myStackTest) {
            stackElements.remove(currStackElement);
        }
        assertTrue("The stackElements list must be empty after removing " +
                "all elements", stackElements.isEmpty());
    }
}