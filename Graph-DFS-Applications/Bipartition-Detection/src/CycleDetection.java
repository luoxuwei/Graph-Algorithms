import java.util.*;

public class CycleDetection {
    private boolean[] validate;
    private Graph g;

    public CycleDetection(Graph g) {
        validate = new boolean[g.V()];
        this.g =g;
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w, v);
        }
    }

    public static void main(String[] args) {

    }

}
