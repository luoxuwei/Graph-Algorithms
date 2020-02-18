import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public Graph(String path) {
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if (V < 0) throw new IllegalArgumentException("");
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

                if (!adj[b].contains(a))
                    adj[b].add(a);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("");
    }
}
