import java.util.Arrays;

public class FloyedAlgorithm {
    Graph G;
    int[][] dis;

    FloyedAlgorithm(Graph g) {
        G = g;
        dis = new int[G.V()][G.V()];
        for (int i = 0; i < dis.length; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);

        for(int t = 0; t < G.V(); t++) {
            for (int v = 0; v < G.V(); v++) {
                for (int w = 0; w < G.V(); w++) {
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w]) {
                        dis[v][w] = dis[v][t] + dis[t][w];
                    }
                }
            }
        }
    }
}
