import java.util.Arrays;

public class FloyedAlgorithm {
    Graph G;
    int[][] dis;
    boolean hasNegCycle;

    FloyedAlgorithm(Graph g) {
        G = g;
        dis = new int[G.V()][G.V()];
        for (int i = 0; i < dis.length; i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);

        for (int v = 0; v < G.V(); v++) {
            dis[v][v] = 0;
            for (int w : G.adj(v)) {
                dis[v][w] = G.getWeight(v, w);
            }
        }

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

        for (int v = 0; v < G.V(); v++) {
            if (dis[v][v] < 0) {
                hasNegCycle = true;
            }
        }
    }

    public boolean hasNegCycle() {
        return hasNegCycle;
    }

    public int disTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        FloyedAlgorithm floyedAlgorithm = new FloyedAlgorithm(g);
        if (!floyedAlgorithm.hasNegCycle()) {
            for (int v = 0; v < g.V(); v++) {
                for (int w = 0; w < g.V(); w++) {
                    System.out.print(floyedAlgorithm.disTo(v, w) + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("has neg cycle");
        }
    }
}
