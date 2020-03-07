import java.util.List;

public class MaxFlow {
    Graph network;
    Graph rG;
    int s;
    int t;
    int maxFlow;

    public MaxFlow(Graph g, int s, int t) {
        network = g;
        this.s = s;
        this.t = t;

        if (s == t)
            throw new IllegalArgumentException("");

        rG = new Graph(g.V(), true);
        //构造残量图
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                rG.addEdge(v, w, g.getWeight(v, w));
                rG.addEdge(w, v, 0);//添加残量图的反向边
            }
        }

        //求最大流的算法架构，找到增广路径，算出路径上能添加的最大流量，更新残量图边的权值
        while (true) {
            //找到增广路径
            List<Integer> path = getAugmentingPath();
            if (path.size() == 0) break;

            int f = Integer.MAX_VALUE;
            //算出路径上能添加的最大流量
            for (int i = 1; i < path.size(); i++) {
                if (rG.getWeight(i - 1, i) < f)
                    f = rG.getWeight(i - 1, i);
            }
            maxFlow += f;
            //更新残量图中边的权值
            for (int i = 1; i < path.size(); i++) {
                int v = i - 1;
                int w = i;
                if (network.hasEdge(v, w)) {//表明是原图中的正向边
                    rG.setWeight(v, w, rG.getWeight(v, w) - f);
                    rG.setWeight(w, v, rG.getWeight(w, v) + f);
                } else {//如果是残量图中的反向边，从反向边逆流的流量抵消掉了正向边的流量，所以正向边的流量变小容量变大，相应的方向边的容量变小
                    rG.setWeight(v, w, rG.getWeight(v, w) - f);
                    rG.setWeight(w, v, rG.getWeight(w, v) + f);
                }
            }
        }


    }

    public List<Integer> getAugmentingPath() {

    }
}
