import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SingleSourcePath {
    private int[] pre;
    private boolean[] validate;
    private Graph g;

    public SingleSourcePath(Graph g, int s) {
        validate = new boolean[g.V()];
        this.g =g;
        pre = new int[g.V()];
        for (int i = 0; i < g.V(); i++)
            pre[i] = -1;
        dfs(s, s);
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        pre[v] = parent;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w, v);
        }
    }

    public static void main(String[] args) {
        SingleSourcePath dfs = new SingleSourcePath(new Graph("g.txt"), 0);
    }

}
