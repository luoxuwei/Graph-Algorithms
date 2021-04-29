import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution1192 {

    private ArrayList<LinkedList<Integer>> adj;
    private LinkedList<List<Integer>> res;
    private boolean[] validate;
    private int[] order;
    private int[] low;
    private int cnt;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        res = new LinkedList<>();
        adj = new ArrayList<LinkedList<Integer>>();
        validate = new boolean[n];
        order = new int[n];
        low = new int[n];
        for (int i=0; i<n; i++) {
            adj.add(new LinkedList<>());
        }
        for(List<Integer> edge: connections) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        for (int v = 0; v < n; v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }

        return res;
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;
        for (int w : adj.get(v)) {
            if (!validate[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > order[v]) {
                    List<Integer> list = new LinkedList<>();
                    list.add(v);
                    list.add(w);
                    res.add(list);
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

}
