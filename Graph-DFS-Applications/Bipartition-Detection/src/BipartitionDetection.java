import java.util.*;

public class BipartitionDetection {
    private boolean[] validate;
    private Graph g;
    private int[] colors;//0 or 1
    private boolean isBipartition = true;
    public BipartitionDetection(Graph g) {
        validate = new boolean[g.V()];
        this.g =g;
        colors = new int[g.V()];
        for (int i = 0; i < g.V(); i++)
            colors[i] = -1;

        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                if (!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        validate[v] = true;
        colors[v] = color;
        for (int w : g.adj(v)) {
            if (!validate[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }

    public static void main(String[] args) {

    }

}
