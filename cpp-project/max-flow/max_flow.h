//
// Created by 罗旭维 on 2021/11/30.
//

#ifndef CPP_PROJECT_MAX_FLOW_H
#define CPP_PROJECT_MAX_FLOW_H
#include "../CGraph.h"
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>

class MaxFlow {
private:
    CWeightedGraph &G_;//原图
    CWeightedGraph *rG_;//残量图
    int s = 0;
    int t = 0;
    int max_flow_ = 0;
public:
    MaxFlow(CWeightedGraph &g, int s, int t) : G_(g), s(s), t(t) {
        rG_ = new CWeightedGraph(G_.V(), true);
        if (s == t) throw "";
        for (int v = 0; v < G_.V(); v++) {
            for (auto  w : G_.get_adj(v)) {
                rG_->AddEdge(v, w.first, w.second);
                rG_->AddEdge(w.first, v, 0);
            }
        }

        while (true) {
            std::vector<int> path = getAugmentingPath();
            if (path.size() <= 1) return;
            int f = INT32_MAX;
            for (int i = 1; i < path.size(); i++) {
                if (rG_->getWeight(path[i-1], path[i]) < f) {
                    f = rG_->getWeight(path[i-1], path[i]);
                }
            }
            max_flow_ += f;
            for (int i = 1; i < path.size(); i++) {
                if (G_.hasEdge(path[i-1], path[i])) {
                    rG_->SetWeight(path[i-1], path[i], rG_->getWeight(path[i-1], path[i]) - f);
                    rG_->SetWeight(path[i], path[i-1], rG_->getWeight(path[i], path[i-1]) + f);
                } else {
                    rG_->SetWeight(path[i-1], path[i], rG_->getWeight(path[i-1], path[i]) - f);
                    rG_->SetWeight(path[i], path[i-1], rG_->getWeight(path[i], path[i-1]) + f);
                }
            }

        }

    }

    std::vector<int> getAugmentingPath() {
        std::vector<int> res;
        std::queue<int> q;
        std::vector<bool> visited;
        visited.reserve(G_.V());
        std::fill_n(visited.begin(), G_.V(), false);
        q.push(s);
        visited[s] = true;
        std::vector<int> pre;
        pre.reserve(G_.V());
        std::fill_n(pre.begin(), G_.V(), -1);
        while (!q.empty()) {
            int cur = q.front();
            q.pop();
            if (cur == t) break;
            for (auto w : rG_->get_adj(cur)) {
                if (pre[w.first] == -1 && w.second > 0) {//要加上pre[w.first] == -1的条件限制，不然后面遍历路径时会陷入死循环
                    q.push(w.first);
                    visited[w.first] = true;
                    pre[w.first] = cur;
                }
            }
        }

        if (pre[t] == -1) return res;
        int cur = t;
        res.push_back(t);
        while (cur != s) {
            cur = pre[cur];
            res.push_back(cur);
        }
        std::reverse(res.begin(), res.end());
        return res;
    }

    int flow(int v, int w) {
        if (!G_.hasEdge(v, w)) throw "";
        return rG_->getWeight(w, v);
    }

    void printResult() {
        std::cout<<max_flow_<<std::endl;
        for (int i = 0; i < G_.V(); i++) {
            for (auto w : G_.get_adj(i)) {
                std::cout<<i<<"-"<<w.first<<":"<< flow(i, w.first)<<" / "<<w.second<< std::endl;
            }
        }
    }

    ~MaxFlow() {
        delete rG_;
    }

};
#endif //CPP_PROJECT_MAX_FLOW_H
