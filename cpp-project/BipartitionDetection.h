//
// Created by Xuwei Luo 罗旭维 on 2021/4/28.
//

#ifndef CPP_PROJECT_BIPARTITIONDETECTION_H
#define CPP_PROJECT_BIPARTITIONDETECTION_H
#include "CGraph.h"
#include <vector>
using namespace std;
class BipartitionDetection {
private:
    CGraph& G;
    vector<int> colors;
    vector<bool> validate;
    bool isBipartition;

public:
    BipartitionDetection(CGraph& g): G(g) {
        colors.reserve(G.V());
        fill(colors.begin(), colors.end(), -1);
        validate.reserve(G.V());
        fill(validate.begin(), validate.end(), false);
        isBipartition = true;
        for (int v=0; v<G.V(); v++) {
            if (!validate[v]) {
                if (!dfs(v, 1)) {
                    isBipartition = false;
                    break;
                }
            }
        }

    }

    bool dfs(int v, int color) {
        colors[v] = color;
        validate[v] = true;
        for (int w:G.get_adj(v)) {
            if (validate[w]) {
                if (colors[v] == colors[w]) {
                    return false;
                }
            } else {
                if (!dfs(w, 1-color)) return false;
            }
        }
        return true;
    }

    void printResult() {
        std::cout<<G.to_string()<<"\n";
        std::cout<<"is bipartition: "<<isBipartition;
        std::cout<<std::endl;
    }

};

#endif //CPP_PROJECT_BIPARTITIONDETECTION_H
