import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindCutPoints {
    private Graph G;
    private boolean[] validate;
    private int[] order;
    private int[] low;
    private int cnt;
    private Set<Integer> res;
    public FindCutPoints(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        order = new int[g.V()];
        low = new int[g.V()];
        res = new HashSet<>();
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;
        int chil = 0;
        for (int w : G.adj(v)) {
            if (!validate[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (v != parent && low[w] >= order[v]) {
                    res.add(v);
                }
                if (v == parent) {
                    chil++;
                    if (chil > 1) {
                        res.add(v);
                    }
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public Iterable<Integer> result() {
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        FindCutPoints fb = new FindCutPoints(g);
        System.out.println("Bridges in g : " + fb.result());

        Graph g2 = new Graph("g2.txt");
        FindCutPoints fb2 = new FindCutPoints(g2);
        System.out.println("Bridges in g2 : " + fb2.result());

        Graph g3 = new Graph("g3.txt");
        FindCutPoints fb3 = new FindCutPoints(g3);
        System.out.println("Bridges in g3 : " + fb3.result());

    }
}
