/**
 * ****************************************************************************
 *  Compilation:  javac AllPaths.java
 *  Execution:    java AllPaths
 *  Depedencies:  Graph.java
 *
 *  Find all paths from s to t.
 *
 *  % java AllPaths
 *  A: B C
 *  B: A F
 *  C: A D F
 *  D: C E F G
 *  E: D G
 *  F: B C D
 *  G: D E
 *
 *  [A, B, F, C, D, E, G]
 *  [A, B, F, C, D, G]
 *  [A, B, F, D, E, G]
 *  [A, B, F, D, G]
 *  [A, C, D, E, G]
 *  [A, C, D, G]
 *  [A, C, F, D, E, G]
 *  [A, C, F, D, G]
 *
 *  [B, A, C, D, F]
 *  [B, A, C, F]
 *  [B, F]
 *
 *  Remarks
 *  --------
 *   -  Currently prints in reverse order due to stack toString()
 *
 *****************************************************************************
 */
/**
 *  The <tt>AllPaths</tt> class finds every path possible from two points. It
 * supports the following operations: enumerate, which finds every path that
 * uses G Graph, String s, and String t to make every possible path from s to t.
 * <p>
 * For the source code, see
 * <a href="http://introcs.cs.princeton.edu/java/45graph/AllPaths.java.html">Section
 * 4.5</a> of
 * <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by
 * Robert Sedgewick and Kevin Wayne.
 */
package smallworld;

import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AllPaths<Vertex> {

    private Stack<String> path = new Stack<String>();   // the current path
    private SET<String> onPath = new SET<String>();     // the set of vertices on the path
//    private ArrayList<ArrayList<String>> thepaths = new ArrayList<>();
//    private String shortpath;
    private ArrayList<String> shortpath = new ArrayList<>();
    private ArrayList<String> rightpath = new ArrayList<>();
//    private ArrayList<String> oldpath = new ArrayList<>();

    /**
     * Takes the parameters Graph G, String s, and String t and then calls the
     * method enumerate.
     *
     * @param G The graph that has all of the vertexes and their edges.
     * @param s The start of the path.
     * @param t The end of the path.
     */
    public AllPaths(Graph G) {
        System.out.println("Now that you can see all of the places that you"
                + " can go,");
        System.out.println("Where are you?");
        Scanner begin = new Scanner(System.in);
        String start = begin.nextLine();
        System.out.println("  And, where would you like to go?");
        String end = begin.nextLine();
        System.out.println();
        try{
        enumerate(G, start, end);
        } //try
        catch (IndexOutOfBoundsException e) {
    System.err.println("ERROR! I am sorry that is not correct (You may have typed"
            + " something incorrectly).");
            AllPaths allPaths = new AllPaths(G);
    }// catch
    } // AllPaths(Graph)

    /**
     * use DFS
     *
     * puts the starting point into path and onPath, then goes through every
     * vertex next to v until it either runs out of directions, or finds the end
     * (t). Then it erases and starts again.
     *
     * @param G The Graph used to find paths
     * @param v The start of the path.
     * @param t The vertex that is the destination that it is trying to get to.
     */
    private void enumerate(Graph G, String v, String t) {

        // add node v to current path from s
        path.push(v);
        onPath.add(v);
//        int number = 0;

        // found path from s to t - currently prints in reverse order because of stack
        if (v.equals(t)) {

            for (int i = 0; i < path.size(); i++) {
                rightpath.add(i, path.get(i));
            }
            
            if ((rightpath.size() < shortpath.size()) && !shortpath.isEmpty()) {
                shortpath.clear();
                for (int i = 0; i < rightpath.size(); i++) {
                    shortpath.add(i, rightpath.get(i));
                }

//                    shortpath = rightpath;
//                    rightpath.clear();
//                    System.out.println(rightpath);
//                    System.out.println(shortpath);
            }
//            thepaths.add(rightpath);
            if (shortpath.isEmpty()) {
                for (int i = 0; i < rightpath.size(); i++) {
                    shortpath.add(i, rightpath.get(i));
                }
                
                rightpath.clear();

            }

            rightpath.clear();
        } 
        // consider all neighbors that would continue path with repeating a node
        else {
            for (String w : G.adjacentTo(v)) {
                if (!onPath.contains(w)) {
                    enumerate(G, w, t);
                }
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath.delete(v);
        
        if(path.isEmpty()){
            printingPath();
        } // if

    } // enumerate(Graph,String,String)

    public void printingPath(){
        if(shortpath.size() <= 2){
            System.out.println();
            System.out.println("You went two steps.");
            System.out.println("You started at " + shortpath.get(0) + " and then"
                    + " directly got to your destination, "
            + shortpath.get(1) + ".");
        }
        else{
        System.out.println("The shortest path starts at " + shortpath.get(0));
        for(int i = 1; i < shortpath.size() - 1; i++){
            System.out.println("to " + shortpath.get(i));
        }
        System.out.println("and then you arrive at your destination, " + shortpath.get(shortpath.size() -  1));
        }
    }

    /**
     * Tests the code by creating its own and then running AllPaths.
     *
     * It creates its own Graph, then tries to find the path from "A" to "G",
     * then "B" to "F", and finds every possible route.
     *
     * @param args no arguments for this one.
     */
    public static void main(String[] args) {
        Graph G = new Graph();
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("C", "F");
        G.addEdge("B", "F");
        G.addEdge("F", "D");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        StdOut.println(G);
        new AllPaths(G);
        StdOut.println();
        new AllPaths(G);
    }

}