//
// Created by 罗旭维 on 2021/11/29.
//

#ifndef CPP_PROJECT_GRAPH_DFS_H
#define CPP_PROJECT_GRAPH_DFS_H
#include "../CGraph.h"
#include <vector>

class GraphDFS {
private:
    CGraph &G_;
    std::vector<int> pre_;
    std::vector<int> post_;
    std::vector<bool> visited_;
public:
    GraphDFS(CGraph &g) : G_(g) {
        visited_.reserve(G_.V());
        std::fill_n(visited_.begin(), G_.V(), false);

        for (int v = 0; v < G_.V(); v++) {
            if (!visited_[v]) {
                dfs(v);
            }
        }
    }

    void dfs(int v) {
        pre_.push_back(v);
        visited_[v] = true;

        for (auto w : G_.get_adj(v)) {
            if (!visited_[w]) {
                dfs(w);
            }
        }

        post_.push_back(v);
    }

    std::vector<int>& pre() {
        return pre_;
    }

    std::vector<int>& post() {
        return post_;
    }

};

#endif //CPP_PROJECT_GRAPH_DFS_H
