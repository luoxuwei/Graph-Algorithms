import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphDFS {
    private List<Integer> order;
    private boolean[] validate;
    private Graph g;
    public GraphDFS(Graph g) {
        order = new LinkedList<>();
        validate = new boolean[g.V()];
        this.g =g;
        dfs(0);
    }

    private void dfs(int v) {
        order.add(v);
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w);
        }
    }




}
