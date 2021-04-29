import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FindBridgesRecursion {
    private Graph G;
    private boolean[] validate;
    private int[] order;
    private int[] low;
    private int cnt;
    private List<Pair<Integer, Integer>> res;
    public FindBridgesRecursion(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        order = new int[g.V()];
        low = new int[g.V()];
        res = new ArrayList<>();
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
        for (int w : G.adj(v)) {
            if (!validate[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > order[v]) {
                    res.add(new Pair<>(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public List<Pair<Integer, Integer>> result() {
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        FindBridgesRecursion fb = new FindBridgesRecursion(g);
        System.out.println("Bridges in g : " + fb.result());

        Graph g2 = new Graph("g2.txt");
        FindBridgesRecursion fb2 = new FindBridgesRecursion(g2);
        System.out.println("Bridges in g2 : " + fb2.result());

        Graph g3 = new Graph("g3.txt");
        FindBridgesRecursion fb3 = new FindBridgesRecursion(g3);
        System.out.println("Bridges in g3 : " + fb3.result());

    }
}
