//
// Created by 杨文家 on 2020/8/3.
//

#ifndef CPP_GUEST_H
#define CPP_GUEST_H

//入住时间的结构体
typedef struct
{
    int year;
    int mon;
    int day;
}checkingTime;

//客官信息
typedef struct Guest
{
    int guenum; // 客人住房编号
    char name[20]; // 姓名
    char sex[5]; // 性别
    char prefession[20]; // 身份
    char idnum[19]; // 身份证号
    char phone[12]; // 电话号码

    checkingTime ; // 入住时间

    int timelong; // 租房时长
    long int s;  // 标记时间
}guest;

#endif //CPP_GUEST_H
