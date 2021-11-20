//
// Created by Xuwei Luo 罗旭维 on 2021/4/22.
//

#ifndef CPP_PROJECT_GRAPH_H
#define CPP_PROJECT_GRAPH_H
#include <vector>
#include <unordered_set>
#include <set>
#include <fstream>
#include <sstream>
#include <assert.h>
class CGraph {
private:
    std::vector<std::set<int>> adj;
    int v, e;
public:
    CGraph(std::string& filename) {
        std::ifstream file(filename);
        std::string line;

        assert(file.is_open());

        //读第一行是图的点和边的数量
        assert(getline(file, line));
        std::stringstream ss(line);
        ss>>v>>e;
        adj = std::vector<std::set<int>>(v, std::set<int>());
        for (int i=0; i<e; i++) {
            assert(getline(file, line));
            int a,b;
            std::stringstream ss(line);
            ss>>a>>b;

            if (adj.at(a).find(b) == adj.at(a).end()) {
                adj.at(a).insert(b);
            }

            if (adj.at(b).find(a) == adj.at(b).end()) {
                adj.at(b).insert(a);
            }
        }
    }

    int V() {return v;}

    int E() {return e;}

    const std::set<int>& get_adj(int v) {
        return adj.at(v);
    }

    std::string to_string() {
        std::stringstream  ss;
        ss<<"V="<<v<<",E="<<e<<"\n";
        for (int i=0; i<v; i++) {
            ss<<i<<":";
            for (int w:adj.at(i)) {
                ss<<w<<" ";
            }
            ss<<"\n";
        }
        return ss.str();
    }

};

#endif //CPP_PROJECT_GRAPH_H
