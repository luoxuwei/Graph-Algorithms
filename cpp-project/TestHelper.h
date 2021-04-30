//
// Created by Xuwei Luo 罗旭维 on 2021/4/29.
//

#ifndef CPP_PROJECT_TESTHELPER_H
#define CPP_PROJECT_TESTHELPER_H
#include <vector>
namespace TestHelper {

    template<typename T>
    void printVector(vector<T>& vector) {
        for (T t:vector) {
            std::cout<<t<<" ";
        }
        std::cout<<std::endl;
    }

}
#endif //CPP_PROJECT_TESTHELPER_H
