import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal {

    Graph G;
    CC cc;
    UF uf;
    List<WeightedEdge> res;

    public Kruskal(Graph g) {
        G = g;
        cc = new CC(g);
        uf = new UF(g.V());

        List<WeightedEdge> edges = new LinkedList<>();

        for (int v = 0; v < g.V(); v++) {
            for (int w : G.adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
                }
            }
        }

        Collections.sort(edges);
    }
}
