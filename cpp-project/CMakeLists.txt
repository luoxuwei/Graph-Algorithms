cmake_minimum_required(VERSION 3.17)
project(cpp_project)
set( CMAKE_EXPORT_COMPILE_COMMANDS ON )
set(CMAKE_CXX_STANDARD 14)

add_executable(cpp_project main.cpp CGraph.h BipartitionDetection.h)