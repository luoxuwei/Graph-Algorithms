import java.util.*;

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
            if (path.size() <= 1) break;

            int f = Integer.MAX_VALUE;
            //算出路径上能添加的最大流量
            for (int i = 1; i < path.size(); i++) {
                if (rG.getWeight(path.get(i - 1), path.get(i)) < f)
                    f = rG.getWeight(path.get(i - 1), path.get(i));
            }
            maxFlow += f;
            //更新残量图中边的权值
            for (int i = 1; i < path.size(); i++) {
                int v = path.get(i - 1);
                int w = path.get(i);
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
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[network.V()];
        int[] pre = new int[rG.V()];
        Arrays.fill(pre, -1);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(s);
        pre[s] = s;
        visited[s] = true;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
//            res.add(cur);路径不能通过这样的方式记录！！和通过pre记录有差别
            if (cur == t)
                break;
            for (int w : rG.adj(cur)) {
                if (!visited[w] && rG.getWeight(cur, w) > 0) {
                    queue.addLast(w);
                    pre[w] = cur;
                    visited[w] = true;
                }
            }
        }
        if (pre[t] == -1) return res; //如果没有找到路径就直接返回
        int cur = t;
        res.add(t);
        while ((cur = pre[cur]) != s) {
            res.add(cur);
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public int result() {
        return maxFlow;
    }

    //残量图中反向边的权值，就是原图正向边流经的流量
    public int flow(int v, int w) {
        if (!network.hasEdge(v, w))
            throw new IllegalArgumentException("");
        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {
        Graph graph = new Graph("network.txt", true);
        MaxFlow maxFlow = new MaxFlow(graph, 0, 3);
        System.out.println(maxFlow.result());
        for (int v=0; v<graph.V(); v++) {
            for (int w:graph.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxFlow.flow(v, w), graph.getWeight(v, w)));
            }
        }
        Graph graph1 = new Graph("network2.txt", true);
        MaxFlow maxFlow1 = new MaxFlow(graph1, 0, 5);
        System.out.println(maxFlow1.result());
        for (int v=0; v<graph1.V(); v++) {
            for (int w:graph1.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxFlow1.flow(v, w), graph1.getWeight(v, w)));
            }
        }
    }
}
