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
    }
}
