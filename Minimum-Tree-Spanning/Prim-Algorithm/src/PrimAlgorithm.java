import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    Graph G;
    boolean[] visited;
    List<WeightedEdge> res;
    CC cc;

    public PrimAlgorithm(Graph g) {
        G = g;
        res = new LinkedList<>();
        cc = new CC(g);
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>();
        visited = new boolean[G.V()];
        visited[0] = true;
        for (int v : G.adj(0)) {
            queue.add(new WeightedEdge(0, v, G.getWeight(0, v)));
        }

        WeightedEdge min = null;

        while (!queue.isEmpty()) {
            min = queue.poll();
            if (visited[min.getW()]) {
                continue;
            }
            res.add(min);
            visited[min.getW()] = true;
            for (int w : G.adj(min.getW())) {
                queue.add(new WeightedEdge(min.getW(), w, G.getWeight(min.getW(), w)));
            }

        }
    }

    public List<WeightedEdge> result() {
        return res;
    }

    public static void main(String[] args) {
        PrimAlgorithm primAlgorithm = new PrimAlgorithm(new Graph("g.txt"));
        System.out.println(primAlgorithm.result());
    }
}
