public class CC {
    Graph G;
    int cccount;
    int[] ccids;

    public CC(Graph g) {
        G = g;
        cccount = 0;
        ccids = new int[g.V()];
        for (int i = 0; i < g.V(); i++)
            ccids[i] = -1;

        for (int v = 0; v < G.V(); v++) {
            if (ccids[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        ccids[v] = ccid;

        for (int w: G.adj(v)) {
            if (ccids[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int cccount() {
        return cccount;
    }


}
