//
// Created by 罗旭维 on 2021/11/21.
//

#ifndef CPP_PROJECT_LEETCODE1020_H
#define CPP_PROJECT_LEETCODE1020_H
#include <vector>
#include <queue>

using namespace std;
class Solution1020 {

public:
    int nums = 0;
    int numEnclaves(vector<vector<int>>& grid) {
        std::vector<std::vector<int>> dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int R = grid.size();
        int C = grid[0].size();
        int r = 0, nr = 0, c = 0, nc = 0;
        int cur;
        int res = 0;
        for (int i = 0; i < R*C; i++) {
            r = i/C;
            c = i%C;
            if (grid[r][c] == 1) {
                bool b = false;
                nums = 0;
                queue<int> queue;
                queue.push(i);
                grid[r][c] = 2;
                while (!queue.empty()) {
                    nums++;
                    cur = queue.front();
                    queue.pop();
                    r = cur/C;
                    c = cur%C;
                    if (r == 0 || r == (R - 1) || c == 0 || c == (C-1)) {
                        b = true;
                    }
                    for (int j = 0; j < 4; j++) {
                        nr = dir[j][1] + r;
                        nc = dir[j][0] + c;
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 1) {
                            grid[nr][nc] = 2;
                            queue.push(nr*C+nc);
                        }
                    }
                }
                if (!b) {
                    res += nums;
                }
            }
        }
        return res;
    }
};

#endif //CPP_PROJECT_LEETCODE1020_H
