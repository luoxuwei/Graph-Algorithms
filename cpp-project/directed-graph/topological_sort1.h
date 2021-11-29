//
// Created by 罗旭维 on 2021/11/29.
//

#ifndef CPP_PROJECT_TOPOLOGICAL_SORT1_H
#define CPP_PROJECT_TOPOLOGICAL_SORT1_H
#include "../CGraph.h"
#include "graph_dfs.h"
#include "cycle_detection.h"
#include <vector>
#include <algorithm>

class TopologicalSort1 {
private:
    CGraph &G_;
    std::vector<int> res;

public:
    TopologicalSort1(CGraph &g) : G_(g) {
        CycleDetection cycleDetection(g);
        if (cycleDetection.HasCycle()) return;

        GraphDFS graphDfs(g);
        for (auto v : graphDfs.post()) {
            res.push_back(v);
        }

        std::reverse(res.begin(), res.end());
    }

    void printResult() {
        for (auto v : res) {
            std::cout<<v<<",";
        }
        std::cout<<std::endl;
    }
};

#endif //CPP_PROJECT_TOPOLOGICAL_SORT1_H
