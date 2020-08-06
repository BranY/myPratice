//
// Created by 杨文家 on 2020/8/4.
//
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include "hotelManageSystem.h"

int roomNum = 0;
int guestNum = 0;

int main(int argc, char *argv[]) {
    int flag = 1;

    RoomInfo *room = NULL;
    GuestInfo *guest = NULL;

    while (flag) {
        printMangeMenuMessage();

        int choice;
        printf("请输入您的选择：");
        //如果输入不非法
        if (scanf("%d", &choice) != 1) {
            printf("输入错误！\n已退出！\n");
            exit(0);
        }

        switch (choice) {
            case 1:
                roomManageMenu(&room);
                break; // 客房维护模块
            case 2:
                managementMenu(room, &guest);
                break; // 入住管理模块
            case 3:
                queryMenu(room, guest);
                break; // 查询信息模块
            case 4:
                dataStatsMenu(room, guest);
                break; // 数据统计模块
            case 0:
                printf("\n感谢使用本管理系统\n\n");
                flag = 0;
                break;
        }
    }
    return 0;
}

// 住宿管理菜单模块
void managementMenu(RoomInfo **room, GuestInfo **guest) {
    printCheckInRoomManageMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    switch (choice) {
        case 1:
            break; // 入住操作
        case 2:
            break; // 退房操作
        case 3:
            break; // 续房操作
        case 0:
            return; // 返回上一菜单
    }
}

// 房间信息管理菜单函数
void roomManageMenu(RoomInfo **room) {
    printRoomManageMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    switch (choice) {
        case 1:
            *room = initHotelRoom(*room);
            printRoomInfo(*room);
            break; // 初始化客房
        case 2:
            addRoomInfo(room);
            printRoomInfo(*room);
            break; // 添加房间
        case 3:
            printf("请输入您需要删除的房间编号：");
            int num;
            //如果输入不非法
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
            } else {
                delRoom(room, num);
                printRoomInfo(*room);
            }
            break; // 删除房间
        case 4:
            printf("请输入您需要修改的房间编号：");
            int num;
            //如果输入不非法
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
            } else {
                modifyRoom(room, num);
                printRoomInfo(*room);
            }
            break; // 修改房间
        case 0:
            return; // 返回上一菜单
    }
}

// init房间信息函数
RoomInfo *initHotelRoom(RoomInfo *room) {
    printf("注意！！！是否继续，此操作会将原客房信息覆盖且无法恢复（Y/N）\n");
    char choice;//确认操作
    scanf("%*c%c", &choice);
    if (choice != 'Y' && choice != 'y') {
        return room;
    }

    RoomInfo *p1;
    RoomInfo *p2;

    p1 = p2 = (RoomInfo *) malloc(sizeof(RoomInfo)); //申请新节点
    if (p1 == NULL || p2 == NULL) {
        printf("内存分配失败\n");
        exit(0);
    }

    roomNum = 0;
    inputRoomInfo(p1);
    p1->next = NULL;  //新节点的指针置为空

    // 输入的房间大于0则继续，直到输入的值为负
    while (p1->roomNum >= 0) {
        if (room == NULL)       //空表，接入表头
        {
            room = p1;
        } else {
            p2->next = p1;       //非空表，接入表尾
        }
        p2 = p1;
        p1 = (RoomInfo *) malloc(sizeof(RoomInfo));    //再重申请一个节点
        if (p1 == NULL || p2 == NULL) {
            printf("内存分配失败\n");
            exit(0);
        }
        inputRoomInfo(p1);
        p1->next = NULL;  //新节点的指针置为空
    }
    printf("酒店初始化成功，链表创建成功\n");
    return room;
}

void inputRoomInfo(RoomInfo *p) {
    memset(p, 0, sizeof(RoomInfo));
    p->roomNum = roomNum;
    roomNum++;

    scanf("房间类型%s", &p->roomType);
    p->status = 0;
    scanf("房间价格%lf", &p->price);
    p->guest = NULL;
}

// 增添房间函数
void addRoomInfo(RoomInfo **room) {
    RoomInfo *p;
    RoomInfo *head;
    RoomInfo *tmp;

    head = *room;
    tmp = head;

    p = (RoomInfo *) malloc(sizeof(RoomInfo)); // 申请一个新节点
    inputRoomInfo(p);

    while (head->next != NULL) {
        head = head->next;
    }
    head->next = p;
    *room = tmp;
    printf("成功添加房间\n");
}

// 删除房间
void delRoom(RoomInfo **room, int num) {
    RoomInfo *pre;
    RoomInfo *p;
    RoomInfo *tmp;

    p = *room;
    tmp = p;
    pre = NULL;

    while (p->next != NULL) {
        pre = p;
        p = p->next;

        if (p->roomNum == num) {
            if (p->status > 0) {
                printf("该房间号：%d 不能删除， 有客人：%s入住，不能删除.\n", p->guest->name, num);
                break;
            }
            // 判断是否为尾结点
            if (p->next == NULL) {
                pre->next = NULL;
            } else {
                pre->next = p->next;
            }
            free(p);
            printf("成功删除房间：%d.\n", num);
            *room = tmp;
            return;
        }
    }
    printf("没有需找到要删除的房间.\n");
}

