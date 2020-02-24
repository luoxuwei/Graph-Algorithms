public class HamiltomLoop {
    private Graph G;
    private boolean[] validate;

    public HamiltomLoop(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        dfs(0);
    }

    private boolean dfs(int v) {
        validate[v] = true;

        for (int w : G.adj(v)) {
            if (!validate[w]) {
                if (dfs(w)) return true;
            } else if (w == 0 && allVisited()) {
                return true;
            }
        }
        //遍历了所有连接的顶点没有找到哈密尔顿回路，回退
        validate[v] = false;
        return false;
    }

    private boolean allVisited() {
        for (boolean visited :validate) {
            if (!visited) return false;
        }

        return true;
    }
}
