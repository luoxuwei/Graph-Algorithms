//
// Created by 罗旭维 on 2021/11/23.
//

#ifndef CPP_PROJECT_LEETCODE_773_H
#define CPP_PROJECT_LEETCODE_773_H
#include <string>
#include <queue>
#include <map>
#include <vector>

using namespace std;
class Leetcode773 {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        std::vector<std::vector<int>> dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int R = board.size();
        int C = board[0].size();
        int r = 0, nr = 0, c = 0, nc = 0;
        string target("123450");
        map<string, int> res;
        char *chars = new char[7];
        int a = 0;
        for (int i=0; i<2; i++) {
            for (int j=0; j<3; j++) {
                chars[a] = board[i][j] + '0';
                a++;
            }
        }
        chars[6] = '\0';
        string *cur = new string(chars);
        if (*cur == target) return 0;
        queue<string*> queue;
        queue.push(cur);
        res[*cur] = 0;
        int i0;
        int n0;
        string temps;
        while (!queue.empty()) {
            cur = queue.front();
            queue.pop();
            i0 = cur->find('0');
            r = i0/C;
            c = i0%C;
            for (int i=0; i<4; i++) {
                nr = r + dir[i][1];
                nc = c + dir[i][0];
                if (nr >=0 && nr < R && nc >= 0 && nc < C) {
                    temps.clear();
                    temps.append(*cur);
                    char *tempc = const_cast<char *>(temps.c_str());
                    n0 = nr * 3 + nc;
                    char temp = tempc[i0];
                    tempc[i0] = tempc[n0];
                    tempc[n0] = temp;
                    if (res.find(temps) == res.end()) {
                        queue.push(new string(temps));
                        res[*queue.back()] = res[*cur] + 1;
                        if (temps == target) {
                            return res[temps];
                        }
                    }

                }
            }
        }

        return -1;
    }
};

#endif //CPP_PROJECT_LEETCODE_773_H
