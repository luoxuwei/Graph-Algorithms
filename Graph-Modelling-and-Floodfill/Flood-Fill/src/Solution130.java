import java.util.LinkedList;
import java.util.List;

//https://leetcode-cn.com/problems/surrounded-regions/
public class Solution130 {
    char[][] G;
    int R;
    int C;
    int [][] dirs;
    boolean[] validate;

    public void solve(char[][] board) {
        G = board;
        R = G.length;
        if (R == 0) {
            return ;
        }
        C = G[0].length;
        dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};
        validate = new boolean[R*C];
        List<Integer> res;

        for (int v = 0; v <= (R * C - 1); v++) {
            if (G[v/C][v%C] == 'O') {
                res = new LinkedList<>();
                if (!dfs(v, res)) {
                   for (int w : res) {
                       int r = w/C;
                       int c = w%C;
                       G[r][c] = 'X';
                   }
                }
            }
        }

    }

    private boolean dfs(int v, List<Integer> resList) {
        int r = v/C;
        int c = v%C;
        validate[v] = true;
        resList.add(v);
        int nr = 0; int nc = 0;
        boolean res = false;
        if (r == 0 || r == (R - 1) || c == 0 || c == (C - 1)) {
            res = true;
        }
        for (int i = 0; i < 4; i++) {
            nr = r + dirs[i][0];
            nc = c + dirs[i][1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && !validate[nr*C + nc] && G[nr][nc] == 'O') {
                if (nr == 0 || nr == (R - 1) || nc == 0 || nc == (C - 1)) {
                    res = true;
                }
                res = res | dfs(nr * C + nc, resList);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution130 solution1020 = new Solution130();

    }

}
