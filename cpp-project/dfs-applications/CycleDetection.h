//
// Created by Xuwei Luo 罗旭维 on 2021/4/29.
//

#ifndef CPP_PROJECT_CYCLEDETECTION_H
#define CPP_PROJECT_CYCLEDETECTION_H
#include "../CGraph.h"
#include <vector>
class CycleDetection {
private:
    CGraph& G;
    vector<bool> validate;
    bool hasCycle;
public:
    CycleDetection(CGraph& g):G(g) {
        validate.reserve(G.V());
        std::fill(validate.begin(), validate.end(), false);
        hasCycle = false;
        for (int v=0; v<G.V(); v++) {
            if (!validate[v]) {
                dfs(v, v);
            }
        }
    }

    void dfs(int v, int parent) {
        validate[v] = true;
        for(int w:G.get_adj(v)) {
            if (!validate[w]) {
                dfs(w, v);
            } else if (w != parent) {
                hasCycle = true;
            }
        }
    }

    void printResult() {
        std::cout<<"has cycle "<<hasCycle<<std::endl;
    }

};

#endif //CPP_PROJECT_CYCLEDETECTION_H
