public class DirectedCycleDetection {
    private boolean[] validate;
    private Graph g;
    private int s;
    private boolean hasCycle;
    private boolean[] onPath;

    public DirectedCycleDetection(Graph g) {
        if (!g.isDirected())
            throw new IllegalArgumentException("");

        validate = new boolean[g.V()];
        onPath = new boolean[g.V()];
        this.g =g;
        this.s = s;
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w, v);
            else  if (onPath[w]) {
                hasCycle = true;
            }
        }
        onPath[v] = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        DirectedCycleDetection dfs = new DirectedCycleDetection(new Graph("ug.txt", true));
        System.out.println(dfs.hasCycle());
    }

}
