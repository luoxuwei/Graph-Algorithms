#include <iostream>
#include "../CGraph.h"
#include "BipartitionDetection.h"
#include "CycleDetection.h"
int main() {
#if 0
    std::cout << "bipartitionDetection\n" << std::endl;
    std::string file_name("g2.txt");
    CGraph G(file_name);
    BipartitionDetection bipartitionDetection(G);
    bipartitionDetection.printResult();
#endif
#if 1
    std::cout<<"CycleDetection\n"<<std::endl;
    std::string path("g2.txt");
    CGraph g(path);
    CycleDetection cycleDetection(g);
    cycleDetection.printResult();
    std::string path2("g3.txt");
    CGraph g2(path2);
    CycleDetection cycleDetection2(g2);
    cycleDetection2.printResult();
#endif
    return 0;
}
