import java.util.Arrays;

public class UF {
    private int[] parent;

    public UF(int n) {
        parent = new int[n];
        for ( int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int v) {
        int res = -1;
        while ((res = parent[v]) != res) ;
        return res;
    }

    public void unionElements(int v, int w) {
        // 不能单纯将w连上v，因为如果w v 分别属于两个不连通的集合
        //w v 连接后这两个集合里的元素也都是相互连通的了，为了达到这个效果
        //应该将 w 所在集合的root 连上 v 所在集合的root
        int rootV = find(v);
        int rootW = find(w);

        if (rootV != rootW) {
            parent[rootW] = rootV;
        }

    }

    public boolean isConnected(int v, int w) {
        return find(v) == find(w);
    }


}
