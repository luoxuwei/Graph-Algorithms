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
        int x, y;//x表示5升的桶，y表示3升的桶
        LinkedList<Integer> nexts = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        //一共有6种操作
        while (!quque.isEmpty()) {
            nexts.clear();
            cur = quque.removeFirst();
            x = cur/10;
            y = cur%10;
            //把x装满，y不变
            if (x != 5) {
                x = 5;
                nexts.add(x * 10 + y);
                x = cur/10;//恢复
            }
            //y装满，x不变
            if (y != 3) {
                y = 3;
                nexts.add(x * 10 + y);
                y = cur%10;
            }
            //x倒空，y不变
            if (x != 0) {
                x = 0;
                nexts.add(x * 10 + y);
                x = cur/10;
            }
            //y倒空，x不变
            if (y != 0) {
                y = 0;
                nexts.add(x * 10 + y);
                y = cur%10;
            }
            //把y里的水倒到x里
            if (x != 5 && y != 0) {
                int temp = 5 - x;//x剩余容量
                x = x + y;
                if (x > 5) {
                    x = 5;
                }
                y = y - temp;
                if (y < 0) {//y桶里的水不够temp的量，就表示全都倒入了x，y变0
                    y = 0;
                }
                nexts.add(x * 10 + y);
                x = cur/10;
                y = cur%10;
            }
            //把x里的水倒到y里
            if (x != 0 && y != 3) {
                int temp = 3 - y;//y剩余容量
                y = y + x;
                if (y > 3) {
                    y = 3;
                }
                x = x -temp;
                if (x < 0) {//x桶里的水不够temp的量，就表示全都倒入了y，x变0
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
                    while ((preState = pre.get(preState)) != 0) {
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
