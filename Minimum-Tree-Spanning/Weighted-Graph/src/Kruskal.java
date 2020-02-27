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
        res = new LinkedList<>();
        if (cc.cccount() > 1) {
            return;
        }
        List<WeightedEdge> edges = new LinkedList<>();

        for (int v = 0; v < g.V(); v++) {
            for (int w : G.adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
                }
            }
        }

        Collections.sort(edges);
        WeightedEdge edge;
        while (!edges.isEmpty()) {
            edge = ((LinkedList<WeightedEdge>) edges).removeFirst();
            if (!uf.isConnected(edge.getV(), edge.getW())) {
                res.add(edge);
                uf.unionElements(edge.getV(), edge.getW());
            }
        }
    }

    public List<WeightedEdge> result() {
        return res;
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal(new Graph("g.txt"));
        System.out.println(kruskal.result());
    }
}
