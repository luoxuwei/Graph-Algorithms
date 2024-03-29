//
// Created by Xuwei Luo 罗旭维 on 2021/4/22.
//

#ifndef CPP_PROJECT_GRAPH_H
#define CPP_PROJECT_GRAPH_H
#include <vector>
#include <unordered_set>
#include <set>
#include <map>
#include <fstream>
#include <sstream>
#include <assert.h>
class CGraph {
private:
    std::vector<std::set<int>> adj;
    int v, e;
    bool directed_;
    std::vector<int> indegrees_, outdegrees_;
public:
    CGraph(std::string& filename, bool directed = false) : directed_(directed) {
        std::ifstream file(filename);
        std::string line;

        assert(file.is_open());

        //读第一行是图的点和边的数量
        assert(getline(file, line));
        std::stringstream ss(line);
        ss>>v>>e;
        indegrees_.reserve(v);
        outdegrees_.reserve(v);
        std::fill_n(indegrees_.begin(), v, 0);
        std::fill_n(outdegrees_.begin(), v, 0);

        adj = std::vector<std::set<int>>(v, std::set<int>());
        for (int i=0; i<e; i++) {
            assert(getline(file, line));
            int a,b;
            std::stringstream ss(line);
            ss>>a>>b;

            if (adj.at(a).find(b) == adj.at(a).end()) {
                adj.at(a).insert(b);
            }
            indegrees_[b]++;
            outdegrees_[a]++;
            if (!directed_) {
                if (adj.at(b).find(a) == adj.at(b).end()) {
                    adj.at(b).insert(a);
                }
            }
        }
    }

    bool isDirected(){
        return directed_;
    }

    int V() {return v;}

    int E() {return e;}

    const std::set<int>& get_adj(int v) {
        return adj.at(v);
    }

    int degree(int v){
        if(!directed_) throw "degree only works in undirected graph.";

        return adj[v].size();
    }

    int indegree(int v){
        if(!directed_) throw "indegree only works in directed graph.";
        validateVertex(v);
        return indegrees_[v];
    }

    int outdegree(int v){
        if(!directed_) throw "outdegree only works in directed graph.";
        validateVertex(v);
        return outdegrees_[v];
    }

    void validateVertex(int v) {
        if (v < 0 || v >= V())
            throw "";
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

class CWeightedGraph {
private:
    std::vector<std::map<int, int>> adj;
    int v, e;
    bool directed_;
public:
    CWeightedGraph(std::string& filename, bool directed = false) : directed_(directed) {
        std::ifstream file(filename);
        std::string line;

        assert(file.is_open());

        //读第一行是图的点和边的数量
        assert(getline(file, line));
        std::stringstream ss(line);
        ss>>v>>e;
        adj = std::vector<std::map<int, int>>(v, std::map<int, int>());
        for (int i=0; i<e; i++) {
            assert(getline(file, line));
            int a,b,w;
            std::stringstream ss(line);
            ss>>a>>b>>w;

            if (adj.at(a).find(b) == adj.at(a).end()) {
                adj.at(a)[b] = w;
            }

            if (!directed_) {
                if (adj.at(b).find(a) == adj.at(b).end()) {
                    adj.at(b)[a] = w;
                }
            }
        }
    }

    CWeightedGraph(int v, bool directed) : v(v), directed_(directed) {
        e = 0;
        adj = std::vector<std::map<int, int>>(v, std::map<int, int>());
    }

    void AddEdge(int v, int w, int weight) {
        validateVertex(v);
        validateVertex(w);
        if (v == w) throw "";//自环边
        adj[v][w]=weight;
        if (!directed_) {
            adj[w][v] = weight;
        }
        e++;
    }

    void SetWeight(int v, int w, int weight) {
        if (!hasEdge(v, w)) throw "";

        adj[v][w]=weight;
        if (!directed_) {
            adj[w][v] = weight;
        }
    }

    bool isDirected(){
        return directed_;
    }

    int V() {return v;}

    int E() {return e;}

    const std::map<int, int> & get_adj(int v) {
        return adj.at(v);
    }

    int degree(int v) {
        return adj.at(v).size();
    }

    int getWeight(int v, int w) {
        if (hasEdge(v, w)) return adj.at(v)[w];
        throw 0;
    }

    bool hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj.at(v).find(w) != adj.at(v).end();
    }

    void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj.at(v).find(w) != adj.at(v).end()) e--;
        adj.at(v).erase(w);
        if (directed_) {
            adj.at(w).erase(v);
        }
    }

    void validateVertex(int v) {
        if (v < 0 || v >= V())
            throw "";
    }

    std::string to_string() {
        std::stringstream  ss;
        ss<<"V="<<v<<",E="<<e<<"\n";
        for (int i=0; i<v; i++) {
            ss<<i<<":";
            for (auto w:adj.at(i)) {
                ss<<w.first<<"-"<<w.second;
            }
            ss<<"\n";
        }
        return ss.str();
    }

};

struct WeightedEdge {
    int V;
    int W;
    int Weight;
    WeightedEdge(int v, int w, int weight) {
        V = v;
        W = w;
        Weight = weight;
    }

    std::string ToString() {
        std::stringstream ss;
        ss<<"("<<V<<"-"<<W<<":"<<Weight<<")";
        return ss.str();
    }

    bool operator>(const WeightedEdge &e) const {
        return Weight > e.Weight;
    }

    bool operator<(const WeightedEdge &e) const {
        return Weight < e.Weight;
    }
};

struct WeightedEdgeCompartor {
    bool operator()(const WeightedEdge &a, const WeightedEdge &b) {
        return a.Weight < b.Weight;
    }
};



#endif //CPP_PROJECT_GRAPH_H
