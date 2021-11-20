//
// Created by 罗旭维 on 2021/11/20.
//

#ifndef CPP_PROJECT_BFS_H
#define CPP_PROJECT_BFS_H
#include "../CGraph.h"
#include <vector>
#include <queue>
#include <iostream>

class BFS {
public:
    BFS(CGraph &g) : G_(g) {
        visited_.reserve(g.V());
        std::fill_n(visited_.begin(), G_.V(), false);
        for (int i = 0; i < G_.V(); i++) {
            if (!visited_[i]) {
                bfs(i);
            }
        }
    }

    void bfs(int v) {
        std::queue<int> queue;
        queue.push(0);
        visited_[0] = true;
        while (!queue.empty()) {
            int cur = queue.front();
            order_.push_back(cur);
            queue.pop();
            for ( auto i : G_.get_adj(cur)) {
                if (!visited_[i]) {
                    visited_[i] = true;
                    queue.push(i);
                }
            }
        }
    }

    std::vector<int> &Order() {
        return order_;
    }

    void printResult() {
        std::cout<<G_.to_string()<<"\n";
        for (int v:order_) {
            std::cout<<v<<" ";
        }
        std::cout<<std::endl;
    }

private:
    CGraph &G_;
    std::vector<bool> visited_;
    std::vector<int> order_;
};

#endif //CPP_PROJECT_BFS_H
