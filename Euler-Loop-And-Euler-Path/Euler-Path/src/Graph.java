import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {
    int E;
    int V;
    TreeSet<Integer>[] adj;

    public Graph(String path) {
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            E = scanner.nextInt();
            adj = new TreeSet[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet();
            }
            int a = 0; int b = 0;
            for (int i = 0; i < E; i++) {
                validateVertex(a);
                validateVertex(b);
                if (a == b)
                    throw new IllegalArgumentException("");//自环边
                if (adj[a].contains(b) || adj[b].contains(a))
                    throw new IllegalArgumentException("");//平行边
                a = scanner.nextInt();
                b = scanner.nextInt();
                adj[a].add(b);
                adj[b].add(a);
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
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].contains(w)) E--;
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Graph clone() {
        try {
            Graph clone = (Graph) super.clone();
            clone.adj = new TreeSet[clone.V];
            for (int i = 0; i < clone.V; i++) {
                clone.adj[i] = new TreeSet<Integer>();
                for (int w : adj[i]) {
                    clone.adj[i].add(w);
                }
            }

            return clone;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
