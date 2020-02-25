import java.util.LinkedList;

public class Solution1034 {
    private boolean[] visited;

    public int[][] colorBorder(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }
        int target = image[sr][sc];
        if (target == newColor) {
            return image;
        }
        int[][] dirs = new int[][]{{0, -1},{-1, 0},{0, 1},{1, 0}};
        int R = image.length;
        int C = image[0].length;
        visited = new boolean[R*C];
        LinkedList<Integer> queue = new LinkedList<>();
        if (sr == 0 || sr == (R - 1) || sc == 0 || sc == (C -1)) {
            image[sr][sc] = newColor;
        }

        queue.addLast(sr*C+sc);
        visited[sr*C+sc] = true;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            int nr = 0;
            int nc = 0;

            for (int i = 0; i < 4; i++) {
                nr = cur/C + dirs[i][0];
                nc = cur%C + dirs[i][1];
                if (nr >=0 && nr < R && nc >=0 && nc < C) {

                    if (image[nr][nc] == target && !visited[nr*C+nc]) {
                        queue.addLast(nr*C+nc);
                        visited[nr*C+nc] = true;
                    } else if (image[nr][nc] != target) {
                        image[cur/C][cur%C] = newColor;
                    }

                    if ((nr == 0 || nr == (R - 1) || nc == 0 || nc == (C -1)) && image[nr][nc] == target) {
                        image[nr][nc] = newColor;
                    }
                }
            }
        }

        return image;
    }

    public static void main(String[] args) {
        Solution1034 solution1034 = new Solution1034();
        int[][] board = new int[][]{{1,2,2},{2,3,2}};
        solution1034.colorBorder(board,0,1,3);
        print(board);

    }

    private static void print(int[][] board) {
        for (int[] r : board) {
            for (int c : r) {
                System.out.print(c);
                System.out.print(',');
            }
            System.out.println();
        }
    }

}
