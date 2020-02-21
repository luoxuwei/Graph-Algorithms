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
        dirs = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},{1, 0},{1, -1}};

        if (G[0][0] == 1) {
            return -1;
        }

        if (G.length == 1 && G[0].length == 1) {
            return 1;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int cur = 0;
        int r = 0;
        int c = 0;
        G[r][c] = 2;
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
                    queue.addLast(nextY * C + nextX);
                    G[nextY][nextX] = G[r][c] + 1;

                    if (nextX == C -1 && nextY == R - 1) {
                        return G[nextY][nextX] - 1;
                    }
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution1091 solution1091 = new Solution1091();
        System.out.println(solution1091.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

}
