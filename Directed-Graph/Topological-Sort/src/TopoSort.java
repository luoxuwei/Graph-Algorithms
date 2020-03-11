import java.util.ArrayList;
import java.util.LinkedList;

public class TopoSort {
    Graph G;
    int[] indegrees;
    ArrayList<Integer> res;
    boolean hasCycle;
    public TopoSort(Graph g) {
        G = g;
        res = new ArrayList<>();
        indegrees = new int[G.V()];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            indegrees[v] = G.indegree(v);
            if (indegrees[v] == 0) {
                queue.addLast(v);
            }
        }
        int cur = 0;
        //迭代，将入度为零的顶点保存到结果中，并将与之相邻的顶点的入度减-，将减一后入度为0的顶点加入列队
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            res.add(cur);
            for (int w : G.adj(cur)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    queue.addLast(w);
                }
            }
        }

        if (res.size() != G.V()) {
            hasCycle = true;
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        TopoSort sort = new TopoSort(g);
        System.out.println(sort.res);
    }
}
