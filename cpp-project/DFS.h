//
// Created by Xuwei Luo 罗旭维 on 2021/4/26.
//

#ifndef CPP_PROJECT_DFS_H
#define CPP_PROJECT_DFS_H
#include "CGraph.h"
#include <vector>
#include <stdio.h>

class DFS {
private:
    CGraph& G;
    std::vector<bool> validate;
    std::vector<int> order;
public:
    DFS(CGraph& g):G(g) {
        validate.reserve(G.V());
    }

    void dfs() {
        for(int i=0; i<G.V(); i++) {
            if(!validate[i]) {
                dfs(i);
            }
        }
    }

    void dfs(int v) {
        order.push_back(v);
        validate[v] = true;
        for(int w:G.get_adj(v)) {
            if (!validate[w])
                dfs(w);
        }
    }

    std::vector<int>& getOrder() {
        return order;
    }

    void printResult() {
        std::cout<<G.to_string()<<"\n";
        for (int v:order) {
            std::cout<<v<<" ";
        }
        std::cout<<std::endl;
    }
};
#endif //CPP_PROJECT_DFS_H
