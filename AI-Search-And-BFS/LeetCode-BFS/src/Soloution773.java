import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class Soloution773 {

    public int slidingPuzzle(int[][] board) {
        TreeMap<String, Integer> res = new TreeMap<>();
        char[] chars = new char[6];
        int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        String target = "123450";
        int a = 0;
        for (int i=0; i<2; i++) {
            for (int j=0; j<3; j++) {
                chars[a] = Character.forDigit(board[i][j], 10);
                a++;
            }
        }
        String cur = new String(chars);
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(cur);
        res.put(cur, 0);
        int i0;
        int n0;
        int nx, ny;
        String next;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            i0 = cur.indexOf('0');
            for (int i = 0; i < 4; i++) {
                ny = dirs[i][0] + i0/3;
                nx = dirs[i][1] + i0%3;
                if (ny >= 0 && ny < 2 && nx >= 0 && nx < 3) {
                    n0 = ny * 3 + nx;
                    char[] tempc = cur.toCharArray();
                    char temp = tempc[i0];
                    tempc[i0] = tempc[n0];
                    tempc[n0] = temp;
                    next = new String(tempc);
                    if (!res.containsKey(next)) {
                        queue.addLast(next);
                        res.put(next, res.get(cur) + 1);
                        if (target.equals(next))
                            return res.get(next);
                    }
                }

            }
        }

        return -1;
    }



}
