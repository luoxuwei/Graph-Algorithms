#include <iostream>
#include "CGraph.h"
#include "dfs/DFS.h"
#include "dfs-applications/BipartitionDetection.h"
int main() {
#if 0
    std::cout << "dfs\n" << std::endl;
    std::string file_name("g.txt");
    CGraph G(file_name);
    DFS dfs(G);
    dfs.dfs();
    dfs.printResult();
#endif
    std::cout << "bipartitionDetection\n" << std::endl;
    std::string file_name("g2.txt");
    CGraph G(file_name);
    BipartitionDetection bipartitionDetection(G);
    bipartitionDetection.printResult();
    return 0;
}
