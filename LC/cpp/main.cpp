//
// Created by 杨文家 on 2020/4/27.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void swap(vector<int>& nums, unsigned long i, unsigned long j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}

void reverse(vector<int>& nums, unsigned long start, unsigned long size) {
    unsigned long i = start, j = size - 1;
    while (i < j) {
        swap(nums, i, j);
        i++;
        j--;
    }
}


void nextPermutation(vector<int>& nums) {
    unsigned long size = nums.size();
    if (size <= 1) {
        return;
    }
    unsigned long i = size - 2, j = size - 1, k = size - 1;
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


void test31() {
    vector<int> A = {1,2,3,4,5,6};
    nextPermutation(A);

    vector<int>::iterator it;
    for (it=A.begin(); it!=A.end(); ++it)
        cout << " " << *it;
}
int main(int argc, char * argv[]) {
   test31();
    return 0;
}