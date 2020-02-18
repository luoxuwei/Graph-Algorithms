# 图的深度优先遍历

**基本概念：**

1.与树的深度优先遍历相比，遍历图的时候需要注意重复遍历某个节点的问题，所以在遍历完一个节点后需要保存一下用来判断是否已经遍历过

```java
void dfs(int v) {
     visited[v] = true;
     list.add(v);
     for (int w: adj(v)) {
         if (!visited[w])
             dfs(w);
     }
 }
```