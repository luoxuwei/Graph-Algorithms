import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphBFS {
    private boolean[] validate;
    private Graph g;
    private List<Integer> order;

    public GraphBFS(Graph g) {
        this.g = g;
        validate = new boolean[g.V()];
        order = new ArrayList<>();
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v])
                bfs(v);
        }
    }

    private void bfs(int v) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(v);
        validate[v] = true;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            order.add(cur);
            for (int w:g.adj(cur)) {
                if (!validate[w]) {
                    queue.addLast(w);
                    validate[w] = true;
                }
            }
        }
    }

    public List<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        GraphBFS bfs = new GraphBFS(new Graph("g.txt"));
        System.out.println(bfs.order());
    }
}
