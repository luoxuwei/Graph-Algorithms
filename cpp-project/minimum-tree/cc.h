//
// Created by 罗旭维 on 2021/11/26.
//

#ifndef CPP_PROJECT_CC_H
#define CPP_PROJECT_CC_H
#include "../CGraph.h"
#include <vector>

class CC {
public:
    CC(CWeightedGraph &g) : G_(g) {
        ccids.reserve(G_.V());
        std::fill_n(ccids.begin(), G_.V(), -1);
        for (int i = 0; i < G_.V(); i++) {
            if (ccids[i] == -1) {
                Dfs(i, cccount);
                cccount++;
            }
        }
    }

    void Dfs(int v, int id) {
        ccids[v] = id;
        for (auto w : G_.get_adj(v)) {
            if (ccids[w.first] == -1) {
                Dfs(w.first, id);
            }
        }
    }

    int Cccount() {
        return cccount;
    }

private:
    CWeightedGraph &G_;
    int cccount = 0;
    std::vector<int> ccids;
};

#endif //CPP_PROJECT_CC_H
