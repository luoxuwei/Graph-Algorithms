# 有向图的强连通分量

1.连通分量的概念在有向图中是有变化的，连通分量是图中所有的顶点都相互可达的，但在有向图中，顶点相互连接了也不一定能相互到达，很直观的是
入度为0的顶点就是不可达的顶点。 

2.正因如此，在有向图中我们需要定义强连通分量的概念。在一个强连通分量中，任何两点都可达。入度为0，或出度为0的点，都是单独的强连通分量，因为不能满足
任意两点都相互可达。

3.将有向图中所有的强连通分量看做一个点，某个强连通中的点可以到达另外一个连通分量，就可以在这两个连通分量间连一条边，可以把有向图规约成一个新的有向图。
这样的一个新的有向图，两点间可能有多条边，因为，一个强连通分量中有多个点，这多个点都有可能到达另外一个连通分量中多个点，所以边数不只一个。

4.这个新的图有一个特点，就是这样一个新的图，一定是DAG。这一点，可以反证，假如有环的话，这意味着从形成环的着几个连通分量中的点，其实都可以到达
任意其他连通分量中的点。那这几个连通分量就应该是一个连通分量。这里就出现了矛盾。

5.由于这个新的图是DAG，所以一定能进行拓扑排序，我们按照这个新的图拓扑排序的逆序，一个区域一个区域去遍历原来的图中的这些点，就得到了一个一个的强
连通分量。按照拓扑排序的逆序去遍历，强连通分量中的点是相互可达的所以可以都遍历到，但由于是拓扑排序的逆序，所以这个强连通分量中的点，不能反向，走到其他连通分量中。
对于已经遍历完的强连通分量，我们可以不用再遍历，所以我们只能往回退到拓扑排序的逆序的下一个点（强连通分量），这样就能得到一个个强连通分量。

6.由于这个新的图是我们构想出来的，我们并不知道都有哪些强连通分量，那么对于原图来说，我们有没有可能找到，这个构想中的图中的拓扑排序逆序的强连通分量中的点？然后用dfs把
它遍历完，再找到下一个强连通分量中的某个点。以此类推，我们可不可以，在原来的这个图中找到，这个构想中以强连通分量为点的新图的拓扑排序逆序的点的顺序了？

7.这是我们要解决的核心问题，一个直观的解法，就是我们能不能对原先的图的后序遍历的顺序来找到一个一个强连通分量了。这个想法其实是错误的。因为对原图的
后序遍历的顺序，与我们以连通分量为点的新图的拓扑排序后序顺序不一定一样。

8.我们需要对原图做反图，把每条边反过来，对这个反图做后序遍历，这个后序遍历的逆，就是我们想要的结果

9.为了证明，假设我们有一个强连通分量，过这个强连通分量中的任意一点可以到达某个点V，我们对这个图做反图，就变成v有一条边到达这个强连通分量。现在对这个
反图做后序遍历的话，这个遍历的结果，v这个点一定相较于这个强连通分量中的点后出现。因为，如果我们后序遍历是从强连通分量中的某一个点开始进行的，那么这个
遍历过程一定就在这个强连通分量中转，不会来到v这个点，这是因为现在v这条边是指向强连通分量的。强连通分量中没有点再指向v了。我们一定会先把这个强连通分量
中的点都遍历完了，发现这个点落单了，我们再去看它，那么在这种情况下，v这个点一定出现在强连通分量中的点后面。如果后序遍历是从v出发的，一上来我们马上会进入
这个强连通分量，进入到强连通分量后，肯定会先遍历完强连通分量中的点后，才会回到v，这种情况，v这个点也会出现在强连通分量中所有点后面。

10.这个算法叫Kosaraju算法 