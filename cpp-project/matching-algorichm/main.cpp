//
// Created by 罗旭维 on 2021/12/1.
//
#include <iostream>
#include <string>
#include "hungarian_bfs.h"
int main() {
    std::string p("g.txt");
    CGraph g(p);
    HungarianBFS hungarianBfs(g);
    std::cout<<hungarianBfs.maxMatching()<<std::endl;

    std::string p2("g2.txt");
    CGraph g2(p2);
    HungarianBFS hungarianBfs2(g2);
    std::cout<<hungarianBfs2.maxMatching()<<std::endl;
}