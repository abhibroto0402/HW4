package pset4;

import java.util.*;

import static junit.framework.TestCase.fail;

/**
 * Created by amukherjee on 5/8/17.
 */
public class Graph {
    private Set<Integer> nodes; // set of nodes in the graph
    private Map<Integer, List<Integer>> edges;

    // map between a node and a list of nodes that are connected to it by outgoing edges
    // class invariant: fields "nodes" and "edges" are non-null;
    // "this" graph has no node that is not in "nodes"
    public Graph() {
        nodes = new HashSet<Integer>();
        edges = new HashMap<Integer, List<Integer>>();
    }

    public String toString() {
        return "nodes=" + nodes + "; " + "edges=" + edges;
    }

    public void addNode(int n) {
        // postcondition: adds the node "n" to this graph
        nodes.add(n);
    }

    public void addEdge(int from, int to) {
        System.out.println("Adding Edge " + from + " -> " + to);
        if (!this.nodes.contains(from)) {
            addNode(from);
        }
        if (!this.nodes.contains(to)) {
            addNode(to);
        }
        List<Integer> itemsList = edges.get(from);
        // if list does not exist create it
        if (itemsList == null) {
            itemsList = new ArrayList<Integer>();
            itemsList.add(to);
            edges.put(from, itemsList);
        } else {
            itemsList.add(to);
        }
        System.out.println("Current Graph State: " + this.toString());

        //Check Class invariant
        if (!repOk())
            fail();
    }

    public boolean repOk() {
        /**
         * Check class-invariant
         * Return false if either nodes or edges have null value
         * Return false if there's any node not in Graph.nodes
         * Return true otherwise
         */
        if (this.nodes.contains(null)) {
            System.out.println("One or more node contains Null");
            return false;
        }
        if (this.edges.keySet().contains(null)) {
            System.out.println("One or more Edges are set to Null");
            return false;
        }
        for (Integer i : edges.keySet()) {
            List<Integer> temp_list = getConnectedNodes(i);
            for (Integer j : temp_list) {
                if (!nodes.contains(j))
                    return false;
            }
        }
        return true;
    }

    public boolean unreachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources == null || targets == null) throw new IllegalArgumentException();
        // postcondition: returns true if (1) "sources" is a subset of "nodes", (2) "targets" is
        // a subset of "nodes", and (3) for each node "m" in set "targets",
        // there is no node "n" in set "sources" such that there is a
        // directed path that starts at "n" and ends at "m" in "this"; and
        // false otherwise
        // your code goes here
        // ...
        boolean flag = false;

        for (Integer i : sources) {
            if (!this.nodes.contains(i)) {
                System.out.println("Node " + i + " missing in graph");
                return flag;
            }
        }
        for (Integer i : targets) {
            if (!this.nodes.contains(i)) {
                System.out.println("Node " + i + " missing in graph");
                return flag;
            }
        }

        //Check if there's any common element between sources and
        //target as every ndoe is reachable from itself

        for (Integer i : sources) {
            if (targets.contains(i)) {
                System.out.println("Node " + i + " is reachable from itself");
                return flag;
            }
        }

        for (Integer i : sources) {
            List<Integer> temp_list = getConnectedNodes(i);
            for (Integer j : targets) {
                if (temp_list != null && temp_list.contains(j)) {
                    System.out.println("Edge exists from: " + i + " -> " + j);
                    return flag;
                }
            }
        }
        return true;
    }

    public List<Integer> getConnectedNodes(int node) {
        List<Integer> itemsList = this.edges.get(node);
        if (itemsList == null) {
            System.out.println("No directed edges to any node from this node");
            return null;
        } else return itemsList;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        Set<Integer> source = new HashSet<>();
        Set<Integer> target = new HashSet<>();
        source.add(0);
        source.add(1);
        target.add(2);
        g.addNode(2);
        boolean flag = g.unreachable(new HashSet<Integer>(), target);
        System.out.println(flag);
    }
}
