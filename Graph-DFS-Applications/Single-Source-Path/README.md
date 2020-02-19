# 单源路径

1.在深度优先遍历的基础上，用一个数组保存遍历到的节点的上一个节点。求单源路径时不需要把图的每个节点都遍历到，所以遍历开始时不用循环各个节点，直接从源节点开始就可以了。

2.以源节点开始进行深度遍历后，求某个节点是否与源节点相连，只需通过validate数组判断是否遍历过就可以。

3.求路径时，先判断是否连接，如果是连接的顶点，只需以该顶点为起点在保存前一个顶点的数组里回溯到源顶点，就可以求出路径。

4.如果只求一个路径，可以在遍历到某个节点时，判断是否是目标顶点，是就提前返回。虽然可以提前返回，单是算法复杂度没变，最坏的情况还是要遍历每个顶点。