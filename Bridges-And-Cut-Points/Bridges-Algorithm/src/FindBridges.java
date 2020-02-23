import javafx.util.Pair;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FindBridges {
    private Graph G;
    private boolean[] validate;
    private int[] order;
    private int[] low;
    private int cnt;
    private List<Pair<Integer, Integer>> res;
    public FindBridges(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        order = new int[g.V()];
        low = new int[g.V()];
        res = new ArrayList<>();
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        Stack<Integer> vstack = new Stack<>();
        Stack<LinkedList<Integer>> wstack = new Stack<>();
        vstack.push(v);
        validate[v] = true;
        int cur = v;
        int parent = v;
        int chil = v ;
        LinkedList<Integer> vlist;
        while (!vstack.empty()) {
            if (!validate[cur]) {
                vstack.push(cur);
                cnt++;
                order[cur] = cnt;
                low[cur] = cnt;
                validate[cur] = true;
                vlist = new LinkedList<>();
                for (int w : G.adj(v)) {
                    if (!validate[w]) {
                        vlist.addLast(w);
                    } else if (w != parent) {
                        low[cur] = Math.min(low[cur], low[w]);
                    }
                }
                wstack.push(vlist);
            }
            vlist = wstack.peek();
            if (vlist.isEmpty()) {
                wstack.pop();
                chil = vstack.pop();
                parent = vstack.peek();
                low[parent] = Math.min(low[parent], low[chil]);
                if (low[chil] > low[parent]) {
                    res.add(new Pair<>(parent, chil));
                }
                vlist = wstack.peek();
            }
            cur = vlist.removeFirst();
        }
    }
}
