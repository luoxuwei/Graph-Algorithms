# 图的广度优先遍历 

**概述**

1.与树的广度优先遍历一样，借助队列，将当前顶点的连接点入队，然后每次从对首取出一个顶点，循环整个过程，直到队列为空。

2.与深度优先遍历一样需要防止顶点重复遍历，需要一个保存入队状态的数组。进行广度优先遍历时是防止重复入队。

3.一样为了处理图中有多个连通分量的情况，在初始时需要一个循环遍历每个顶点。

**复杂度**

与深度优先遍历一样是O(V + E)

**图的广度优先遍历的应用**

1.单源路径，与深度优先遍历一样，每遍历一个顶点都保存一下这个顶点的上一个顶点

```java
private void bfs(int s) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(s);
        validate[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            order.add(cur);
            for (int w:g.adj(cur)) {
                if (!validate[w]) {
                    queue.addLast(w);
                    pre[w] = cur;
                    validate[w] = true;
                }
            }
        }
    }

```


与深度优先遍历取得的路径相比，广度优先遍历的路径更短，而且通过广度优先遍历取得的路径时源顶点与图中任意一点的最短路径。可以结合树的广度优先遍历（层序）来理解，就是根据离顶点的远近开始遍历，先遍历最近的开始，依次向越来越远的顶点遍历，后遍历的点与源点的距离一定是大于等于先遍历的点。这个性质就保证，在遍历的过程中，来到一个点就是最早可以来到这个点的时刻。显然这个性质只适用于无权图。


**深度和广度优先遍历对比**

1.深度优先遍历的非递归实现

```java
private void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        stack.add(v);
        validate[v] = true;
        while (!stack.isEmpty()) {
            int cur = stack.remove();
            order.add(cur);
            for (int w:g.adj(cur)) {
                if (!validate[w]) {
                    stack.add(w);
                    validate[w] = true;
                }
            }
        }
    }
```
与广度优先遍历对比，代码几乎一样，唯一的差异是适用的集合不同，深度优先用的是栈，广度优先用的是队列。可以看出对图进行遍历，用这套逻辑框架就可以搞定了，其中使用的集合，可以是任一类型的集合，其中的区别只是存取元素的顺序不同而已，甚至可以是一个随机取元素的集合。