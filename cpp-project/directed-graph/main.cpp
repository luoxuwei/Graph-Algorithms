//
// Created by 罗旭维 on 2021/11/28.
//
#include "cycle_detection.h"
#include <string>
#include <iostream>
int main() {
#if 1
    std::string p("ug.txt");
    CGraph g(p, true);
    CycleDetection cycleDetection(g);
    std::cout<<cycleDetection.HasCycle()<<std::endl;

    std::string p2("ug2.txt");
    CGraph g2(p2, true);
    CycleDetection cycleDetection2(g2);
    std::cout<<cycleDetection2.HasCycle();
#endif
}