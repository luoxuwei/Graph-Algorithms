//https://leetcode-cn.com/problems/unique-paths-iii/
public class Solution980 {
    private int[][] G;
    private boolean[] validate;
    private int res = 0;
    int R = 0;
    int C = 0;
    int[][] dirs;
    int start;
    int end;
    public int uniquePathsIII(int[][] grid) {
        G = grid;
        R = grid.length;
        C = grid[0].length;
        validate = new boolean[R*C];
        dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};
        int invalid = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    start = i * C + j;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    invalid++;
                }
            }
        }
        dfs(start,  R*C - invalid);
        return res;
    }

    private void dfs(int v, int left) {
        validate[v] = true;
        left--;
        if (left == 0 && v == end) {
            res++;
        }
        int nr = 0;
        int nc = 0;

        for (int i = 0; i < 4; i++) {
            nr = v / C + dirs[i][0];
            nc = v % C + dirs[i][1];
            if (nr >=0 && nr < R && nc >=0 && nc < C) {
                if (!validate[nr*C + nc] && G[nr][nc] == 0) {
                    dfs(nr*C + nc, left);
                }
            }

        }

        //遍历了所有连接的顶点没有找到哈密尔顿回路，回退
        validate[v] = false;
    }

    public static void main(String[] args) {
        Solution980 solution980 = new Solution980();
        System.out.println(solution980.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
    }
}

