import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BellmenFordAlgorithm {
    Graph G;
    int[] dis;
    int[] pre;
    boolean hasNegCycle;
    int S;
    public BellmenFordAlgorithm(Graph g, int s) {
        S = s;
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
                        pre[w] = v;
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

    public List<Integer> path(int w) {
        G.validateVertex(w);
        List<Integer> res = new LinkedList<>();
        res.add(w);
        int cur = w;
        while ((cur = pre[cur]) != S) {
            res.add(cur);
        }
        res.add(S);
        Collections.reverse(res);
        return res;
    }

    public Graph graph() {
        return G;
    }

    public static void main(String[] args) {
        BellmenFordAlgorithm bellmenFordAlgorithm = new BellmenFordAlgorithm(new Graph("g.txt"), 0);
        BellmenFordAlgorithm bellmenFordAlgorithm1 = new BellmenFordAlgorithm(new Graph("g2.txt"), 0);
        if(!bellmenFordAlgorithm.hasNegCycle()){
            for(int v = 0; v < bellmenFordAlgorithm.graph().V(); v ++)
                System.out.print(bellmenFordAlgorithm.disTo(v) + " ");
            System.out.println();
            System.out.println(bellmenFordAlgorithm.path(3));
        }
        else
            System.out.println("exist negative cycle.");

        if(!bellmenFordAlgorithm1.hasNegCycle()){
            for(int v = 0; v < bellmenFordAlgorithm1.graph().V(); v ++)
                System.out.print(bellmenFordAlgorithm1.disTo(v) + " ");
            System.out.println();
        }
        else
            System.out.println("exist negative cycle.");
    }


}
