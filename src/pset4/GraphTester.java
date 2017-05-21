package pset4;

/**
 * Created by amukherjee on 5/10/17.
 */
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class GraphTester {
    // tests for method "addEdge" in class "Graph"
    @Test public void tae0() {
        Graph g = new Graph();
        g.addEdge(0, 1);
        assertEquals(g.toString(), "nodes=[0, 1]; edges={0=[1]}");
    }
    // your tests for method "addEdge" in class "Graph" go here
// you must provide at least 4 test methods;
// each test method must have at least 1 invocation of addEdge;
// each test method must have at least 1 test assertion;
// your test methods must provide full statement coverage of your
// implementation of addEdge and any helper methods
// no test method directly invokes any method that is not
// declared in the Graph class as given in this homework
// tests for method "unreachable" in class "Graph"

    /**
     * Check with Null values; NumberFormatException must be thrown
     */
    @Test(expected = NumberFormatException.class)
    public void testAddEdge1(){
        Graph g = new Graph();
        Integer a = new Integer(null);
        g.addEdge(a, a);
    }

    /**
     * Check for double linked nodes
     */

    @Test public void testAddEdge2(){
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        assertEquals(g.toString(), "nodes=[0, 1]; edges={0=[1], 1=[0]}");
    }

    /**
     * Self linked nodes
     */

    @Test public void testAddEdge3(){
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(0, 1);
        assertEquals(g.toString(), "nodes=[0, 1]; edges={0=[1, 1]}");
    }

    /**
     * Node with Negative value
     */

    @Test public void testAddEdge4(){
        Graph g = new Graph();
        g.addEdge(0, -1);
        assertEquals(g.toString(), "nodes=[0, -1]; edges={0=[-1]}");
    }

    @Test public void tr0() {
        Graph g = new Graph();
        g.addNode(0);
        Set<Integer> nodes = new HashSet<Integer>();
        nodes.add(0);
        assertTrue(g.unreachable(new HashSet<Integer>(), nodes));
    }
// your tests for method "unreachable" in class "Graph" go here
// you must provide at least 6 test methods;
// each test method must have at least 1 invocation of unreachable;
// each test method must have at least 1 test assertion;
// at least 2 test methods must have at least 1 invocation of addEdge;
// your test methods must provide full statement coverage of your
// implementation of unreachable and any helper methods
// no test method directly invokes any method that is not
// declared in the Graph class as given in this homework

    /**
     * Test Unconnected nodes
     */
    @Test
    public void testUnreachable1(){
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        source.add(0);
        target.add(1);
        assertTrue(g.unreachable(source, target));
    }

    /**
     * Test null source and null target; IllegalArgumentException must be thrown
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUnreachable2(){
        Graph g = new Graph();
        g.unreachable(null, null);
    }

    /**
     * Test multi-connected nodes
     */
    @Test
    public void testUnreachable3(){
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        g.addNode(0);
        g.addNode(1);
        g.addEdge(0,1);
        g.addEdge(0,1);
        source.add(0);
        target.add(1);
        assertFalse(g.unreachable(source, target));

    }

    /**
     * Test self reachable with self-edge
     */
    @Test
    public void testUnreachable4(){
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        g.addNode(0);
        g.addEdge(0,0);
        source.add(0);
        target.add(0);
        assertFalse(g.unreachable(source, target));
    }

    /**
     * Test self reachable without connection
     */

    @Test
    public void testUnreachable5(){
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        g.addNode(0);
        source.add(0);
        target.add(0);
        assertFalse(g.unreachable(source, target));
    }

    /**
     * Test happy path of 2 connected nodes
     */
    @Test
    public void testUnreachable6(){
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        g.addNode(0);
        g.addNode(1);
        g.addEdge(0,1);
        source.add(0);
        target.add(1);
        assertFalse(g.unreachable(source, target));

    }

    /**
     * Test one node with an edge but not to target node
     */
    @Test
    public void testUnreachable7(){
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        g.addNode(0);
        g.addNode(1);
        g.addEdge(0,1);
        g.addEdge(0,2);
        source.add(1);
        target.add(2);
        assertTrue(g.unreachable(source, target));

    }

}