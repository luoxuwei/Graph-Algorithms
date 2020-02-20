import java.util.LinkedList;

public class Solution695 {
    private int[][] G;
    private int[][] dirs;
    private int rows; // 行数
    private int rols; //列数
    public int maxAreaOfIsland(int[][] grid) {
        G = grid;
        dirs = new int[][]{{-1,0},{0, 1},{1, 0},{0, -1}};
        int sum = 0;
        int last = 0;
        rows = G.length;
        rols = G[0].length;

        for (int v = 0; v <= (rows * rols - 1); v++) {
            if (G[v/rols][v%rols] > 0 && G[v/rols][v%rols] != 2) {
                sum = maxAreaOfIsland(v);
                if (sum < last) {
                    sum = last;
                }
                last = sum;
            }
        }
        return sum;
    }

    public int maxAreaOfIsland(int v) {
        int sum = 1;
        int row = v/rols;
        int rol = v%rols;
        G[row][rol] = 2;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(v);
        int cur = v;
        int nextX = 0;
        int nextY = 0;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            row = cur/rols;
            rol = cur%rols;
            for (int i = 0; i < 4; i++) {
                nextY = row + dirs[i][0];
                nextX = rol + dirs[i][1];
                if (nextX > 0 && nextX < rols && nextY > 0 && nextY < rows
                        && G[nextY][nextX] > 0 && G[nextY][nextX] != 2) {
                    sum++;
                    queue.addLast(nextY*rols + nextX);
                    G[nextY][nextX] = 2;
                }
            }

        }
        return sum;
    }
    
}
