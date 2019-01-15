package DataStructures;

/*

 * Author: Omar Khan
 * This is a preliminary undirected graph interface. The files "GraphSoln.java" and "GraphSkeleton.java" are
 * respectively the solution and the skeleton code for the students to fill out
 */


public interface Graph <T> {

    // how many nodes this graph has
    public int numNodes();

    // how many edges this graph has
    public int numEdges();

    // adds the Node (with no neighobrs yet) to the Graph. Returns 0 if successful, and
    // anything else otherwise (i.e. X is already in the graph)
    public int addNode(T x);

    // removes the node X and all it's neighbors. Returns 0 if successful, and
    // anything else otherwise (i.e. X wasn't in the graph)

    public int delNode(T x);


    // returns an iterable of all the neighobrs of X in no particular order
    public Iterable<T> neighbors(T x);


    // iterate through all the nodes in any order
    public Iterable<T> getNodes();


    // add an edge of weight W from U to V. Returns 0 if successful, and
    // anything else otherwise (i.e. U or V isn't in the graph)
    public int addEdge(T u, T v, int w);

    // similar to above, but for graphs with unit weight (all weighted to 1)
    public int addEdge(T u, T v);


    // changes the weight of the edge from U to V.Returns 0 if successful, and
    // anything else otherwise (i.e. U or V isn't in the graph)
    public int changeEdge(T u, T v, int w);


    // returns whether or not the nodes U and V are connected. If U or V isn't a node, return False
    public boolean connected(T u, T v);

    // fast and easy way to reset the graph to being blank
    public void clear();
}
