import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {
    int E;
    int V;
    TreeSet[] adj;

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
}
