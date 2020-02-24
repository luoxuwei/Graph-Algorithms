import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HamiltomLoop {
    private Graph G;
    private boolean[] validate;
    private int end = -1;
    private int[] pre;

    public HamiltomLoop(Graph g) {
        G = g;
        validate = new boolean[g.V()];
        pre = new int[g.V()];
        dfs(0, 0);
    }

    private boolean dfs(int v, int p) {
        validate[v] = true;
        pre[v] = p;
        for (int w : G.adj(v)) {
            if (!validate[w]) {
                if (dfs(w, v)) return true;
            } else if (w == 0 && allVisited()) {
                //一开始写成了 end = w，切结粗心大意
                end = v;
                return true;
            }
        }
        //遍历了所有连接的顶点没有找到哈密尔顿回路，回退
        validate[v] = false;
        return false;
    }

    private boolean allVisited() {
        for (boolean visited :validate) {
            if (!visited) return false;
        }

        return true;
    }

    public List<Integer> result() {
        List<Integer> res = new LinkedList<>();
        if (end == -1) {
            return res;
        }

        res.add(end);
        int cur = end;
        while ((cur = pre[cur]) != 0) {
            res.add(cur);
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        HamiltomLoop hamiltomLoop = new HamiltomLoop(new Graph("g.txt"));
        System.out.println(hamiltomLoop.result());

        HamiltomLoop hamiltomLoop1 = new HamiltomLoop(new Graph("g2.txt"));
        System.out.println(hamiltomLoop1.result());
    }
}
