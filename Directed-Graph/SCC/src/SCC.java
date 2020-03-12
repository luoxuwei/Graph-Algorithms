import java.util.LinkedList;
import java.util.List;

public class SCC {
    Graph G;
    Graph rG;

    public SCC(Graph g) {
        G = g;
        rG = new Graph(G.V(), true);

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v < w) {
                    rG.addEdge(w, v);
                }
            }
        }
        List<Integer> post = rG.post();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int p : post) {
            queue.addFirst(p);
        }
    }
}
