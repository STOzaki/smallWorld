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
    private ArrayList<String> shortpath = new ArrayList<>(); // shortest path
    private ArrayList<String> rightpath = new ArrayList<>(); // the path that links to desired ending
    private String start;
    private String end;
    private String length;
    private String choice;
    private Graph T;

    /**
     * Takes the parameter Graph G, then asks the user for the start and end.
     * And then it calls the method enumerate with the Graph G and the two
     * inputs from the user.
     *
     * @param G The graph that has all of the vertexes and their edges.
     */
    public AllPaths(Graph G) {
        System.out.println(G.getClass());
        prompt();

        try {
            enumerate(G, start, end);
        } //try
        catch (Exception e) {
            System.out.println("\033[31m" + "ERROR! Something went wrong. Please try again.");
            System.out.println();
            AllPaths allPaths = new AllPaths(G);
        }// catch
    } // AllPaths(Graph)
    
    public void prompt(){
        System.out.println("Now that you can see all of the places that you"
                + " can go,");
        System.out.println();
        System.out.println("Where are you?");
        Scanner answer = new Scanner(System.in);
        start = answer.nextLine();
        System.out.println();
        System.out.println("And, where would you like to go?");
        end = answer.nextLine();
        System.out.println();
        System.out.println("Do you want the shortest or longest route?");
        length = answer.nextLine();
        length.toLowerCase();
        System.out.println();
        System.out.println("Would you like for me to print out every line, "
                + "yes or no?"
                + " (Warning: there may be more paths than you expect!)");
        choice = answer.nextLine();
        choice.toLowerCase();
        System.out.println();
    }

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

        // found path from s to t - currently prints in reverse order because of stack
        if (v.equals(t)) {
            shortLong();

        } // if(v.equals(t))
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

        if (path.isEmpty()) {
            printingShort();
        } // if

    } // enumerate(Graph,String,String)

    
    /**
     * Finds the shortest route.
     * 
     * It puts the correct path's values into the rightpath.  After, it checks
     * to see if the rightpath is shorter than the current shortpath and the
     * set is not already empty.  If they are, then it will add the rightpath to
     * the shortpath.  Next it checks to see if the shortpath is
     * null.  If it is, then it will automatically add the right value. After,
     * the rightpath must lose its values for the next recursion.
     * 
     */
    public void shortLong() {
        for (int i = 0; i < path.size(); i++) {
            rightpath.add(i, path.get(i));
        }
        
        //choose whether to print all the paths or not.
        if(choice.equals("yes")) printPath();
        
        
        if ((rightpath.size() < shortpath.size()) && !shortpath.isEmpty() && "shortest".equals(length)) {
            shortpath.clear();
            for (int i = 0; i < rightpath.size(); i++) {
                shortpath.add(i, rightpath.get(i));
            }
        }
        if((rightpath.size() > shortpath.size()) && !shortpath.isEmpty() && "longest".equals(length)){
            shortpath.clear();
            for (int i = 0; i < rightpath.size(); i++) {
                shortpath.add(i, rightpath.get(i));
            }
        }
        
        if (shortpath.isEmpty()) {
            for (int i = 0; i < rightpath.size(); i++) {
                shortpath.add(i, rightpath.get(i));
            }

        }

        rightpath.clear();
    }
    
    public void printPath(){
        System.out.println("One way to get from " + start + " to " + end + ":");
        for(int i = 0; i < rightpath.size() - 1; i++){
            String spot = rightpath.get(i);
            System.out.println(spot + " to ");
        } // for
        System.out.println("And finally to your destination, " + end);
        System.out.println();
    } // printPath()

    
    /**
     * Uses the shortest path and prints out every element.
     */
    public void printingShort() {
        if (shortpath.size() <= 2) {
            System.out.println();
            System.out.println("You went two steps.");
            System.out.println("You started at " + shortpath.get(0) + " and then"
                    + " directly got to your destination, "
                    + shortpath.get(1) + ".");
        } else {
            System.out.println("The shortest path starts at " + shortpath.get(0));
            for (int i = 1; i < shortpath.size() - 1; i++) {
                System.out.println("to " + shortpath.get(i));
            }
            System.out.println("and then you arrive at your destination, " + shortpath.get(shortpath.size() - 1));
        }
        System.out.println("Would you like directions to a new place, yes or no?");
        Scanner choose = new Scanner(System.in);
        String again = choose.nextLine();
        again.toLowerCase();
//        if(again.equals("yes")){
//            
//        }
        
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
