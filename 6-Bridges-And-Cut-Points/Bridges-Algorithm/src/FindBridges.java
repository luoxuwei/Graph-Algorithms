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
        int cur = v;
        int parent = v;
        int chil = v ;
        LinkedList<Integer> vlist;
        while (cur >= 0) {
            if (!validate[cur]) {
                if (!vstack.isEmpty()) {
                    parent = vstack.peek();
                }
                vstack.push(cur);
                order[cur] = cnt;
                low[cur] = cnt;
                cnt++;
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
            cur = -1;
            while (!wstack.isEmpty()) {
                vlist = wstack.peek();
                if (vlist.isEmpty()) {
                    wstack.pop();
                    chil = vstack.pop();
                    if (!vstack.isEmpty()) {
                        parent = vstack.peek();
                        low[parent] = Math.min(low[parent], low[chil]);
                        if (low[chil] > order[parent]) {
                            res.add(new Pair<>(parent, chil));
                        }
                    }
                } else {
                    cur = vlist.removeFirst();
                    break;
                }

            }

        }
    }

    public List<Pair<Integer, Integer>> result() {
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println("Bridges in g : " + fb.result());

        Graph g2 = new Graph("g2.txt");
        FindBridges fb2 = new FindBridges(g2);
        System.out.println("Bridges in g2 : " + fb2.result());

        Graph g3 = new Graph("g3.txt");
        FindBridges fb3 = new FindBridges(g3);
        System.out.println("Bridges in g3 : " + fb3.result());

    }
}
