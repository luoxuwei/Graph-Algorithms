//
// Created by 罗旭维 on 2021/11/27.
//

#ifndef CPP_PROJECT_PRIM_H
#define CPP_PROJECT_PRIM_H
#include <queue>
#include <vector>
#include "../CGraph.h"
#include <functional>
class Prim {
public:
    Prim(CWeightedGraph &g) : G_(g) {
        std::priority_queue<WeightedEdge, std::vector<WeightedEdge>, std::greater<WeightedEdge>> pqueue;
        visited_.reserve(G_.V());
        std::fill_n(visited_.begin(), G_.V(), false);
        for (auto w : G_.get_adj(0)) {
            pqueue.push(WeightedEdge(0, w.first, w.second));
        }
        visited_[0] = true;
        WeightedEdge edge(0, 0, 0);
        while (!pqueue.empty()) {
            edge = pqueue.top();
            pqueue.pop();
            if (!visited_[edge.W]) {
                res_.push_back(edge);
                visited_[edge.W] = true;
                for (auto w : G_.get_adj(edge.W)) {
                    pqueue.push(WeightedEdge(edge.W, w.first, w.second));
                }
            }
        }

    }

    void printResult() {
        for (auto w : res_) {
            std::cout<<w.ToString()<<",";
        }
    }

private:
    CWeightedGraph &G_;
    std::vector<WeightedEdge> res_;
    std::vector<bool> visited_;
};

#endif //CPP_PROJECT_PRIM_H
