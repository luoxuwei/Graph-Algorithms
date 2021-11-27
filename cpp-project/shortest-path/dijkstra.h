//
// Created by 罗旭维 on 2021/11/27.
//

#ifndef CPP_PROJECT_DIJKSTRA_H
#define CPP_PROJECT_DIJKSTRA_H
#include "../CGraph.h"
#include <vector>
#include <queue>
#include <iostream>

class Dijkstra {
    struct Node {
        Node(int v, int dis) : v_(v), dis_(dis) {

        }

        bool operator>(const Node &other) const {
            return dis_ > other.dis_;
        }
        int v_;
        int dis_;
    };
public:
    Dijkstra(CWeightedGraph &g, int s) : G_(g), s_(s) {
        dis_.reserve(G_.V());
        std::fill_n(dis_.begin(), G_.V(), INT32_MAX);
        visited_.reserve(G_.V());
        std::fill_n(visited_.begin(), G_.V(), false);
        std::priority_queue<int, std::vector<Node>, std::greater<Node>> pq;
        dis_[s] = 0;
        pq.push(Node(s, 0));
        Node cur(0, 0);
        while (!pq.empty()) {
            cur = pq.top();
            pq.pop();
            visited_[cur.v_] = true;
            for (auto w : G_.get_adj(cur.v_)) {
                if (!visited_[w.first] && (cur.dis_ + w.second < dis_[w.first])) {
                    dis_[w.first] = cur.dis_ + w.second;
                    pq.push(Node(w.first, dis_[w.first]));
                }
            }
        }
    }

    int distTo(int v) {
        return dis_[v];
    }

    void printResult() {
        for (int v = 0; v < G_.V(); v++) {
            std::cout<<distTo(v)<<" ";
        }
    }

private:
    CWeightedGraph &G_;
    int s_;
    std::vector<int> dis_;
    std::vector<bool> visited_;
};

#endif //CPP_PROJECT_DIJKSTRA_H
