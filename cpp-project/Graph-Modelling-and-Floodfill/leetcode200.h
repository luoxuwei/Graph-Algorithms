//
// Created by 罗旭维 on 2021/11/20.
//

#ifndef CPP_PROJECT_LEETCODE200_H
#define CPP_PROJECT_LEETCODE200_H
#include <queue>
#include <vector>

using namespace std;
class Solution200 {
public:
    int numIslands(vector<vector<char>>& grid) {
        std::vector<std::vector<int>> dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int R = grid.size();
        int C = grid[0].size();
        int r = 0, nr = 0, c = 0, nc = 0;
        int cur;
        int result = 0;
        for (int i = 0; i < R*C; i++) {
            if (grid[i/C][i%C] == '1') {
                result++;
                queue<int> q;
                q.push(i);
                grid[i/C][i%C] = '2';
                while (!q.empty()) {
                    cur = q.front();
                    q.pop();
                    r = cur/C;
                    c = cur%C;
                    for (int j = 0; j < 4; j++) {
                        nc = c + dir[j][0];
                        nr = r + dir[j][1];
                        if (nc >=  0 && nc < C && nr >= 0 && nr < R && grid[nr][nc] == '1') {
                            grid[nr][nc] = '2';
                            q.push(nr*C + nc);
                        }
                    }
                }

            }
        }
        return result;
    }

};
#endif //CPP_PROJECT_LEETCODE200_H
