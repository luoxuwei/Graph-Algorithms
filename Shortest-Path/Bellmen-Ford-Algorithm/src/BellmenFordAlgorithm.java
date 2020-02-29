import java.util.Arrays;

public class BellmenFordAlgorithm {
    Graph G;
    int[] dis;
    int[] pre;
    boolean hasNegCycle;
    public BellmenFordAlgorithm(Graph g) {
        G = g;
        dis = new int[g.V()];
        pre = new int[g.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);
    }
}
