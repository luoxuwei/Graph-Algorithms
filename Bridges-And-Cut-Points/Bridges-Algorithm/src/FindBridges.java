import java.util.Stack;

public class FindBridges {
    private Graph G;
    private boolean[] validate;

    public FindBridges(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        validate[v] = true;
        int cur = v;
        while (!stack.empty()) {
            cur = stack.pop();
            for (int w : G.adj(v)) {
                if (!validate[w]) {
                    stack.push(w);
                }
            }
        }
    }
}
