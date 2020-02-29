import java.util.Arrays;

public class FloyedAlgorithm {
    Graph G;
    int[][] dis;

    FloyedAlgorithm(Graph g) {
        G = g;
        dis = new int[G.V()][G.V()];
        for (int i = 0; i < dis.length; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        
    }
}
