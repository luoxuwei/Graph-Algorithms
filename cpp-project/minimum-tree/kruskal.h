//
// Created by 罗旭维 on 2021/11/27.
//

#ifndef CPP_PROJECT_KRUSKAL_H
#define CPP_PROJECT_KRUSKAL_H
#include <vector>
#include <algorithm>
#include <iostream>
#include "../CGraph.h"
#include "cc.h"
#include "uf.h"

class Kruskal {
public:
    Kruskal(CWeightedGraph &g) : G_(g), cc_(g), uf_(g.V()) {
        if (cc_.Cccount() > 1) return;
        std::vector<WeightedEdge> edges;
        for (int i = 0; i < g.V(); i++) {
            for (auto w : G_.get_adj(i)) {
                if (i < w.first) {
                    edges.push_back(WeightedEdge(i, w.first, w.second));
                }
            }
        }

        std::sort(edges.begin(), edges.end(), WeightedEdgeCompartor());
        for (auto edge : edges) {
            if (!uf_.isConnected(edge.V, edge.W)) {
                res.push_back(edge);
                uf_.unionElements(edge.V, edge.W);
            }
        }
    }

    void printResult() {
        for (int i = 0; i < res.size(); ++i) {
            std::cout<<res.at(i).ToString()<<",";
        }
    }

private:
    CWeightedGraph &G_;
    CC cc_;
    UF uf_;
    std::vector<WeightedEdge> res;
};

#endif //CPP_PROJECT_KRUSKAL_H
