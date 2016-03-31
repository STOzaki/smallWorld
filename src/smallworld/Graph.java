package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;

/******************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph
 *  Dependencies: ST.java SET.java In.java StdOut.java
 *  
 *  Undirected graph data type implemented using a symbol table
 *  whose keys are vertices (String) and whose values are sets
 *  of neighbors (SET of Strings).
 *
 *  Remarks
 *  -------
 *   - Parallel edges are not allowed
 *   - Self-loop are allowed
 *   - Adjacency lists store many different copies of the same
 *     String. You can use less memory by interning the strings.
 *
 ******************************************************************************/

/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  with string names.
 *  It supports the following operations: add an edge, add a vertex,
 *  get all of the vertices, iterate over all of the neighbors adjacent
 *  to a vertex, is there a vertex, is there an edge between two vertices.
 *  Self-loops are permitted; parallel edges are discarded.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/45graph">Section 4.5</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Graph {

    // symbol table: key = string vertex, value = set of neighboring vertices
    private ST<String, SET<String>> st;

    // number of edges
    private int E;

   /**
     * Create an empty graph with no vertices or edges.
     */
    public Graph() {
        st = new ST<String, SET<String>>();
    }

    /**
     * Create an graph from given input stream using given delimiter.
     * 
     * makes a new ST and SET called st.  As long as there is another line, it
     * will grab that line and split it by the delimiter.  After putting all of
     * the names in the one array, it will grab each one and adds the new edge
     * for the first name.
     * 
     * @param in as long as there is something in the text file, it will read it.
     * @param delimiter this is used to divide the each line.
     */
    public Graph(In in, String delimiter) {
        st = new ST<String, SET<String>>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            for (int i = 1; i < names.length; i++) {
                addEdge(names[0], names[i]);
            }
        }
    }

   /**
     * Number of vertices.
     */
    public int V() {
        return st.size();
    }

   /**
     * Number of edges.
     */
    public int E() {
        return E;
    }

    // 
    /**
     * throw an exception if v is not a vertex
     * @param v if v does not have a vertex, then it will throw saying that 
     * "v is not a vertex".
     */
    private void validateVertex(String v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not a vertex");
    }
    
    
    /**
     * Degree of this vertex.
     * @param v is used to find that word.
     * @return the word in the st, then finds the size.
     */
    public int degree(String v) {
        validateVertex(v);
        return st.get(v).size();
    }
    
    
    /**
     * Add edge v-w to this graph (if it is not already an edge).
     * 
     * @param v if it does not have this string, then it will add v.  And, it
     * gets the v in st and then adds w as a neighbor to v.
     * 
     * @param w  if it does not have w string, then it will add w.  And, it will
     * grab w from the st and add v as a neighbor to w.
     */
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) E++;
        st.get(v).add(w);
        st.get(w).add(v);
    }

    
    /**
     * Add vertex v to this graph (if it is not already a vertex)
     * 
     * @param v if it does not already have v, then put v as the key
     * and with a new SET<String> as the value in the st.
     */
    public void addVertex(String v) {
        if (!hasVertex(v)) st.put(v, new SET<String>());
    }


   /**
     * Return the set of vertices as an Iterable.
     */
    public Iterable<String> vertices() {
        return st.keys();
    }

    
    /**
     * Return the set of neighbors of vertex v as an Iterable.
     * 
     * @param v finds all of the neighbor(s) of v.
     * @return the neighbor(s) of v.
     */
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return st.get(v);
    }

    
    /**
     * Is v a vertex in this graph?
     * 
     * @param v sees if v exists anywhere in st
     * @return true if v is in st, else false will be returned.
     */
    public boolean hasVertex(String v) {
        return st.contains(v);
    }

    
    /**
     * Is v-w an edge in this graph?
     * 
     * @param v checks to sees specifically v's neighbor.
     * @param w to see if w exists as v's neighbor.
     * @return returns a true or false statement depending on whether w exists
     * as one of v's neighbor.
     */
    public boolean hasEdge(String v, String w) {
        validateVertex(v);
        validateVertex(w);
        return st.get(v).contains(w);
    }

   /**
     * Return a string representation of the graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String v : st) {
            s.append(v + ": ");
            for (String w : st.get(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    /**
     * Creates its own G Graph and then finds all of the neighbor(s) w for each 
     * v.
     * @param args there are no need for arguments for this main class.
     */
    public static void main(String[] args) {
        Graph G = new Graph();
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        G.addVertex("H");

        // print out graph
        StdOut.println(G);

        // print out graph again by iterating over vertices and edges
        for (String v : G.vertices()) {
            StdOut.print(v + ": ");
            for (String w : G.adjacentTo(v)) {
                StdOut.print(w + " ");
            }
            StdOut.println();
        }

    }

}
