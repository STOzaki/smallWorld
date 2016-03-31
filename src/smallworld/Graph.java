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
     * Creates st and then checks to see if there is another line, and if there
     * is then if looks at it and splits by delimiter then stores it onto names.
     * After it stores all of the edges associated with the first name.
     * 
     * @param in used to see if there is another line.
     * @param delimiter is used to separate each word in line and store it in
     * name.
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
     * 
     * @return the size of the st
     */
    public int V() {
        return st.size();
    }


    /**
     * Number of edges.
     * 
     * @return how many edges.
     */
    public int E() {
        return E;
    }


    /**
     * throw an exception if v is not a vertex.
     * 
     * @param v checks to see if the String v is a vertex.
     */
    private void validateVertex(String v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not a vertex");
    }

   /**
     * 
     */
    /**
     * Degree of this vertex.
     * 
     * @param v grabs the edges from st and checks to see how many edges are
     * connected to it.
     * @return returns the number of edges or degree for v.
     */
    public int degree(String v) {
        validateVertex(v);
        return st.get(v).size();
    }

    
    
    /**
     * Add edge v-w to this graph (if it is not already an edge).
     * 
     * @param v check to see if there is a vertex named v, and if not it will
     * add it.  After it adds w as one of v's edge(s).
     * @param w check to see if there is a vertex named w, and if not it will
     * add it.  After it adds v as one of w's edge(s).
     */
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) E++;
        st.get(v).add(w);
        st.get(w).add(v);
    }


    
    /**
     * Add vertex v to this graph (if it is not already a vertex).
     * 
     * @param v If v is not a vertex, then it adds v to st with new SET.
     */
    public void addVertex(String v) {
        if (!hasVertex(v)) st.put(v, new SET<String>());
    }


    
    /**
     * Return the set of vertices as an Iterable.
     * 
     * @return returns all of the vertices.
     */
    public Iterable<String> vertices() {
        return st.keys();
    }

    
    
    /**
     * Return the set of neighbors of vertex v as an Iterable.
     * 
     * @param v grabs all of the neighbors of v from st.
     * @return gives you the vertex of v in st.
     */
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return st.get(v);
    }

   
    
    /**
     * Is v a vertex in this graph?
     * 
     * @param v checks to see if the vector v exists in st.
     * @return returns true if v exits in st, else false.
     */
    public boolean hasVertex(String v) {
        return st.contains(v);
    }

    
    
    /**
     * Is v-w an edge in this graph?
     * 
     * @param v Grabs the vector v from st.
     * @param w is used to check to see if the edge w exists in vector v.
     * @return returns true if the edge w exists in the vertex v.
     */
    public boolean hasEdge(String v, String w) {
        validateVertex(v);
        validateVertex(w);
        return st.get(v).contains(w);
    }

    
    
    /**
     * 
     * @return Return a string representation of the graph.
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
     * Tests out this program by building its own G Graph and testing it.
     * 
     * It goes through every v string and states what is the name of the vertex
     * and names all of the edges within each vertex.
     * 
     * @param args There are no arguments in this one.
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
