//
// Created by 罗旭维 on 2021/11/20.
//

#include "leetcode200.h"
#include "leetcode1020.h"
#include <vector>
#include <iostream>

int main() {
#if 0
    Solution200 solution200;
    std::vector<std::vector<char>> grid = {{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
    std::cout<<solution200.numIslands(grid)<<std::endl;
#endif
    Solution1020 solution;
    std::vector<std::vector<int>> grid = {{0,0,0,0}, {1,0,1,0}, {0,1,1,0}, {0,0,0,0}};
    std::cout<<solution.numEnclaves(grid)<<std::endl;
#if 1
#endif
}