import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SingleSourcePath {
    private List<Integer> order;
    private boolean[] validate;
    private Graph g;
    public SingleSourcePath(Graph g) {
        order = new LinkedList<>();
        validate = new boolean[g.V()];
        this.g =g;
    }

    private void dfs(int v) {
        order.add(v);
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w);
        }
    }

    public static void main(String[] args) {
        SingleSourcePath dfs = new SingleSourcePath(new Graph("g.txt"));
        System.out.println(dfs.order);
    }

}
