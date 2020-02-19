import java.util.*;

public class CycleDetection {
    private int[] pre;
    private boolean[] validate;
    private Graph g;
    private int s;

    public CycleDetection(Graph g, int s) {
        validate = new boolean[g.V()];
        this.g =g;
        this.s = s;
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
        CycleDetection dfs = new CycleDetection(new Graph("g.txt"), 0);
    }

}
