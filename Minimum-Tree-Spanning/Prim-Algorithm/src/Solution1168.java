import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution1168 {
    class WeightedEdge {
        int v;
        int w;
        int weight;
        WeightedEdge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
    }

    public class Graph {
        int E;
        int V;
        TreeMap<Integer, Integer>[] adj;

        public Graph(int[][] pips, int n) {
            adj = new TreeMap[n];
            V = n;
            E = pips.length;
            adj = new TreeMap[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap();
            }
            int a = 0; int b = 0; int weight = 0;
            for (int i = 0; i < E; i++) {
                a = pips[i][0];
                b = pips[i][1];
                weight = pips[i][2];
                validateVertex(a);
                validateVertex(b);
                if (a == b)
                    throw new IllegalArgumentException("");//自环边
                if (adj[a].containsKey(b) || adj[b].containsKey(a))
                    throw new IllegalArgumentException("");//平行边

                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }

        }

        public void validateVertex(int v) {
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("");
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v].keySet();
        }

        public int degree(int v) {
            return adj[v].size();
        }

        public int getWeight(int v, int w) {
            if (hasEdge(v, w)) return adj[v].get(w);
            throw new IllegalArgumentException("");
        }

        public boolean hasEdge(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            return adj[v].containsKey(w);
        }

        public void removeEdge(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            if (adj[v].containsKey(w)) E--;
            adj[v].remove(w);
            adj[w].remove(v);
        }

    }

    public class CC {
        Graph G;
        int cccount;
        int[] ccids;

        public CC(Graph g) {
            G = g;
            cccount = 0;
            ccids = new int[g.V()];
            for (int i = 0; i < g.V(); i++)
                ccids[i] = -1;

            for (int v = 0; v < G.V(); v++) {
                if (ccids[v] == -1) {
                    dfs(v, cccount);
                    cccount++;
                }
            }
        }

        private void dfs(int v, int ccid) {
            ccids[v] = ccid;

            for (int w: G.adj(v)) {
                if (ccids[w] == -1) {
                    dfs(w, ccid);
                }
            }
        }

        public int cccount() {
            return cccount;
        }

        public List<List<Integer>> result() {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < cccount; i++) {
                result.add(new LinkedList<>());
            }
            for (int v = 0; v < G.V(); v++) {
               result.get(ccids[v]).add(v);
            }
            return result;
        }


    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

    }


}
