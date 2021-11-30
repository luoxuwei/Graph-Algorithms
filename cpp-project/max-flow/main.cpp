//
// Created by 罗旭维 on 2021/11/30.
//
#include <string>
#include "../CGraph.h"
#include "max_flow.h"

int main() {
    std::string p("network.txt");
    CWeightedGraph g(p, true);
    MaxFlow maxFlow(g, 0, 3);
    maxFlow.printResult();

    std::string p2("network2.txt");
    CWeightedGraph g2(p2, true);
    MaxFlow maxFlow2(g2, 0, 5);
    maxFlow2.printResult();
}