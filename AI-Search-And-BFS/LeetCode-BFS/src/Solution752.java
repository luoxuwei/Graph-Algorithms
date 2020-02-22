import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;

public class Solution752 {
    int[][] dirs;
    int[] a;

    public int openLock(String[] deadends, String target) {
        int targetInt = Integer.parseInt(target);
        a = new int[]{0, -1, 1};
        dirs = new int[4 * 3][4];
        LinkedList<Integer> queue = new LinkedList<>();
        int cur = 0;
        queue.addLast(0);
        TreeMap<Integer, Integer> validate = new TreeMap<>();
        int d = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                dirs[d][i] = a[j];
                d++;
            }
        }
        HashSet<Integer> deadendsInt = new HashSet<>();
        int base = 1;
        for (int ds = 0; ds < deadends.length; ds++) {
            deadendsInt.add(Integer.parseInt(deadends[ds]));
        }
        int nextA = 0; int nextB = 0; int nextC = 0; int nextD = 0;
        int max = 9999 - deadends.length;
        int next;
        validate.put(cur, 0);
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            next = 0;
            for (int i = 0; i < dirs.length; i ++) {
                 nextA = cur/1000 + dirs[i][0];
                 if (nextA < 0) {
                     nextA = 9;
                 } else if (nextA > 9) {
                     nextA = 0;
                 }
                 next = nextA *1000;
                 nextB = cur/100 + dirs[i][1];
                if (nextB < 0) {
                    nextB = 9;
                } else if (nextB > 9) {
                    nextB = 0;
                }
                next += nextB * 100;
                 nextC = cur/10 + dirs[i][2];
                if (nextC < 0) {
                    nextC = 9;
                } else if (nextC > 9) {
                    nextC = 0;
                }
                next += nextC * 10;
                 nextD = cur%1000 + dirs[i][3];
                if (nextD < 0) {
                    nextD = 9;
                } else if (nextD > 9) {
                    nextD = 0;
                }
                next += nextD;
                if (!deadendsInt.contains(next) && !validate.containsKey(next)) {
                    queue.addLast(next);
                    validate.put(next, validate.get(cur) + 1);
                    if (next == targetInt) {
                        return validate.get(next);
                    }
                    if (validate.keySet().size() == max) {
                        return -1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution752 solution752 = new Solution752();
        System.out.println(solution752.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }
}