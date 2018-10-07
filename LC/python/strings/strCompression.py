#!/usr/bin/env python
# -*- coding: UTF-8 -*-
# Created by YWJ on 2018/10/3


class Solution:

    @staticmethod
    def compress(chars):
        """
        :type chars: List[str]
        :rtype: int
        """
        n = len(chars)
        pos, count = 0, 1
        for j in range(1, n + 1):
            if j < n and chars[j] == chars[j - 1]:
                count += 1
            else:
                chars[pos] = chars[j - 1]
                pos += 1
                if count > 1:
                    for m in str(count):
                        chars[pos] = m
                        pos += 1
                count = 1

        return pos

    @staticmethod
    def findRestaurant(list1, list2):
        """
        :type list1: List[str]
        :type list2: List[str]
        :rtype: List[str]
        """
        dict1 = {}
        min_index = 2000
        result = []
        for i in range(len(list1)):
            dict1[list1[i]] = i

        for i in range(len(list2)):
            if list2[i] in dict1 and dict1[list2[i]] + i < min_index:
                min_index = dict1[list2[i]] + i
                result = []
                result = [list2[i]]
            elif list2[i] in dict1 and dict1[list2[i]] + i == min_index:
                result.append(list2[i])

        return result


if __name__ == '__main__':
    chars = ["a","a","b","b","c","c","c"]
    chars1 = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]

    charsx =[">","m","m","m","m","m","w","w","w","|","|","|","*","0",":",":",":","S","1","1","1","1","T","T","s","s","s","s","s","s","l","l","2","q","M","M","p","p","p","p","p","p","<","p","L","L","L","0","0","5","5","5","5","^","X","I","I","I","F","|","|","|","|","|","|","Q","Q","G","-","-","-",">","U","U","U","a","/","?","?","?","M","M","M","x","x","x","|","G","G","G","G","k","k",";","T","]","@","\\","J","J","J","T","2","(","|","|","|","&","&","G","|","|","|","|","q","q","`","`","C","H","H","o","o","o",">",":","s","s","s","z","Q","Q","f","u","c","c","c","c","c","c","A","A","*","*","*","~","~","~","]","v","B","e","B","B","B","g","o","3","9","|","q","q","g","g","g",",",",",",",",",">","u","u","*","*","*","\\","d","}","v","W","W","W","o","o","0","6","6","6","6","M","a","8","a","N","%","%","%","%","4","p","m","m","W","W","3","b","e","r","r","g","4","4","4","k","k","$","$","$","H","S","]",")",")",")",")",")","U","U","{","B","{","s","s","s","C","C","3","E",")","Z","Z","Z","h","f","f","G","G","G","p","p","/","{","{","x","U","U","U","U","U","D","3","?","?","?","d","B","B","B","B","F","M","M","M","z","z","|","o","o","-","D","D","D","D","D","c","c","c","I","I","}","}","}","}","Q","Q","Q","_","_","e","e"]
    cls = Solution()

    print(cls.compress(charsx))

    list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]
    list2 = ["KFC", "Shogun", "Burger King"]
    print(cls.findRestaurant(list1, list2))