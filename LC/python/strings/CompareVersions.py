#!/usr/bin/env python
# -*- coding: UTF-8 -*-
# Created by YWJ on 2018/10/7
import operator

class Solution:

    @staticmethod
    def compareVersion(version1, version2):
        """
        :type version1: str
        :type version2: str
        :rtype: int
        """
        v1 = list(map(int, version1.split(".")))
        v2 = list(map(int, version2.split(".")))
        if len(v1) < len(v2):
            v1.extend([0] * (len(v2) - len(v1)))
        else:
            v2.extend([0] * (len(v1) - len(v2)))

        if operator.gt(v1, v2):
            return 1
        elif operator.lt(v1, v2):
            return -1
        else:
            return 0


if __name__ == '__main__':
    version1 = "7.5.2.4"
    version2 = "7.5.3"

    print(Solution.compareVersion(version1, version2))