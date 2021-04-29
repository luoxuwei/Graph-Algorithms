# 求连通分量的个数

在深度优先遍历的基础上，在初始循环里，当遇到没有被访问过的顶点时，即表明是一个新的连通分量

````java
for (int w : g.adj(v)) {
    if (!validate[w]) {
        dfs(w);
        cccount++
    }
}
````

# 求每个连通分量包括的顶点
1.每个连通分量里的顶点保存到不同的list中，在初始循环里，遇到新的连通分量，传入新的list

```java
for (int w : g.adj(v)) {
    if (!validate[w]) {
        dfs(w, list[cccount]);
        cccount++
    }
}
```

2.利用validate数组保存顶点所在连通分量的id

```java
for (int w : g.adj(v)) {
    if (!validate[w]) {
        dfs(w, cccount);
        cccount++
    }
}

dfs(int v, int ccid) {
    validate[v] = ccid;
}
```

3.有validate数组保存的信息后，判断两个顶点是否相连就比较简单了

```java
isConnected(int v, int w) {
    return validate[v] == validate[w];
}
```

4.获取图有多少个连通分量和每个连通分量包含的顶点

```java
public ArrayList<Integer>[] components() {
    ArrayList<Integer>[] res = new ArrayList[cccount];
    for (int v : g.V()) {
        res[validate[v]].add(v);
    }
    return res;
}
```