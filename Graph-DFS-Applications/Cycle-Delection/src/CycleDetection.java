import java.util.*;

public class CycleDetection {
    private boolean[] validate;
    private Graph g;
    private int s;
    private boolean hasCycle;

    public CycleDetection(Graph g) {
        validate = new boolean[g.V()];
        this.g =g;
        this.s = s;

        for (int v = 0; v < g.V(); v++)
            dfs(v, v);
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w, v);
            else  if (w != v) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        CycleDetection dfs = new CycleDetection(new Graph("g.txt"));
        System.out.println(dfs.hasCycle());
    }

}
