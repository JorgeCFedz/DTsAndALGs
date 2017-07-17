import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;


/**
 * <p>This class controls the execution of all JUnit tests that can be used to test
 * the implementation for the different data structures implementation</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class DTTests {

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(MyLinkedListTest.class, MyStackTest.class, MyArrayDequeTest.class, MyHashTableTest.class, MyGraphTest.class);
    }
}