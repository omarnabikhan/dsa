package Tests;

import DataStructures.Graph;
import DataStructures.GraphSoln;
import org.junit.Test;
import org.junit.Assert;

public class GraphTest {

    @Test
    public void sanityTest() {
        Graph<Integer> g = new GraphSoln<>(false);
        Assert.assertEquals(0, g.numNodes());
        Assert.assertEquals(0, g.numEdges());

        g.addNode(0);

        for (int i = 1; i < 10; i += 1) {
            g.addNode(i);
            g.addEdge(i - 1, i);
        }

        // forms a linked list: 0 -> 1 -> 2 -> 3 ... 8 -> 9

        Assert.assertEquals(10, g.numNodes());
        Assert.assertEquals(9, g.numEdges());


        for (int x : g.neighbors(0)) {
            Assert.assertEquals(1, x);
        }
        for (int x : g.neighbors(9)) {
            Assert.assertEquals(8, x);
        }

        for (int i = 1; i < 9; i += 1) {
            int correctVal = i - 1;
            for (int x : g.neighbors(i)) {
                Assert.assertEquals(correctVal, x);
                correctVal += 2;
            }
        }

        g.delNode(5);
        Assert.assertEquals(9, g.numNodes());
        Assert.assertEquals(7, g.numEdges());
    }

    @Test
    public void testAddsDels() {
        Graph<Integer> g = new GraphSoln<>(false);
        g.addNode(0);

        for (int i = 1; i < 100; i += 1) {
            g.addNode(i);

            for (Integer neighbor : g.getNodes()) {
                g.addEdge(i, neighbor);
            }
        }

        // g should be Strongly connected now

        Assert.assertEquals(100, g.numNodes());
        Assert.assertEquals(100 * 99 / 2, g.numEdges());

        g.clear();

        Assert.assertEquals(0, g.numNodes());
        Assert.assertEquals(0, g.numEdges());

        g.addNode(0);

        for (int i = 1; i < 500; i += 1) {
            g.addNode(i);
            g.addEdge(0, i); // without the node 0, the graph would be disconnected
        }

        g.delNode(0);

        Assert.assertEquals(0, g.numEdges());
        Assert.assertEquals(499, g.numNodes());
    }
}
