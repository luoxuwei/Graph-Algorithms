import java.util.LinkedList;
import java.util.List;

public class GraphDFS {
    private List<Integer> order;
    private boolean[] validate;
    private Graph g;
    public GraphDFS(Graph g) {
        order = new LinkedList<>();
        validate = new boolean[g.V()];
        this.g =g;
//        dfs(0);
        //当输入图不是一个连通图，有多个连通分量时，为保证每个顶点都能访问到，在最开始的时候就需要用一个循环遍历所有顶点
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v])
                dfs(v);
        }
    }

    private void dfs(int v) {
        order.add(v);
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w);
        }
    }

    private Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        GraphDFS dfs = new GraphDFS(new Graph("g.txt"));
        System.out.println(dfs.order);
    }

}
