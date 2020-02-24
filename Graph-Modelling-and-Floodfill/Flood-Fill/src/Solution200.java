public class Solution200 {
    char[][] G;
    int R;
    int C;
    int [][] dirs;

    public int numIslands(char[][] grid) {
        G = grid;
        R = G.length;
        if (R == 0) {
            return 0;
        }
        C = G[0].length;
        dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};
        int res = 0;
        for (int v = 0; v <= R * C - 1; v++) {
            if (G[v/C][v%C] == '1') {
                dfs(v);
                res++;
            }
        }
        return res;
    }

    private void dfs(int v) {

        int r = v/C;
        int c = v%C;
        G[r][c] = '2';
        int nr = 0; int nc = 0;

        for (int i = 0; i < 4; i++) {
            nr = r + dirs[i][0];
            nc = c + dirs[i][1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && G[nr][nc] == '1') {
                dfs(nr * C + nc);
            }
        }

    }
}
