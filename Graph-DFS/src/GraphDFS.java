import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GraphDFS {
    private List<Integer> order;
    private boolean[] validate;
    private Graph g;
    public GraphDFS(Graph g) {
        order = new LinkedList<>();
        validate = new boolean[g.V()];
        this.g =g;
//        dfs(0);
        //当输入图不是一个连通图，有多个连通分量时，为保证每个顶点都能访问到，在最开始的时候就需要用一个循环遍历所有顶点
        for (int v = 0; v < g.V(); v++) {
            if (!validate[v]) {
//                dfs(v);
                preorderTraversal(v);
            }
        }
    }

    private void dfs(int v) {
        order.add(v);
        validate[v] = true;
        for (int w : g.adj(v)) {
            if (!validate[w])
                dfs(w);
        }
    }

    //深度优先遍历非递归实现
    private void preorderTraversal(int v) {
        Stack<LinkedList> stack = new Stack<>();

        int cur = v;
        while (cur >= 0 || !stack.empty()) {
            if (!validate[cur]) {
                order.add(cur);
                validate[cur] = true;
                LinkedList<Integer> linkedList = new LinkedList<>();
                for (int w:g.adj(cur)) {
                    if (!validate[w]) {
                        linkedList.add(w);
                    }
                }
                if (!linkedList.isEmpty()) {
                    cur = linkedList.removeFirst();
                    if (!linkedList.isEmpty()) {
                        stack.push(linkedList);
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    cur = -1;
                } else {
                    LinkedList<Integer> linkedList = stack.peek();
                    cur = linkedList.removeFirst();
                    if (linkedList.isEmpty()) {
                        stack.pop();
                    }
                }
            }

        }
    }

    private Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        GraphDFS dfs = new GraphDFS(new Graph("g.txt"));
        System.out.println(dfs.order);
    }

}
