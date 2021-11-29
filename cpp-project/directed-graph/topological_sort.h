//
// Created by 罗旭维 on 2021/11/29.
//

#ifndef CPP_PROJECT_TOPOLOGICAL_SORT_H
#define CPP_PROJECT_TOPOLOGICAL_SORT_H
#include "../CGraph.h"
#include <vector>
#include <queue>
#include <iostream>
class TopologicalSort {
public:
    TopologicalSort(CGraph &g) : G_(g) {
        if (!G_.isDirected()) assert(0);
        std::vector<int> indegrees;
        indegrees.reserve(G_.V());
        std::fill_n(indegrees.begin(), G_.V(), 0);
        std::queue<int> q;
        for (int v = 0; v < G_.V(); v++) {
            indegrees[v] = G_.indegree(v);
            if (indegrees[v] == 0) {
                q.push(v);
            }
        }

        while (!q.empty()) {
            int v = q.front();
            q.pop();
            res_.push_back(v);
            for (int w : G_.get_adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    q.push(w);
                }
            }
        }

        if (res_.size() != G_.V()) {
            has_cycle_ = true;
            res_.clear();
        }

    }

    void printResult() {
        for (auto v : res_) {
            std::cout<<v<<",";
        }
        std::cout<<std::endl;
    }
private:
    CGraph &G_;
    std::vector<int> res_;
    bool has_cycle_ = false;
};

#endif //CPP_PROJECT_TOPOLOGICAL_SORT_H
