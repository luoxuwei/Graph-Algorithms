public class MaxFlow {
    Graph network;
    Graph rG;
    int s;
    int t;

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



    }
}
