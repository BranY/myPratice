//
// Created by 杨文家 on 2020/4/27.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution31 {
public:
    void nextPermutation(vector<int>& nums) {
        auto size = nums.size();
        if (size <= 1) {
            return;
        }
        auto i = size - 2, j = size - 1, k = size - 1;
        while (i >=0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        if (i >= 0) {
            while(nums[i] >= nums[k]) k--;
            swap(nums, i, k);
        }

        reverse(nums, j, size);
    }
    void reverse(vector<int>& nums, int start, int size) {
        auto i = start, j = size - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(vector<int>& nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
};

int main(int argc, char * argv[]) {
    printf("test multi dir cpp\n");
    return 0;
}
