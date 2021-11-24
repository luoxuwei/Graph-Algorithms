//
// Created by 罗旭维 on 2021/11/24.
//
#include "bridge/findbridge.h"
#include <iostream>
#include "cut-point/find_cut_point.h"

int main() {
#if 0
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

#if 1
    std::string path("g.txt");
    CGraph g(path);
    FindCutPoint findCutPoint(g);
    std::cout<<"Cut Points in g :";
    findCutPoint.PrintResult();
    std::cout<<std::endl;

    std::string path1("g2.txt");
    CGraph g1(path1);
    FindCutPoint findCutPoint1(g1);
    std::cout<<"Cut Points in g2 : ";
    findCutPoint1.PrintResult();
    std::cout<<std::endl;

    std::string path3("g3.txt");
    CGraph g3(path3);
    FindCutPoint findCutPoint3(g3);
    std::cout<<"Cut Points in g3 : ";
    findCutPoint3.PrintResult();
    std::cout<<std::endl;

    std::string path4("tree.txt");
    CGraph g4(path4);
    FindCutPoint findCutPoint4(g4);
    std::cout<<"Cut Points in tree : ";
    findCutPoint4.PrintResult();
    std::cout<<std::endl;
#endif

}