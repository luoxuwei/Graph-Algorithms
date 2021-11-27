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

add_executable(cpp_project_modelling_and_floodfill
        Graph-Modelling-and-Floodfill/leetcode200.h
        Graph-Modelling-and-Floodfill/main.cpp
        Graph-Modelling-and-Floodfill/leetcode1020.h)

add_executable(leetcode-bfs leetcode-bfs/main.cpp CGraph.h leetcode-bfs/leetcode_773.h)
add_executable(bridges-and-cut-point CGraph.h bridges-and-cut-point/bridge/findbridge.h bridges-and-cut-point/main.cpp bridges-and-cut-point/cut-point/find_cut_point.h)
add_executable(minimum-tree minimum-tree/main.cpp minimum-tree/cc.h minimum-tree/uf.h minimum-tree/kruskal.h minimum-tree/prim.h)

add_executable(shortest-path shortest-path/main.cpp shortest-path/dijkstra.h)