//https://leetcode-cn.com/problems/unique-paths-iii/
public class Solution980 {
    private int[][] G;
    private boolean[] validate;
    private int res = 0;
    int R = 0;
    int C = 0;
    int[][] dirs;
    public int uniquePathsIII(int[][] grid) {
        G = grid;
        R = grid.length;
        C = grid[0].length;
        validate = new boolean[R*C];
        dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};

        dfs(0,  R*C);
        return res;
    }

    private void dfs(int v, int left) {
        validate[v] = true;
        left--;
        if (left == 0 && v == (R*C -1)) {
            res++;
        }
        int nr = 0;
        int nc = 0;

        for (int i = 0; i < 4; i++) {
            nr = v / C + dirs[i][0];
            nc = v % C + dirs[i][1];
            if (!validate[nr*C + nc]) {
                dfs(nr*C + nc, left);
            }
        }

        //遍历了所有连接的顶点没有找到哈密尔顿回路，回退
        validate[v] = false;
    }
}

