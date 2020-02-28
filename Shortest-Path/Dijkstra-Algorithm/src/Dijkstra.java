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
    }
}
