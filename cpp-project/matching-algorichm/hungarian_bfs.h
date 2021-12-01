//
// Created by 罗旭维 on 2021/12/1.
//

#ifndef CPP_PROJECT_HUNGARIAN_BFS_H
#define CPP_PROJECT_HUNGARIAN_BFS_H
#include <queue>
#include <vector>
#include "../CGraph.h"
#include "../dfs-applications/BipartitionDetection.h"

using namespace std;
class HungarianBFS {
public:
    HungarianBFS(CGraph &g) : G_(g) {
        BipartitionDetection bipartitionDetection(g);
        if (!bipartitionDetection.IsBipartition()) throw "";

        vector<int> &colors = bipartitionDetection.Colors();
        matching.reserve(G_.V());
        std::fill_n(matching.begin(), G_.V(), -1);
        for (int v = 0; v < G_.V(); v++) {
            if (colors[v] == 0 && matching[v] == -1) {
                if (bfs(v)) max_matching_++;
            }
        }
    }

    bool bfs(int v) {
        queue<int> q;
        q.push(v);
        vector<int> pre;
        pre.reserve(G_.V());
        std::fill_n(pre.begin(), G_.V(), -1);
        int cur;
        while (!q.empty()) {
            cur = q.front();
            q.pop();
            for (auto w : G_.get_adj(v)) {
                if (pre[w] == -1) {
                    if (matching[w] != -1) {
                        q.push(matching[w]);
                        pre[w] = cur;
                        pre[matching[w]] = w;
                    } else {
                        pre[w] = cur;
                        vector<int> path = getAugPath(pre, v, w);
                        for (int i = 0; i < path.size(); i = i + 2) {
                            matching[path[i]] = path[i+1];
                            matching[path[i+1]] = path[i];
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    vector<int> getAugPath(vector<int> &pre, int start, int end) {
        int cur = end;
        vector<int> res;
        res.push_back(cur);
        while (pre[cur] != start) {
            cur = pre[cur];
            res.push_back(cur);
        }
        res.push_back(start);
        return res;
    }

    int maxMatching(){
        return max_matching_;
    }

private:
    CGraph &G_;
    int max_matching_ = 0;
    vector<int> matching;
};

#endif //CPP_PROJECT_HUNGARIAN_BFS_H
