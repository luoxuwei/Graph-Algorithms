import java.util.LinkedList;

public class Solution1034 {

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
        LinkedList<Integer> queue = new LinkedList<>();
        image[sr][sc] = newColor;
        queue.addLast(sr*C+sc);
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            int nr = 0;
            int nc = 0;

            for (int i = 0; i < 4; i++) {
                nr = cur/C + dirs[i][0];
                nc = cur%C + dirs[i][1];
                if (nr >=0 && nr < R && nc >=0 && nc < C) {
                    if (nr == 0 || nr == (R - 1) || nc == 0 || nc == (C -1)) {
                        image[nr][nc] = newColor;
                    }
                    if (image[nr][nc] == target) {
                        queue.addLast(nr*C+nc);
                    }
                    if (image[nr][nc] != target) {
                        image[cur/C][cur%C] = newColor;
                    }
                }
            }
        }

        return image;
    }

}
