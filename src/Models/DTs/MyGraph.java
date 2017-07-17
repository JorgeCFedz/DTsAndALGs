package Models.DTs;

import java.util.*;

/**
 * <p>
 * <b>MyGraph</b> represents a mutable directed label multigraph
 * Each graph represents a collection of nodes,
 * where each node can point to any other node through a directed edge,
 * with an unlimited number of directed edges between 2 nodes
 * </p>
 * <p>
 * <b>Abstract Invariant</b>:
 * A graph does not contain duplicate nodes, or a duplicate edge between same
 * nodes with same edgeLabel
 * </p>
 * <p>
 * <b>Specification fields</b>:
 * @param <N> The type for the graph nodes
 * @param <E> The type for the graph edges
 * @custom.specfield node: String // Each vertex of the graph
 * @custom.specfield edge: String // Each directed edge between 2 nodes of the graph
 * @custom.specfield edgeLabel: String // The value associated with each edge
 * </p>
 * @author JorgeCFedz
 * @version 1.0
 * @since 1.0
 */
public class MyGraph<N, E> {
    private static final boolean CHECK_REP = false;
    private final Map<N, Map<N, Set<E>>> graphMap;

    /**
     * Abstract function:
     * Nodes = graphMap.keySet()
     * Edge = graphMap.get(node).keySet()
     * edgeLabels = graphMap.get(node_origin).get(node_end)
     *
     * Representation Invariant:
     * graphMap is not null &&
     * Set<String> graphMap.keySet() contains no duplicates &&
     * Set<String> graphMap.get(node).keySet() contains no duplicates &&
     * Set<String> graphMap.get(node_origin).get(node_end) contains no duplicates &&
     * If an edge exist, its edgeLabels cannot be null
     */


    /**
     * Creates a new Empty MyGraph
     *
     * @custom.effects A new empty graph will be created
     */
    public MyGraph() {
        graphMap = new HashMap<>();
        check_rep();
    }

    /**
     * checks if the graph is empty
     *
     * @return whether the graph contains any node
     */
    public boolean isEmpty() {
        return graphMap.isEmpty();
    }

    /**
     * checks if the graph contains the given node
     *
     * @param n, the given node
     * @return whether the graph already contains the given node
     * @throws IllegalArgumentException if n is null
     */
    public boolean containsNode(N n) {
        if (n == null) {
            throw new IllegalArgumentException("the value passed is null");
        }
        return graphMap.containsKey(n);
    }

    /**
     * checks if the graph contains an edge between the two given nodes
     *
     * @param origin, the node of origin of the edge
     * @param end,    the ending node of the directed edge
     * @return whether there is an edge from origin to end
     * @throws IllegalArgumentException if origin or end are null
     */
    public boolean containsEdge(N origin, N end) {
        if (origin == null || end == null) {
            throw new IllegalArgumentException("the value passed is null");
        } else
            return !(!containsNode(origin) || graphMap.get(origin) == null) && graphMap.get(origin).containsKey(end);
    }

    /**
     * obtains all the nodes currently contained in this graph
     * and returns them
     *
     * @return all the nodes of this graph
     */
    public Set<N> getNodes() {
        return Collections.unmodifiableSet(graphMap.keySet());
    }

    /**
     * obtains all the neighbor nodes to the given one and returns them
     *
     * @param origin, the node of origin
     * @return a Set with all the neighbor nodes to origin
     * @throws IllegalArgumentException if origin is null or the graph does not contain origin
     */
    public Set<N> getNeighbors(N origin) {
        if (origin == null || !containsNode(origin)) {
            throw new IllegalArgumentException("the value passed is null or the node"
                    + " is not contained in the graph");
        } else {
            return Collections.unmodifiableSet(graphMap.get(origin).keySet());
        }
    }

    /**
     * Obtains all the edges between the two given nodes and returns them
     *
     * @param origin, the node of origin of the edge
     * @param end,    the ending node for the directed edge
     * @return the set of edges between origin and end
     * @throws IllegalArgumentException if origin or end are null, or
     *                                  there isn't an edge between origin and end
     */
    public Set<E> getNodesEdgeLabels(N origin, N end) {
        if (origin == null || end == null || !containsEdge(origin, end)) {
            throw new IllegalArgumentException("the value passed is null or there"
                    + "isn't an edge between these 2 nodes");
        } else {
            return Collections.unmodifiableSet(graphMap.get(origin).get(end));
        }
    }

    /**
     * adds the given node to the graph
     *
     * @param n, the given node
     * @throws IllegalArgumentException if n is null or graph already contains n
     * @custom.modifies graphMap by adding a New key
     * @custom.effects graphMap will contain a key representing n
     */
    public void addNode(N n) {
        check_rep();
        if (n == null || containsNode(n)) {
            throw new IllegalArgumentException("The value passed is null or the"
                    + " node already exist");
        } else {
            graphMap.put(n, new HashMap<>());
            check_rep();
        }
    }

    /**
     * adds the given edge to the graph
     *
     * @param origin    the origin of the edge added
     * @param end       the end of the edge added
     * @param edgeLabel the edge added
     * @throws IllegalArgumentException if origin, end, or edgeLabels are null
     * @custom.modifies graphMap by adding a new edge, or a new label to an existing edge
     * @custom.effects graphMap will contain an edge with the given edgeLabel between
     * origin and end
     */
    public void addEdge(N origin, N end, E edgeLabel) {
        check_rep();
        if (origin == null || end == null || edgeLabel == null) {
            throw new IllegalArgumentException("the object passed is null");
        } else if (!containsNode(end)) {
            graphMap.put(end, new HashMap<>());
        }
        if (!containsNode(origin)) {
            Set<E> edgeLabels = new HashSet<>();
            edgeLabels.add(edgeLabel);
            graphMap.put(origin, new HashMap<>());
            graphMap.get(origin).put(end, edgeLabels);
        } else if (!containsEdge(origin, end)) {
            Set<E> edgeLabels = new HashSet<>();
            edgeLabels.add(edgeLabel);
            graphMap.get(origin).put(end, edgeLabels);
        } else {
            graphMap.get(origin).get(end).add(edgeLabel);
        }
        check_rep();
    }

    /**
     * Checks that rep variant holds if CHECK_REP is set to true
     * <p>
     * Doesn't check duplicate keys in the node map because by definition, a map
     * contains no duplicate keys, and all the keys of the map are strings
     * <p>
     * Doesn't check duplicate edges, because by definition, the edges map
     * contains no duplicate keys, and all the keys of the map are strings
     * <p>
     * Doesn't check duplicate edgeLabels, because by definition, the
     * edgeLabels Set contains no duplicates, and all the elements are Strings
     * <p>
     * Checks if any existing edge does not contain a label
     */
    private void check_rep() {
        if (CHECK_REP) {
            for (N origin : graphMap.keySet()) {
                for (N end : graphMap.get(origin).keySet()) {
                    assert !graphMap.get(origin).get(end).isEmpty();
                    if (graphMap.get(origin).get(end).isEmpty()) {
                        throw new IllegalStateException("The existing edge between " + origin
                                + " and " + end + " has no label ");
                    }
                }
            }
        }
    }
}
