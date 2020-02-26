import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EulerPath {
    Graph G;
    CC cc;
    ArrayList<Integer> res;
    public EulerPath(Graph g) {
        G = g;
        cc = new CC(g);
        res = new ArrayList<>();
        if (hasEulerLoop()) {
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            int cur;
            int w;
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if (G.degree(cur) == 0)
                    res.add(stack.pop());
                else {
                    w = G.adj(cur).iterator().next();
                    stack.push(w);
                    G.removeEdge(cur, w);
                }

            }
        }

    }

    public boolean hasEulerLoop() {
        if (cc.cccount() > 1) { // 必须是联通图
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 != 0) //每个顶点的边必须是偶数
                return false;
        }
        return true;
    }

    public List<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        EulerPath eulerPath = new EulerPath(new Graph("g.txt"));
        System.out.println(eulerPath.result());

        eulerPath = new EulerPath(new Graph("g2.txt"));
        System.out.println(eulerPath.result());
    }


}
