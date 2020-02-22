import com.sun.javafx.image.IntPixelGetter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

//智力题：有两个水桶，一个装5升水，一个装3升水。怎么利用这两个水桶，得到4升水
public class Soloution {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> pre = new TreeMap<>();
        LinkedList<Integer> quque = new LinkedList<>();
        quque.addLast(0);
        pre.put(0, 0);
        int cur;
        int x, y;
        LinkedList<Integer> nexts = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        while (!quque.isEmpty()) {
            nexts.clear();
            cur = quque.removeFirst();
            x = cur/10;
            y = cur%10;

            if (x != 5) {
                x = 5;
                nexts.add(x * 10 + y);
                x = cur/10;
            }
            if (y != 3) {
                y = 3;
                nexts.add(x * 10 + y);
                y = cur%10;
            }
            if (x != 0) {
                x = 0;
                nexts.add(x * 10 + y);
                x = cur/10;
            }
            if (y != 0) {
                y = 0;
                nexts.add(x * 10 + y);
                y = cur%10;
            }
            if (x != 5 && y != 0) {
                int temp = 5 - x;
                x = x + y;
                if (x > 5) {
                    x = 5;
                }
                y = y - temp;
                if (y < 0) {
                    y = 0;
                }
                nexts.add(x * 10 + y);
                x = cur/10;
                y = cur%10;
            }

            if (x != 0 && y != 3) {
                int temp = 3 - y;
                y = y + x;
                if (y > 3) {
                    y = 3;
                }
                x = x -temp;
                if (x < 0) {
                    x = 0;
                }
                nexts.add(x * 10 + y);
            }
            for ( int i : nexts) {
                if (!pre.containsKey(i)) {
                    quque.addLast(i);
                    pre.put(i, cur);
                }

                if (((i / 10) == 4) || ((i % 10) == 4)) {
                    int preState = i;
                    res.add(i);
                    while ((preState = pre.get(i)) != 0) {
                        res.add(preState);
                    }
                    Collections.reverse(res);
                    System.out.println(res);
                    return ;
                }
            }
        }
    }
}
