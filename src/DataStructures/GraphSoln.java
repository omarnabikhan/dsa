package DataStructures;
import java.util.*;


/*
 * Author: Omar Khan
 *
 * Graph representation is an adjancency list. So if an edge exists between U and V with weight W, then in U's
 * adjacency list there will be the edge (U, V) with weight W and (if the graph is undirected) in V's adjacency
 * list there will be the edge (V, U) with weight W

 */


public class GraphSoln <T> implements Graph<T> {


    /*
     * The Node class deals with all things Node. Nodes keep track of who they're next to (adjacent)
     * as well as the corresponding edge
     */
    private class Node <T> {
        T myItem;
        Set<Node> neighbors = new HashSet<>();
        Map<Node, Edge> edges = new HashMap<>();

        public Node(T item) {
            myItem = item;
        }

        public Edge addNeighbor(Node addMe, int weight) {
            neighbors.add(addMe);
            Edge newEdge = new Edge(this, addMe, weight);
            edges.put(addMe, newEdge);
            return newEdge;
        }

        // for unweighted graphs
        public Edge addNeighbor(Node addMe) {
            return addNeighbor(addMe, 1);
        }

        public void delNeighbor(Node delMe) {
            neighbors.remove(delMe);
            edges.remove(delMe);
        }

        public void changeEdge(Node v, int w) {
            edges.get(v).weight = w;
        }

        public boolean connectedTo(Node v) {
            return neighbors.contains(v);
        }

        @Override
        public boolean equals(Object o) {
            if (o.getClass() != getClass()) {
                return false;
            } else {
                return ((Node) o).myItem == myItem;
            }
        }

        @Override
        public int hashCode() {
            return myItem.hashCode();
        }
    }

    private class Edge {
        private int weight;
        private Node from;
        private Node to;

        public Edge(Node u, Node v, int w) {
            weight = w;
            from = u;
            to = v;
        }

        // for unweighted graphs
        public Edge(Node u, Node v) {
            this(u, v, 1);
        }
    }


    private Map<T, Node> myNodes = new HashMap<>();
    private boolean directed = false; // by default make undirected
    private int v = 0, e = 0;

    public GraphSoln(boolean d) {
        directed = d;
    }

    @Override
    public int numNodes() {
        return v;
    }

    public int numEdges() {
        return e;
    }

    // adds the Node (with no neighbors yet) to the Graph. Returns 0 if successful, and
    // anything else otherwise (i.e. X is already in the graph)
    public int addNode(T x) {
        Node newNode = new Node(x);
        if (myNodes.containsKey(newNode)) {
            return 1;
        } else {
            myNodes.put(x, newNode);
            v += 1;
            return 0;
        }
    }

    // removes the node X and all it's neighbors. Returns 0 if successful, and
    // anything else otherwise (i.e. X wasn't in the graph)

    public int delNode(T x) {
        if (!myNodes.containsKey(x)) {
            return 1; // x isn't in the graph
        } else {
            Node delMe = myNodes.get(x);
            myNodes.remove(x); // removed from our nodeset
            for (Node node : myNodes.values()) {
                if (node.connectedTo(delMe)) {
                    node.delNeighbor(delMe);
                    e -= 1;
                }
            }
            v -= 1;
            return 0;
        }
    }


    // returns an iterable of all the neighobrs of X in no particular order
    public Iterable<T> neighbors(T x) {
        List<T> result = new LinkedList();
        Node node = myNodes.get(x);
        for (Object neighborObj : node.neighbors) {
            Node neighbor = (Node) neighborObj;
            result.add((T) neighbor.myItem);
        }
        return result;
    }


    // iterate through all the nodes in any order
    public Iterable<T> getNodes() {
        List<T> result = new LinkedList<>();
        for (Node node : myNodes.values()) {
            result.add((T) node.myItem);
        }
        return result; // potential mutation issue
    }


    // add an edge of weight W from U to V. Returns 0 if successful, and
    // anything else otherwise (i.e. U or V isn't in the graph)
    public int addEdge(T u, T v, int w) {

        if (!(myNodes.containsKey(u) && myNodes.containsKey(v))) {
            return 1; // must have both
        }

        if (u.equals(v)) { // self edge is fine but does nothing
            return 0;
        }

        Node src = myNodes.get(u);
        Node dest = myNodes.get(v);

        src.addNeighbor(dest, w);

        if (!directed) { // only add the reverse edge if it's undirected
            dest.addNeighbor(src, w);
        }
        e += 1;
        return 0;
    }

    // similar to above, but for graphs with unit weight (all weighted to 1)
    public int addEdge(T u, T v) {
        return addEdge(u, v, 1);
    }


    // changes the weight of the edge from U to V. Returns 0 if successful, and
    // anything else otherwise (i.e. U or V isn't in the graph)
    public int changeEdge(T u, T v, int w) {

        if (!(myNodes.containsKey(u) && myNodes.containsKey(v))) {
            return 1; // must have both
        }

        Node src = myNodes.get(u);
        Node dest = myNodes.get(v);

        src.changeEdge(dest, w);

        if (!directed) {
            dest.changeEdge(src, w);
        }
        return 0;

    }


    // returns whether or not the nodes U and V are connected. If U or V isn't a node, return False
    public boolean connected(T u, T v) {
        return false;
    }

    public void clear(){
        myNodes = new HashMap<>();
        v = 0;
        e = 0;
    }

}
