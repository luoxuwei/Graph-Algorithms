//
// Created by 罗旭维 on 2021/11/26.
//
#include "cc.h"
#include "kruskal.h"
#include "prim.h"
int main() {
#if 0
    std::string path("g.txt");
    CWeightedGraph g(path);
    Kruskal kruskal(g);
    kruskal.printResult();
#endif

#if 1
    std::string path("g1.txt");
    CWeightedGraph g(path);
    Prim prim(g);
    prim.printResult();
#endif
}