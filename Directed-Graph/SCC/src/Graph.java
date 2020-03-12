import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private List<Integer> post;
    private boolean directed;
    private int[] indegree;
    private int[] outdegree;
    private boolean[] visited;

    public Graph(String path) {
        this(path, false);
    }

    public Graph(int v, boolean directed) {
        this.directed = directed;
        V = v;
        adj = new TreeSet[v];
        visited = new boolean[V];
        for (int i = 0; i < v; i++) {
            adj[i] = new TreeSet<>();
        }
    }

    public Graph(String path, boolean directed) {
        this.directed = directed;
        File file = new File(path);
        visited = new boolean[V];
        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if (V < 0) throw new IllegalArgumentException("");
            adj = new TreeSet[V];
            indegree = new int[V];
            outdegree = new int[V];
            for (int i=0; i<V; i++)
                adj[i] = new TreeSet<>();

            E = scanner.nextInt();
            for (int i=0; i<E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                //检测是否是平行边
                if (!adj[a].contains(b))
                    adj[a].add(b);
                if (!directed) {
                    if (!adj[b].contains(a))
                        adj[b].add(a);
                } else {
                    indegree[b]++;
                    outdegree[a]++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<Integer> post() {
        post = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
        return post;
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : adj[v]) {
            if (!visited[w])
                dfs(w);
        }
        post.add(v);
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (!adj[v].contains(w))
            adj[v].add(w);
        if (!directed) {
            if (!adj[w].contains(v))
                adj[w].add(v);
        } else {
            indegree[w]++;
            outdegree[v]++;
        }
        E++;
    }



    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public int indegree(int v) {
        validateVertex(v);
        if (!directed)
            throw new IllegalArgumentException("");

        return indegree[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        if (!directed)
            throw new IllegalArgumentException("");

        return outdegree[v];
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w); //or adj[w].constains(v)
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].contains(w)) {
            E--;
            if (directed) {
                indegree[w]--;
                outdegree[v]--;
            }
        }

        adj[v].remove(w);

        if (!directed)
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

//v    public int degree(int v) {
//        validateVertex(v);
//        return adj[v].size();
//    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("");
    }

    public boolean isDirected(){
        return directed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        for (int v=0; v<V; v++) {
            sb.append(String.format("%d : ", v));
            for(int w : adj[v])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        System.out.println(g);
    }
}