// 修改Room
void modifyRoom(RoomInfo **room, int num) {
    RoomInfo *p;
    RoomInfo *tmp;

    p = *room;
    tmp = p;

    while (p->next != NULL) {
        p = p->next;

        if (p->roomNum == num) {
            if (p->status > 0) {
                printf("该房间号：%d 不能删除， 有客人：%s入住，不能修改.\n", p->guest->name, num);
                break;
            }
            int choice;
            printf("*****   修改房间类型请输入    1：       ****\n");
            printf("*****   修改房间价格请输入    2：       ****\n");
            printf("*****   退出修改房间请输入    0：       ****\n");
            switch (choice) {
                case 1:
                    char type[20];
                    if (scanf("%s", &type) != 1) {
                        printf("输入错误！\n");
                        return;
                    } else {
                        p->roomType = type;
                    }
                    break;
                case 2:
                    double price;
                    if (scanf("%lf", &price) != 1) {
                        printf("输入错误！\n");
                        return;
                    } else {
                        p->price = price;
                    }
                    break;
                case 0:
                    return; // 返回上一菜单
            }
            printf("成功修改房间信息：%d.\n", num);
            *room = tmp;
            return;
        }
    }
    printf("没有需找到要修改的房间.\n");
}

void printRoomInfo(RoomInfo *room) {
    RoomInfo *p = room;
    if (p == NULL) {
        printf("当前酒店没有任何房间\n");
    } else {
        while (NULL != p) {
            printf("num: %d, type: %s, price:%lf, status:%d ", p->roomNum, p->roomType, p->price, p->status);
            if (p->status > 0) {
                printf(", 客人：%s", p->guest->name)
            }
            p = p->next;
        }
        printf("\n");
    }
}

void queryMenu(RoomInfo *room, GuestInfo *guest) {
    printSearchMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    switch (choice) {
        case 1:
            break; // 根据身份号码查找客人
        case 2:
            break; // 根据电话号码查找客人
        case 3:
            break; // 根据入住姓名查找客人
        case 0:
            return; // 返回上一菜单
    }
}

// 信息统计模块
void dataStatsMenu(RoomInfo *room, GuestInfo *guest) {
    printDataStatsMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    switch (choice) {
        case 1:
            break; // 某个客人住店花费
        case 2:
            break; // 某天酒店住店人数
        case 3:
            break; // 某天酒店总的收入
        case 0:
            return; // 返回上一菜单
    }
}

void printMangeMenuMessage() {
    printf("*************************************\n");
    printf("****    欢迎使用住宿管理系统    *****\n\n");
    printf("****    客房管理请输入 1：      *****\n");
    printf("****    住宿管理请输入 2：      *****\n");
    printf("****    查询管理请输入 3：      *****\n");
    printf("****    数据统计请输入 4：      *****\n");
    printf("****    系统退出请输入 0：      *****\n\n");
    printf("*************************************\n");
}

void printCheckInRoomManageMessage() {
    printf("************************************\n");
    printf("*****   入住请输入    1：       ****\n");
    printf("*****   退房请输入    2：       ****\n");
    printf("*****   续房请输入    3：       ****\n");
    printf("*****   退出请输入    0：       ****\n");
    printf("************************************\n");
}

void printRoomManageMessage() {
    printf("************************************\n");
    printf("****  初始化客房请输入       1： ***\n");
    printf("****  添加客房信息请输入     2： ***\n");
    printf("****  删除客房信息请输入     3： ***\n");
    printf("****  修改客房信息请输入     4： ***\n");
    printf("****  返回上一菜单请输入     0： ***\n");
    printf("************************************\n");
}

void printSearchMessage() {
    printf("**************************************\n");
    printf("****  根据身份号码查找客人输入     1： ***\n");
    printf("****  根据电话号码查找客人输入     2： ***\n");
    printf("****  根据入住姓名查找客人输入     3： ***\n");
    printf("****  根据入住时间查找客人输入     4： ***\n");
    printf("****  筛选空的客房请输入          5： ***\n");
    printf("****  输出快到期的客人请输入       6： ***\n");
    printf("****  返回上一菜单请输入          0： ***\n");
    printf("***************************************\n");
}

void printDataStatsMessage() {
    printf("***************************************\n");
    printf("*****   某个客人住店花费输入    1：   ****\n");
    printf("*****   某天酒店住店人数输入    2：   ****\n");
    printf("*****   某天酒店总的收入输入    3：   ****\n");
    printf("*****   退出当前管理的请输入    0：   ****\n");
    printf("***************************************\n");
}

