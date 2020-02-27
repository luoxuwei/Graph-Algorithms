import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    int E;
    int V;
    TreeMap<Integer, Integer>[] adj;

    public Graph(String path) {
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            E = scanner.nextInt();
            adj = new TreeMap[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap();
            }
            int a = 0; int b = 0; int weight = 0;
            for (int i = 0; i < E; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                weight = scanner.nextInt();
                validateVertex(a);
                validateVertex(b);
                if (a == b)
                    throw new IllegalArgumentException("");//自环边
                if (adj[a].containsKey(b) || adj[b].containsKey(a))
                    throw new IllegalArgumentException("");//平行边

                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].containsKey(w)) E--;
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Graph clone() {
        try {
            Graph clone = (Graph) super.clone();
            clone.adj = new TreeMap[clone.V];
            for (int i = 0; i < clone.V; i++) {
                clone.adj[i] = new TreeMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> entry: adj[i].entrySet()) {
                    clone.adj[i].put(entry.getKey(), entry.getValue());
                }
            }

            return clone;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
