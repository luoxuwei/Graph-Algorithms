//
// Created by 罗旭维 on 2021/11/24.
//

#ifndef CPP_PROJECT_FINDBRIDGE_H
#define CPP_PROJECT_FINDBRIDGE_H
#include "../../CGraph.h"
#include <vector>
#include <iostream>

class FindBridge {
public:
    FindBridge(CGraph &g) : G_(g) {
        visited_.reserve(G_.V());
        std::fill_n(visited_.begin(), G_.V(), false);
        order_.reserve(G_.V());
        std::fill_n(order_.begin(), G_.V(), 0);
        low_.reserve(G_.V());
        std::fill_n(low_.begin(), G_.V(), 0);
        for (int i = 0; i < G_.V(); i++) {
            if (!visited_[i]) {
                dfs(i, i);
            }
        }
    }

    void dfs(int v, int parent) {
        cnt++;
        order_[v] = cnt;
        low_[v] = cnt;
        visited_[v] = true;
        for (auto w : G_.get_adj(v)) {
            if (!visited_[w]) {
                dfs(w, v);
                low_[v] = std::min(low_[v], low_[w]);
                if (low_[w] > order_[v]) {
                    res_.push_back(std::pair<int, int>(v, w));
                }
            } else if (w != parent) {
                low_[v] = std::min(low_[v], low_[w]);
            }
        }
    }

    void PrintResult() {
        for (auto p : res_) {
            char b[5];
            sprintf(b, "%d-%d", p.first, p.second);
            std::cout<<"["<<b<<"],";
        }
    }

private:
    CGraph &G_;
    std::vector<bool> visited_;
    std::vector<int> order_;
    std::vector<int> low_;
    std::vector<std::pair<int, int>> res_;
    int cnt = 0;
};

#endif //CPP_PROJECT_FINDBRIDGE_H
