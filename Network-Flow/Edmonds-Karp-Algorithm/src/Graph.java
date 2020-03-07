import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    int E;
    int V;
    TreeMap<Integer, Integer>[] adj;
    boolean directed;

    public Graph(String path, boolean directed) {
        File file = new File(path);
        this.directed = directed;
        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            E = 0;
            scanner.nextInt();
            adj = new TreeMap[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap();
            }
            int a = 0; int b = 0; int weight = 0;
            for (int i = 0; i < E; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                weight = scanner.nextInt();
                addEdge(a, b, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Graph(String path) {
        this(path, false);
    }

    public Graph(int v, boolean directed) {
        V = v;
        this.directed = directed;
        E = 0;
        adj = new TreeMap[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new TreeMap();
        }
    }

    public void addEdge(int a, int b, int weight) {
        validateVertex(a);
        validateVertex(b);
        if (a == b)
            throw new IllegalArgumentException("");//自环边
        if (adj[a].containsKey(b) || adj[b].containsKey(a))
            throw new IllegalArgumentException("");//平行边

        adj[a].put(b, weight);
        if (directed)
            adj[b].put(a, weight);
        E++;
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


    public void setWeight(int a, int b, int w) {
        if (!hasEdge(a, b))
            throw new IllegalArgumentException("");

        adj[a].put(b, w);
        if (!directed)
            adj[b].put(a, w);

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
        if (!directed)
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
