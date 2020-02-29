import java.util.Arrays;

public class BellmenFordAlgorithm {
    Graph G;
    int[] dis;
    int[] pre;
    boolean hasNegCycle;
    public BellmenFordAlgorithm(Graph g, int s) {
        G = g;
        dis = new int[g.V()];
        pre = new int[g.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[s] = 0;
        //对每个顶点进行v-1轮松弛操作
        for(int pass = 0; pass < G.V() - 1; pass++) {

            //对每个顶点进行一轮松弛操作
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                    }

                }
            }
        }

        //进行了V-1次松弛操作后，如果再进行一轮松弛操作，还能得到更小的最短路就表示存在负权环
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                    hasNegCycle = true;
                }

            }
        }

    }

    public boolean hasNegCycle() {
        return hasNegCycle;
    }

    public boolean isConnected(int w) {
        G.validateVertex(w);
        return dis[w] != Integer.MAX_VALUE;
    }

    public int disTo(int w) {
        G.validateVertex(w);
        if (hasNegCycle) throw new IllegalArgumentException("");
        return dis[w];
    }


}
