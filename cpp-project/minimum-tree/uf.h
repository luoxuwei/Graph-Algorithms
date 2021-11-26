//
// Created by 罗旭维 on 2021/11/26.
//

#ifndef CPP_PROJECT_UF_H
#define CPP_PROJECT_UF_H
#include <vector>

class UF {
public:
    UF(int n) {
        parent.reserve(n);
        rank.reserve(n);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //压缩路径
    int find(int v) {

        while (v != parent[v]) {
            parent[v] = parent[parent[v]];
            v = parent[v];
        }
        return v;
    }

    void unionElements(int v, int w) {
        int rootV = find(v);
        int rootW = find(w);

        if (rootV != rootW) {
            if (rank[rootV] < rank[rootW]) {
                parent[rootV] = rootW;
            } else if (rootW < rootV) {
                parent[rootW] = rootV;
            } else {
                parent[rootW] = rootV;
                rank[rootV] += 1;
            }
        }
    }

    bool isConnected(int v, int w) {
        return find(v) == find(w);
    }

private:
    std::vector<int> parent;
    std::vector<int> rank;
};

#endif //CPP_PROJECT_UF_H
