import java.util.*;

public class Dijkstra {
    Graph G;
    int[] dis;
    int S;
    boolean[] visited;
    int[] pre;

    class Node implements Comparable<Node> {
        int v;
        int dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }
    }

    public Dijkstra(Graph g, int s) {
        G = g;
        S = s;
        dis = new int[g.V()];
        pre = new int[g.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);
        visited = new boolean[g.V()];
        dis[s] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, dis[0]));
        while (!queue.isEmpty()) {
            int cur = queue.remove().v;

            if (visited[cur]) continue;
            visited[cur] = true;
            for (int w:G.adj(cur)) {
                if (!visited[w] && (dis[cur] + G.getWeight(cur, w)) < dis[w]) {
                    dis[w] = dis[cur] + G.getWeight(cur, w);
                    queue.add(new Node(w, dis[w]));
                    pre[w] = cur;
                }
            }
        }
    }

    public int disTo(int w) {
        G.validateVertex(w);
        return dis[w];
    }

    public boolean isConnected(int w) {
        return visited[w];
    }

    public List<Integer> path(int w) {
        List<Integer> res = new LinkedList<>();
        if (!isConnected(w)) return res;
        int cur = w;
        res.add(w);
        while ((cur = pre[cur]) != S) {
            res.add(cur);
        }
        res.add(S);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);

        for (int v = 1; v < g.V(); v++) {
            System.out.print(dijkstra.disTo(v) + " ");
        }

        System.out.println(dijkstra.path(3));
    }
}
