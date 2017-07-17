import Models.DTs.MyGraph;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * <p>This class contains a set of tests that can be used to test the
 * implementation of the MyGraph class</p>
 *
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public final class MyGraphTest {


    ///////////////////////////////////////////////////////////////////////////////////////
    ////	Constructor
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testNoArgCtor() {
        MyGraph<String, String> g = new MyGraph<>();
        assertTrue(g.isEmpty());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	isEmpty Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testIsEmptyWhenTrue() {
        MyGraph<String, String> g = new MyGraph<>();
        assertTrue(g.isEmpty());
    }

    @Test
    public void testIsEmptyWhenFalseAndNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertFalse(g.isEmpty());
        g.addNode("two");
        assertFalse(g.isEmpty());
    }

    @Test
    public void testIsEmptyWhenFalseAndEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertFalse(g.isEmpty());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	containsNode Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testContainsNodeWhenParameterNull() {
        MyGraph<String, String> g = new MyGraph<>();
        g.containsNode(null);
    }

    @Test
    public void testContainsNodeWhenTrueAndNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertTrue(g.containsNode("one"));
        g.addNode("two");
        assertTrue(g.containsNode("one"));
        assertTrue(g.containsNode("two"));
    }

    @Test
    public void testContainsNodeWhenTrueAndEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertTrue(g.containsNode("one"));
        assertTrue(g.containsNode("two"));
        g.addNode("three");
        g.addEdge("one", "two", "one-two-B");
        g.addEdge("one", "three", "one-three-A");
        assertTrue(g.containsNode("one"));
        assertTrue(g.containsNode("two"));
        assertTrue(g.containsNode("three"));
    }

    @Test
    public void testContainsNodeWhenFalseAndEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        assertFalse(g.containsNode("one"));
    }

    @Test
    public void testContainsNodeWhenFalseAndNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertFalse(g.containsNode("three"));
        g.addNode("two");
        assertFalse(g.containsNode("three"));
    }

    @Test
    public void testContainsNodeWhenFalseAndEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertFalse(g.containsNode("four"));
        g.addEdge("one", "three", "one-three-A");
        assertFalse(g.containsNode("four"));
        g.addEdge("one", "two", "one-two-B");
        assertFalse(g.containsNode("four"));
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    ////	containsEdge Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testContainsEdgeWhenParameterNull() {
        MyGraph<String, String> g = new MyGraph<>();
        g.containsEdge(null, "two");
    }

    @Test
    public void testContainsEdgeWhenTrue() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertTrue(g.containsEdge("one", "two"));
        g.addNode("three");
        g.addEdge("one", "three", "one-three-A");
        assertTrue(g.containsEdge("one", "three"));
        g.addEdge("one", "two", "one-two-B");
        assertTrue(g.containsEdge("one", "two"));
    }

    @Test
    public void testContainsEdgeWhenFalseAndEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        assertFalse(g.containsEdge("one", "two"));
    }

    @Test
    public void testContainsEdgeWhenFalseAndNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        assertFalse(g.containsEdge("one", "two"));
        g.addNode("one");
        assertFalse(g.containsEdge("one", "two"));
        g.addNode("two");
        assertFalse(g.containsEdge("one", "two"));
    }

    @Test
    public void testContainsEdgeWhenFalseAndEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertFalse(g.containsEdge("one", "four"));
        g.addEdge("one", "three", "one-three-A");
        assertFalse(g.containsEdge("one", "four"));
        g.addEdge("three", "four", "three-four-A");
        assertFalse(g.containsEdge("one", "four"));
        g.addEdge("three", "four", "three-four-B");
        assertFalse(g.containsEdge("one", "four"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	getNodes Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testGetNodesWhenEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        assertEquals("[]", g.getNodes().toString());
    }

    @Test
    public void testGetNodesWhenNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertEquals("[one]", g.getNodes().toString());
        g.addNode("two");
        // next line changed to work with HashMap for overall graph Map
        assertEquals("[one, two]", g.getNodes().toString());
    }

    @Test
    public void testGetNodesWhenEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        // next line changed to work with HashMaps for overall graph Map
        assertEquals("[one, two]", g.getNodes().toString());
        g.addNode("three");
        assertEquals("[one, two, three]", g.getNodes().toString());
        g.addEdge("one", "two", "one-two-B");
        assertEquals("[one, two, three]", g.getNodes().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	getNeighbors Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testGetNeighborsWhenParameterNull() {
        MyGraph<String, String> g = new MyGraph<>();
        g.getNeighbors(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNeighborsWhenEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        g.getNeighbors("one");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNeighborsWhenNodeNotContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        g.getNeighbors("two");
    }

    @Test
    public void testGetNeighborsWhenNoEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertEquals("[]", g.getNeighbors("one").toString());
        g.addNode("two");
        assertEquals("[]", g.getNeighbors("one").toString());
        assertEquals("[]", g.getNeighbors("two").toString());
    }

    @Test
    public void testGetNeighborsWhenEdges() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertEquals("[two]", g.getNeighbors("one").toString());
        g.addNode("three");
        assertEquals("[two]", g.getNeighbors("one").toString());
        g.addEdge("one", "two", "one-two-B");
        assertEquals("[two]", g.getNeighbors("one").toString());
        g.addEdge("one", "three", "one-three-A");
        // next line fixed to work with endNodes HashMap
        assertEquals("[two, three]", g.getNeighbors("one").toString());
        assertEquals("[]", g.getNeighbors("two").toString());
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    ////	getNodesEdgeLabels Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testGetNodesEdgeLabelsWhenParameterNull() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.getNodesEdgeLabels(null, "two");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNodesEdgeLabelsWhenEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        g.getNodesEdgeLabels("one", "two");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNodesEdgeLabelsWhenEdgeNotContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.getNodesEdgeLabels("one", "three");
    }

    @Test
    public void testGetNodesEdgeLabelsWhenOneEdgeLabel() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        g.addEdge("one", "three", "one-three-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
    }

    @Test
    public void testGetNodesEdgeLabelsWhenManyEdgeLabels() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("one", "two", "one-two-B");
        Set<String> result = new TreeSet<>();
        result.addAll(g.getNodesEdgeLabels("one", "two"));
        assertEquals("[one-two-A, one-two-B]", result.toString());
        g.addEdge("one", "three", "one-three-A");
        g.addNode("four");
        g.addEdge("one", "four", "one-four-A");
        result.clear();
        result.addAll(g.getNodesEdgeLabels("one", "two"));
        assertEquals("[one-two-A, one-two-B]", result.toString());
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
        assertEquals("[one-four-A]", g.getNodesEdgeLabels("one", "four").toString());
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    ////	addNode	Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddNodeWhenEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        assertEquals("[one]", g.getNodes().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeWhenParameterNull() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeWhenNodeAlreadyContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        g.addNode("one");
    }

    @Test
    public void testAddNode() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        g.addNode("two");
        g.addNode("three");
        // Next line changed to work with HashMaps for overall graph Map
        assertEquals("[one, two, three]", g.getNodes().toString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////	addEdge Tests
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testAddEdgeWhenEmpty() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
    }

    @Test
    public void testAddEdgesWithOneLabel() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("one", "three", "one-three-A");
        g.addEdge("two", "three", "two-three-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
        assertEquals("[two-three-A]", g.getNodesEdgeLabels("two", "three").toString());
    }

    @Test
    public void testAddEdgesToExistingNodes() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        g.addNode("two");
        g.addNode("three");
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("one", "three", "one-three-A");
        g.addEdge("two", "three", "two-three-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
        assertEquals("[two-three-A]", g.getNodesEdgeLabels("two", "three").toString());
    }

    @Test
    public void testAddEdgesWhenNodePointedIsNotContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("one");
        g.addNode("two");
        g.addNode("three");
        g.addEdge("one", "three", "one-three-A");
        g.addEdge("two", "three", "two-three-A");
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
        assertEquals("[two-three-A]", g.getNodesEdgeLabels("two", "three").toString());
    }

    @Test
    public void testAddEdgesWhenNodePointingIsNotContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addNode("two");
        g.addNode("three");
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("one", "three", "one-three-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        assertEquals("[one-three-A]", g.getNodesEdgeLabels("one", "three").toString());
    }

    @Test
    public void testAddEdgesWhenNodesNotContained() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("three", "four", "three-four-A");
        assertEquals("[one-two-A]", g.getNodesEdgeLabels("one", "two").toString());
        assertEquals("[three-four-A]", g.getNodesEdgeLabels("three", "four").toString());
    }

    @Test
    public void testAddEdgesWithManyEdgeLabels() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "two", "one-two-A");
        g.addEdge("one", "three", "one-three-A");
        g.addEdge("one", "three", "one-three-B");
        g.addEdge("two", "three", "two-three-A");
        g.addEdge("two", "three", "two-three-B");
        g.addEdge("two", "three", "two-three-C");
        Set<String> result = new TreeSet<>();
        result.addAll(g.getNodesEdgeLabels("one", "two"));
        assertEquals("[one-two-A]", result.toString());
        result.clear();
        result.addAll(g.getNodesEdgeLabels("one", "three"));
        assertEquals("[one-three-A, one-three-B]", result.toString());
        result.clear();
        result.addAll(g.getNodesEdgeLabels("two", "three"));
        assertEquals("[two-three-A, two-three-B, two-three-C]",
                result.toString());
    }

    @Test
    public void testAddEdgesToItself() {
        MyGraph<String, String> g = new MyGraph<>();
        g.addEdge("one", "one", "one-one-A");
        g.addEdge("one", "one", "one-one-B");
        g.addNode("three");
        g.addEdge("three", "three", "three-three-A");
        Set<String> result = new TreeSet<>();
        result.addAll(g.getNodesEdgeLabels("one", "one"));
        assertEquals("[one-one-A, one-one-B]", result.toString());
        assertEquals("[three-three-A]", g.getNodesEdgeLabels("three", "three").toString());
    }
}
