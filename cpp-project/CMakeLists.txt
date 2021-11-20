cmake_minimum_required(VERSION 3.17)
project(cpp_project)
set( CMAKE_EXPORT_COMPILE_COMMANDS ON )
set(CMAKE_CXX_STANDARD 14)

add_executable(cpp_project-dfs dfs/main.cpp dfs/DFS.h CGraph.h)
add_executable(cpp_project-dfs-applications
        dfs-applications/main.cpp
        dfs-applications/BipartitionDetection.h
        dfs-applications/CycleDetection.h
        dfs-applications/SingleSourcePath.h CGraph.h)

add_executable(cpp_project-bfs bfs/main.cpp bfs/BFS.h CGraph.h)