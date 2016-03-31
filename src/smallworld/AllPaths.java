package smallworld;
import edu.princeton.cs.StdOut;
import java.util.ArrayList;
import java.util.Stack;
import smallworld.Graph;
import smallworld.SET;

public class AllPaths<Vertex> {

    private Stack<String> path  = new Stack<String>();   // the current path
    private SET<String> onPath  = new SET<String>();     // the set of vertices on the path
    
    /**
     * takes G Graph, String s, and String t to call the function enumerate.
     *
     * @param G The Graph that you are using to the path(s).
     * @param s Starting point for the path you wish to find.
     * @param t Ending point for your path.
     */
    public AllPaths(Graph G, String s, String t) {
        enumerate(G, s, t);
    }
    
    /**
     * use DFS
     * 
     * adds the start to path and onPath, then finds the adjacent vertex.  It
     * then checks to see if that vertex is the right one, if not it will repeat
     * that until it either finds the end vertex, or runs out of vertexes to
     * go to.  If it does finds the right vertex, it will print that, then
     * deletes that path and moves on.
     * 
     * @param G uses the graph to find the best path.
     * @param v starting point for the path.
     * @param t ending for the path.
     */
    private void enumerate(Graph G, String v, String t) {

        // add node v to current path from s
        path.push(v);
        onPath.add(v);
//        int distance = 0;
//        int number = 0;
//        ArrayList<String> allpaths = new ArrayList<String>();

        // found path from s to t - currently prints in reverse order because of stack
        if (v.equals(t)) {
            StdOut.println(path.size());
//            number = number + 1;
        }

        // consider all neighbors that would continue path with repeating a node
        else {
            for (String w : G.adjacentTo(v)) {
                if (!onPath.contains(w)) {
                    enumerate(G, w, t);
                } // if
            } // for
        } // else

        // done exploring from v, so remove from path
        path.pop();
        onPath.delete(v);
    } // enumerate

    /**
     * Makes a Graph, then uses AllPaths to finds all of the path.
     * 
     * @param args no argument for this main method
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
        new AllPaths(G, "A", "G");
        StdOut.println();
        new AllPaths(G, "B", "F");
    }

}
