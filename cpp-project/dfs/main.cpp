#include <iostream>
#include "../CGraph.h"
#include "DFS.h"
int main() {
    std::cout << "dfs\n" << std::endl;
    std::string file_name("g.txt");
    CGraph G(file_name);
    DFS dfs(G);
    dfs.dfs();
    dfs.printResult();
    return 0;
}
