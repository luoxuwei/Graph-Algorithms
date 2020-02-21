import java.util.LinkedList;

public class Solution1091 {
    private int[][] G;
    private int R;
    private int C;
    private int[][] dirs;

    public int shortestPathBinaryMatrix(int[][] grid) {
        G = grid;
        R = grid.length;
        C = grid[0].length;
        dirs = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

        int res = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        int cur = 0;
        int r = 0;
        int c = 0;
        dirs[r][c] = 2;
        queue.addLast(cur);
        int nextY = 0;
        int nextX = 0;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            r = cur/C;
            c = cur%C;

            for (int i = 0; i < 8; i++) {
                nextY = r + dirs[i][0];
                nextX = c + dirs[i][1];

                if (nextX >= 0 && nextX < C && nextY >= 0 && nextY < R && G[nextY][nextX] == 0) {
                    queue.addLast(nextY * C + c);
                    G[nextY][nextX] = 2;
                    res++;
                }

            }
        }

        return -1;
    }

    public int shortestPathBinaryMatrix(int v) {

    }
}
