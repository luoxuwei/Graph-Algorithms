//
// Created by 罗旭维 on 2021/11/26.
//
#include "cc.h"
#include "kruskal.h"
int main() {
    std::string path("g.txt");
    CWeightedGraph g(path);
    Kruskal kruskal(g);
    kruskal.printResult();
}