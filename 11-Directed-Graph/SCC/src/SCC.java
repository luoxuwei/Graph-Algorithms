import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SCC {
    Graph G;
    Graph rG;
    boolean[] visited;
    int cccount;
    int[] ccids;

    public SCC(Graph g) {
        G = g;

        if (!g.isDirected())
            throw new IllegalArgumentException("");

        rG = new Graph(G.V(), true);

        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                rG.addEdge(w, v);
            }
        }
        ccids = new int[G.V()];
        List<Integer> post = rG.post();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int p : post) {
            queue.addFirst(p);
        }
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            if (!visited[cur]) {
                dfs(cur, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        ccids[v] = ccid;
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, ccid);
            }
        }
    }

    public int cccount() {
        return cccount;
    }

    public List<List<Integer>> componts() {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < cccount; i++) {
            res.add(new ArrayList<>());
        }
        for (int v = 0; v < G.V(); v++) {
            res.get(ccids[v]).add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        SCC scc = new SCC(g);
        System.out.println(scc.cccount());

        List<List<Integer>> ccomponts = scc.componts();
        for (int i = 0; i < ccomponts.size(); i++) {
            System.out.print("ccid " + i + " :");
            for (int v : ccomponts.get(i)) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
