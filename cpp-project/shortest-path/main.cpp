//
// Created by 罗旭维 on 2021/11/27.
//
#include <string>
#include "dijkstra.h"

int main() {
#if 1
    std::string path("g.txt");
    CWeightedGraph g(path);
    Dijkstra dijkstra(g, 0);
    dijkstra.printResult();
#endif
}