//
// Created by 罗旭维 on 2021/11/28.
//

#ifndef CPP_PROJECT_CYCLE_DETECTION_H
#define CPP_PROJECT_CYCLE_DETECTION_H
#include "../CGraph.h"
#include <vector>

class CycleDetection {
public:
    CycleDetection(CGraph &g) : G_(g) {
        visited_.reserve(G_.V());
        std::fill_n(visited_.begin(), G_.V(), false);
        onPath_.reserve(G_.V());
        std::fill_n(onPath_.begin(), G_.V(), false);
        for (int v = 0; v < G_.V(); v++) {
            if (!visited_[v]) {
                dfs(v);
            }
        }
    }

    void dfs(int v) {
        onPath_[v] = true;
        visited_[v] = true;

        for (int w : G_.get_adj(v)) {
            if (!visited_[w]) {
                dfs(w);
            } else if (onPath_[w]) {
                hasCycle = true;
            }
        }

        onPath_[v] = false;
    }

    bool HasCycle() {
        return hasCycle;
    }

private:
    CGraph &G_;
    std::vector<bool> visited_;
    std::vector<bool> onPath_;
    bool hasCycle = false;
};

#endif //CPP_PROJECT_CYCLE_DETECTION_H
