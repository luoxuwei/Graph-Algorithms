#include <iostream>
#include "../CGraph.h"
#include "BipartitionDetection.h"
#include "CycleDetection.h"
#include "SingleSourcePath.h"
#include "../TestHelper.h"
int main() {
#if 0
    std::cout << "bipartitionDetection\n" << std::endl;
    std::string file_name("g2.txt");
    CGraph G(file_name);
    BipartitionDetection bipartitionDetection(G);
    bipartitionDetection.printResult();
#endif
#if 0
    std::cout<<"CycleDetection\n"<<std::endl;
    std::string parent("g2.txt");
    CGraph g(parent);
    CycleDetection cycleDetection(g);
    cycleDetection.printResult();
    std::string path2("g3.txt");
    CGraph g2(path2);
    CycleDetection cycleDetection2(g2);
    cycleDetection2.printResult();
#endif
#if 1
    std::cout<<"Single Source parent\n"<<std::endl;
    std::string path("g4.txt");
    CGraph g(path);
    SingleSourcePath singleSourcePath(g, 0);
    std::cout<<g.to_string()<<"\n"<<std::endl;
    std::cout<<"0 -> 6 : ";
    vector<int> r;
    singleSourcePath.path(r, 6);
    TestHelper::printVector(r);
    std::cout<<"0 -> 5 : ";
    vector<int> r1;
    singleSourcePath.path(r1, 5);
    TestHelper::printVector(r1);
#endif
    return 0;
}
