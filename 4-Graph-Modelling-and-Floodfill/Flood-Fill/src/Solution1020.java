
//https://leetcode-cn.com/problems/number-of-enclaves/
public class Solution1020 {
    int[][] G;
    int R;
    int C;
    int [][] dirs;
    int nums = 0;

    public int numEnclaves(int[][] A) {
        G = A;
        R = G.length;
        if (R == 0) {
            return 0;
        }
        C = G[0].length;
        dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};
        int res = 0;
        for (int v = 0; v <= R * C - 1; v++) {
            if (G[v/C][v%C] == 1) {
                nums = 0;
                if (!dfs(v)) {
                    res = res + nums;

                }

            }
        }
        return res;
    }

    private boolean dfs(int v) {
        nums++;
        int r = v/C;
        int c = v%C;
        G[r][c] = 2;
        int nr = 0; int nc = 0;
        boolean res = false;
        if (r == 0 || r == (R - 1) || c == 0 || c == (C - 1)) {
            res = true;
        }
        for (int i = 0; i < 4; i++) {
            nr = r + dirs[i][0];
            nc = c + dirs[i][1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && G[nr][nc] == 1) {
                if (nr == 0 || nr == (R - 1) || nc == 0 || nc == (C - 1)) {
                    res = true;
                }
                res = res | dfs(nr * C + nc);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution1020 solution1020 = new Solution1020();
        System.out.println(solution1020.numEnclaves(new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}}));
    }

}
