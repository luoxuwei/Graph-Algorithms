import java.util.ArrayList;
import java.util.Collections;

public class Path {
    private int[] pre;
    private boolean[] validate;
    private Graph g;
    private int s;
    private int t;

    public Path(Graph g, int s, int t) {
        validate = new boolean[g.V()];
        this.g =g;
        this.s = s;
        this.t = t;
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

    public boolean isConnected(int t) {
        g.validateVertex(t);
        return validate[t];
    }

    public Iterable<Integer> path(int t) {
        g.validateVertex(t);
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t))
            return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }

        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Path dfs = new Path(new Graph("g.txt"), 0);
        System.out.println(dfs.path(6));
    }

}
