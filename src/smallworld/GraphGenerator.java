package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac GraphGenerator.java
 *  Execution:    java GraphGenerator filename delimiter
 *  Dependencies: Graph.java In.java StdOut.java
 *
 ******************************************************************************/
/**
 *  The <tt>GraphGenerator</tt> class that builds Graph using a text file.
 *  It supports the following operations: read which reads a text file and
 * divides each of the lines up and then divides each of the names up by the
 * delimiter which is the main argument.
 *  <p>
 *  For the source code, see <a href="http://introcs.cs.princeton.edu/java/45graph/GraphGenerator.java.html">Section 4.5</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 */
public class GraphGenerator {


/**
 * reads the text you give them.
 * 
 * makes a Graph then as long as there is another line, it will read each line
 * and split each line up by the delimiter.  Then adds each name to the 
 * appropriate movie starting with the first.
 * @param in as long as there is something in the text file, it will read it.
 * @param delimiter this is used to divide the each line.
 * @return G which has all of the actors involved in each movie.
 */
    public static Graph read(In in, String delimiter) {
        Graph G = new Graph();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            String movie = names[0];
            for (int i = 1; i < names.length; i++) {
                G.addEdge(movie, names[i]);
            }
        }
        return G;
    }
    
    /**
     * uses a text file and delimiter from the main arguments to generate a 
     * G Graph using GraphGenerator, then finds all paths.
     * 
     * @param args the file name and the delimiter.
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        In in = new In(filename);
        Graph G = GraphGenerator.read(in, delimiter);
        StdOut.println(G);
        
        AllPaths a = new AllPaths(G);        
    }

}
