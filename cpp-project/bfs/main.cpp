//
// Created by 罗旭维 on 2021/11/20.
//
#include "BFS.h"
#include <string>
int main() {
    std::string f("g.txt");
    CGraph G(f);
    BFS bfs(G);
    bfs.printResult();
}