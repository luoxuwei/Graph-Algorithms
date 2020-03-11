import java.util.ArrayList;
import java.util.LinkedList;

public class TopoSort {
    Graph G;
    int[] indegrees;
    ArrayList<Integer> res;
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

    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        TopoSort sort = new TopoSort(g);
        System.out.println(sort.res);
    }
}
