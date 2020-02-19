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

        for (boolean v : validate)
            System.out.print(v+" ");

        System.out.println();
    }

    private boolean dfs(int v, int parent) {
        validate[v] = true;
        pre[v] = parent;
        if (v == t) return true;
        for (int w : g.adj(v)) {
            if (!validate[w]) {
                if (dfs(w, v)) return true;
            }

        }

        return false;
    }

    public boolean isConnected() {
        g.validateVertex(t);
        return validate[t];
    }

    public Iterable<Integer> path() {
        g.validateVertex(t);
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected())
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
        Path dfs = new Path(new Graph("g.txt"), 0, 6);
        System.out.println(dfs.path());
        Path dfs1 = new Path(new Graph("g.txt"), 0, 1);
        System.out.println(dfs1.path());
        Path dfs2 = new Path(new Graph("g.txt"), 0, 5);
        System.out.println(dfs2.path());
    }

}
