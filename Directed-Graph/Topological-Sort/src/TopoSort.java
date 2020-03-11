import java.util.ArrayList;
import java.util.LinkedList;

public class TopoSort {
    Graph G;
    int[] outdegrees;
    ArrayList<Integer> res;
    public TopoSort(Graph g) {
        G = g;
        outdegrees = new int[G.V()];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            outdegrees[v] = G.outdegree(v);
            if (outdegrees[v] == 0) {
                queue.addLast(v);
            }
        }

    }
}
