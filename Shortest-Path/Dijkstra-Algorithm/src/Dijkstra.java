import java.util.Arrays;

public class Dijkstra {
    Graph G;
    int[] dis;
    int S;
    boolean[] visited;

    public Dijkstra(Graph g, int s) {
        G = g;
        S = s;
        dis = new int[g.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        visited = new boolean[g.V()];
        dis[s] = 0;
        while (true) {
            int cur = -1;
            int minDis = Integer.MIN_VALUE;
            for (int v = 0; v < g.V(); v++) {
                if (!visited[v] && dis[v] < minDis) {
                    minDis = dis[v];
                    cur = v;
                }
            }

            if (cur == -1) break;
            
        }
    }
}
