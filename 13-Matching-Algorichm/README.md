# 最大匹配和完全匹配

1.无向二分图，可以建模很多匹配问题。比如相亲问题，一边是男士，一边是女士，图中的边表示的是这名男士和这名女士的条件是匹配的。最多能让多少对匹配成功了？再比如，选导师可以也用匹配问题建模，一边是学生，一边是导师，图中的边表示学生和导师是匹配的。我们最终能让多少个学生选中导师。匹配的问题实际上是在选边，一个顶点不能被选择两次这也是匹配问题约束的限制。我们选择的所有的边他们的端点都应该是不同的。在这种情况下，我们可以理解所谓的最大匹配就是我们最多可以选多少条边。因为每一条边就相当于完成了一组匹配。但是满足最大匹配的匹配方式可以有多种。通常我们在解决最大匹配问题的时候，不去考虑不同的匹配方案，我们最关系的是那个最大匹配的数字。

2.最大的匹配能把所有的顶点都匹配上的方案叫完全匹配。完全匹配一定是一个最大匹配，最大匹配不一定是完全匹配。一旦我们解决了最大匹配问题之后，解决完全匹配其实是简单的，我们只需要看求解出来的最大匹配的解是否匹配上了图中所有的点，如果匹配上了所有的点，这个最大匹配一定是完全匹配，反之，对于我们这个图来说是不存在最大匹配。所以核心在于寻找最大匹配。

