public class HamiltomLoop {
    private Graph G;
    private boolean[] validate;

    public HamiltomLoop(Graph g) {
        G = g;
        validate = new boolean[g.V()];


    }

    private void dfs(int v) {
        validate[v] = true;

        for (int w : G.adj(v)) {
            if (!validate[w]) {
                dfs(w);
            }
        }
    }
}
