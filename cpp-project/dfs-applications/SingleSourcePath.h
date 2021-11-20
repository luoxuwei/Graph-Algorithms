//
// Created by Xuwei Luo 罗旭维 on 2021/4/29.
//

#ifndef CPP_PROJECT_SINGLESOURCEPATH_H
#define CPP_PROJECT_SINGLESOURCEPATH_H
#include "../CGraph.h"
#include <vector>
#include <algorithm>
class SingleSourcePath {
private:
    CGraph& G;
    vector<int> parent;
    int s;
public:
    SingleSourcePath(CGraph& g, int s): G(g),s(s) {
        parent.reserve(G.V());
        std::fill_n(parent.begin(), G.V(), -1);
        dfs(s, s);
    };

    void dfs(int v, int p) {
        parent[v] = p;
        for (int w:G.get_adj(v)) {
            if (parent[w] == -1) {
                dfs(w, v);
            }
        }
    }

    bool isConnectedTo(int t) {
        return parent[t] != -1;
    }

    void path(vector<int>& result, int t) {
        if (!isConnectedTo(t)) return;
        int cur = t;
        while(cur != s) {
            result.push_back(cur);
            cur = parent[cur];
        }
        result.push_back(s);
        std::reverse(result.begin(), result.end());
    }

};

#endif //CPP_PROJECT_SINGLESOURCEPATH_H
