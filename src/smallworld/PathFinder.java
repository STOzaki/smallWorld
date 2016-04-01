package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;
import java.util.Stack;

/******************************************************************************
 *  Compilation:  javac PathFinder.java
 *  Execution:    java Pathfinder input.txt delimiter source
 *  Dependencies: Queue.java Stack.java Graph.java
 *  
 *  Runs breadth first search algorithm from source s on a graph G.
 *  After preprocessing the graph, can process shortest path queries
 *  from s to any vertex t.
 *
 *  % java PathFinder routes.txt " " JFK
 *  LAX
 *     JFK
 *     ORD
 *     PHX
 *     LAX
 *  distance 3
 *  MCO
 *     JFK
 *     MCO
 *  distance 1
 *  DFW
 *     JFK
 *     ORD
 *     DFW
 *  distance 2
 *
 ******************************************************************************/

public class PathFinder {

    // prev[v] = previous vertex on shortest path from s to v
    // dist[v] = length of shortest path from s to v
    private ST<String, String>  prev = new ST<String, String>();
    private ST<String, Integer> dist = new ST<String, Integer>();

    // 
    /**
     * run BFS in graph G from given source vertex s
     * 
     * @param G Looks through q and adds the distance and location of each vertex
     * closest to s.
     * @param s is the location you wish to check.
     */
    public PathFinder(Graph G, String s) {

        // put source on the queue
        Queue<String> q = new Queue<String>();
        q.enqueue(s);
        dist.put(s, 0);
        
        // repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while (!q.isEmpty()) {
            String v = q.dequeue();
            for (String w : G.adjacentTo(v)) {
                if (!dist.contains(w)) {
                    q.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }


    /**
     * is v reachable from the source s?
     * 
     * @param v Checks to see if the vertex v exists in dist.
     * @return true if v exists in dist, else false.
     */
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }


    /**
     * return the length of the shortest path from v to s.
     * 
     * if there is a path to v then it will get that and then add it to the 
     * dist.
     * 
     * @param v checks if there is a path to v.
     * @return grabs the path towards v and stores it in dist.
     */
    public int distanceTo(String v) {
        if (!hasPathTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }


    
    
    /**
     * return the shortest path from v to s as an Iterable
     * 
     * If there is a v and it is not already in dist, then add it as the previous
     * path, and then return the path
     * 
     * @param v vertex you are adding
     * @return returns the path that v has been added to.
     */
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }
    

/**
 * Tests the program by using a text file and running the class.
 * 
 * It takes the file and then runs the GraphGenerator to make it organized.
 * Then applies this class.  After words it tells the path and distance to 
 * "JFK" and "MCO" using the method under the main called report.
 * 
 * @param args the file name, what divides each word, and the place you wish to
 * go.
 */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        
        System.out.println( filename );
        System.out.println( ">" + delimiter + "<" );
        In in = new In(filename); 
        Graph G = GraphGenerator.read(in, delimiter);
        String s = args[2];
        PathFinder pf = new PathFinder(G, s);
        
        
        pf.report( "JFK" );
        pf.report( "MCO" );
        
    } // main( String [] )

    /**
     * Takes and prints out the path and then the distance from airport.
     * @param airport the ending point for the path.
     */
    private void report( String airport ) {

            for (String v : this.pathTo(airport)) {
                StdOut.println("   " + v);
            }
            StdOut.println("distance " + this.distanceTo(airport));
    } // report( PathFinder, String )
    
} // PathFinder
