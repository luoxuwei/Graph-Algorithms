import java.util.LinkedList;

//leetcode 785
public class Solution0785 {
    private int[][] G;
    private int[] colors; // 0 or 1

    public boolean isBipartite(int[][] graph) {
        G = graph;
        colors = new int[graph.length];
        for (int i = 0; i < G.length; i++) {
            colors[i] = -1;
        }

        for (int v = 0; v < G.length; v++) {
            if (colors[v] == -1) {
                if (!isBipartite(v)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isBipartite(int v) {
        colors[v] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(v);
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            for (int w : G[cur]) {
                if (colors[w] == -1) {
                    queue.addLast(w);
                    colors[w] = 1-colors[cur];
                } else if (colors[w] == colors[cur]) {
                    return false;
                }
            }
        }
        return true;
    }


}
