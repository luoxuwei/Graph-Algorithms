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
            int minDis = Integer.MAX_VALUE;
            for (int v = 0; v < g.V(); v++) {
                if (!visited[v] && dis[v] < minDis) {
                    minDis = dis[v];
                    cur = v;
                }
            }

            if (cur == -1) break;
            visited[cur] = true;
            for (int w:G.adj(cur)) {
                if (!visited[w] && (dis[cur] + G.getWeight(cur, w)) < dis[w]) {
                    dis[w] = dis[cur] + G.getWeight(cur, w);
                }
            }
        }
    }

    public int disTo(int w) {
        G.validateVertex(w);
        return dis[w];
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);

        for (int v = 1; v < g.V(); v++) {
            System.out.print(dijkstra.disTo(v) + " ");
        }
    }
}
