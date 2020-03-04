import javafx.util.Pair;

import java.util.*;

public class Solution1168 {

    class UF {
        Pair<Integer, Integer>[] nodes;

        public UF(int[] wells) {
            nodes = new Pair[wells.length];
            for (int i = 0; i<wells.length; i++) {
                nodes[i] = new Pair<>(i, wells[i]);
            }
        }

        public Pair<Integer, Integer> find(int v) {
            if (nodes[v].getKey() != v)
                return find(nodes[v].getKey());

            return nodes[v];
        }

        public void unionElement(int a, int b) {
            Pair<Integer, Integer> rootA = find(a);
            Pair<Integer, Integer> rootB = find(b);
            nodes[rootB.getKey()] = rootA;
        }
    }

    public class WeightedEdge implements Comparable<WeightedEdge> {
        private int V;
        private int W;
        private int Weight;

        public WeightedEdge(int v, int w, int weight) {
            V = v;
            W = w;
            Weight = weight;
        }

        public int getV() {
            return V;
        }

        public int getW() {
            return W;
        }

        public int getWeight() {
            return Weight;
        }

        @Override
        public String toString() {
            return String.format("%d-%d:%d", V, W, Weight);
        }

        @Override
        public int compareTo(WeightedEdge o) {
            return Weight - o.Weight;
        }
    }

    class Graph {
        TreeMap<Integer, Integer>[] adj;

        public Graph(int[] wells, int[][] pipes) {

            adj = new TreeMap[wells.length + 1];
            for (int i = 0; i < wells.length + 1; i++) {
                adj[i] = new TreeMap<>();
            }

            for (int i = 0; i < pipes.length; i++) {
                Integer weight = adj[pipes[i][0]].get(pipes[i][1]);
                if (weight == null || weight > pipes[i][2]) {
                    weight = pipes[i][2];
                }
                adj[pipes[i][0]].put(pipes[i][1], weight);
                adj[pipes[i][1]].put(pipes[i][0], weight);

            }

            for (int i = 0; i < wells.length; i++) {
                adj[0].put(i+1, wells[i]);
                adj[i+1].put(0, wells[i]);
            }
        }

        public Iterable<Integer> adj(int v) {
            return adj[v].keySet();
        }

        public int getWeight(int v, int w) {
            return adj[v].get(w);
        }
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
//        return kruskal(n, wells, pipes);
        return prim(n, wells, pipes);
    }

    private int prim(int n, int[] wells, int[][] pipies) {
        Graph graph = new Graph(wells, pipies);
        PriorityQueue<WeightedEdge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[wells.length + 1];
        int cost = 0;
        for (int v : graph.adj(0)) {
            queue.add(new WeightedEdge(0, v, graph.getWeight(0, v)));
        }

        visited[0] = true;
        WeightedEdge weightedEdge = null;

        int newV;

        while (!queue.isEmpty()) {
            weightedEdge = queue.remove();
            if (visited[weightedEdge.getV()] && visited[weightedEdge.getW()])
                continue;

            cost = cost + weightedEdge.getWeight();
            newV = visited[weightedEdge.getV()]?weightedEdge.getW():weightedEdge.getV();

            visited[newV] = true;

            for (int w : graph.adj(newV)) {
                queue.add(new WeightedEdge(newV, w, graph.getWeight(newV, w)));
            }

        }

        return cost;
    }

    private int kruskal(int n, int[] wells, int[][] pipies) {

        int cost = 0;
        for (int c:wells) {
            cost = cost + c;
        }
        UF uf = new UF(wells);
        List<WeightedEdge> weightedEdgeList = new ArrayList<>();
        for (int i = 0; i < pipies.length; i++) {
            weightedEdgeList.add(new WeightedEdge(pipies[i][0], pipies[i][1], pipies[i][2]));
        }

        Collections.sort(weightedEdgeList);
        for (WeightedEdge weightedEdge:weightedEdgeList) {

            Pair<Integer, Integer> a = uf.find(weightedEdge.V-1);
            Pair<Integer, Integer> b = uf.find(weightedEdge.W-1);
            if (a.getKey() != b.getKey()) {
                if (a.getValue() >= b.getValue() && weightedEdge.getWeight() < a.getValue()) {
                    cost = cost - a.getValue() + weightedEdge.getWeight();
                    uf.unionElement(b.getKey(), a.getKey());
                } else if (b.getValue() > weightedEdge.getWeight()) {
                    cost = cost - b.getValue() + weightedEdge.getWeight();
                    uf.unionElement(a.getKey(), b.getKey());
                }
            }

        }

        return cost;

    }

    public static void main(String[] args) {
        Solution1168 solution1168 = new Solution1168();
//        System.out.println(solution1168.minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}}));

        System.out.println(solution1168.minCostToSupplyWater(5, new int[]{46012,72474,64965,751,33304}, new int[][]{{2,1,6719}, {3,2,753121}, {5,3,44918}}));
    }


}
