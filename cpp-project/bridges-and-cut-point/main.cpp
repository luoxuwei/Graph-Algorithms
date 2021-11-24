//
// Created by 罗旭维 on 2021/11/24.
//
#include "bridge/findbridge.h"
#include <iostream>

int main() {
#if 1
    std::string path("g.txt");
    CGraph g(path);
    FindBridge findBridge(g);
    std::cout<<"Bridges in g : ";
    findBridge.PrintResult();
    std::cout<<std::endl;

    std::string path1("g2.txt");
    CGraph g1(path1);
    FindBridge findBridge1(g1);
    std::cout<<"Bridges in g2 : ";
    findBridge1.PrintResult();
    std::cout<<std::endl;

    std::string path3("g3.txt");
    CGraph g3(path3);
    FindBridge findBridge3(g3);
    std::cout<<"Bridges in g3 : ";
    findBridge3.PrintResult();
    std::cout<<std::endl;
#endif

}