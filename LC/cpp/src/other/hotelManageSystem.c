//
// Created by 杨文家 on 2020/8/4.
//
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include "hotelManageSystem.h"

int roomNum = 0;

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
                dataStatsMenu(guest);
                break; // 数据统计模块
            case 0:
                printf("\n感谢您使用本管理系统.\n\n");
                flag = 0;
                break;
        }
    }
    return 0;
}

// 住宿管理菜单模块
void managementMenu(RoomInfo *room, GuestInfo **guest) {
    printCheckInRoomManageMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    int num;
    switch (choice) {
        case 1:
            checkIn(room, guest);
            break; // 入住操作
        case 2:
            //如果输入不非法
            printf("输入需要退掉的房间编号： ");
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
                return;
            } else {
                checkOut(room, guest, num);
            }
            break; // 退房操作
        case 3:
            //如果输入不非法
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
                return;
            } else {
                renewal(room, guest, num);
            }
            break; // 续房操作
        case 0:
            return; // 返回上一菜单
    }
}

// 时间输入, 默认顺序为： year, month , day
TimeInfo *inputTime() {
    TimeInfo *t;

    t = (TimeInfo *) malloc(sizeof(TimeInfo)); //申请新节点
    if (t == NULL) {
        printf("内存分配失败\n");
        exit(0);
    }
    memset(t, 0, sizeof(TimeInfo));

    printf("年：");
    scanf("%d", &t->year);
    printf("月：");
    scanf("%d", &t->month);
    printf("日：");
    scanf("%d", &t->day);
    // todo: 判断日期合法性
    return t;
}

// 计算时间差 (注意是否是闰年：四年一闰，百年不闰，四百年再闰 ）
int getDayDiff(TimeInfo *in, TimeInfo *out) {
    int y1, m1, d1;
    int y2, m2, d2;

    m1 = (in->month + 9) % 12;
    y1 = in->year - m1 / 10;
    d1 = 365 * y1 + (y1 / 4 - y1 / 100 + y1 / 400) + (m1 * 306 + 5) / 10 + (in->day - 1);

    m2 = (out->month + 9) % 12;
    y2 = out->year - m2 / 10;
    d2 = 365 * y2 + (y2 / 4 - y2 / 100 + y2 / 400) + (m2 * 365 + 5) / 10 + (out->day - 1);

    return (d2 - d1);
}

// 酒店链表中的第一个空房
RoomInfo *findEmptyRoom(RoomInfo *room) {
    RoomInfo *p = room;
    if (p == NULL) {
        printf("当前酒店没有任何房间\n");
    } else {
        while (NULL != p) {
            if (p->status == 0) {
                return p;
            }
            p = p->next;
        }
    }
    return NULL;
}

RoomInfo *findRoomByRoomID(RoomInfo *room, int num) {
    RoomInfo *p = room;
    if (p == NULL) {
        printf("当前酒店没有任何房间\n");
    } else {
        while (NULL != p) {
            if (p->roomNum == num) {
                return p;
            }
            p = p->next;
        }
    }
    return NULL;
}

GuestInfo *findGuestByRoomID(GuestInfo *head, int num) {
    GuestInfo *p = head;
    if (p == NULL) {
        printf("没有找到这个客人呀，%s, 房间编号：%d .\n", head->name, num);
    } else {
        while (NULL != p) {
            if (p->guestNum == num && p->isLeave == 0) {
                return p;
            }
            p = p->next;
        }
    }
    return NULL;
}

GuestInfo *addGuest(GuestInfo *guest) {
    GuestInfo *p;
    GuestInfo *head;

    head = guest;

    p = (GuestInfo *) malloc(sizeof(GuestInfo)); // 申请一个新节点
    inputGuestInfo(p);

    if (guest == NULL) {
        guest = p;
        return guest;
    }

    while (head->next != NULL) {
        head = head->next;
    }

    head->next = p;
    return p;
}

void inputGuestInfo(GuestInfo *p) {
    memset(p, 0, sizeof(GuestInfo));

    printf("输入客人姓名： ");
    p->name = (char *) malloc(10 * sizeof(char));
    scanf("%s", p->name);

    printf("输入客人身份证ID: ");
    p->idNum = (char *) malloc(20 * sizeof(char));
    scanf("%s", p->idNum);

    printf("输入客人电话号码: ");
    p->tel = (char *) malloc(15 * sizeof(char));
    scanf("%s", p->tel);

    printf("输入客人性别: ");
    p->sex = (char *) malloc(8 * sizeof(char));
    scanf("%s", p->sex);

    printf("输入客人住店时间\n");
    p->checkInTime = inputTime();
    printf("输入客人预计离店时间\n");
    p->checkOutTime = inputTime();
    p->stayTime = getDayDiff(p->checkInTime, p->checkOutTime);
}

// 入住操作
void checkIn(RoomInfo *room, GuestInfo **guest) {
    RoomInfo *p;
    p = findEmptyRoom(room);
    // 先遍历找到空房
    if (NULL == p) {
        printf("当前酒店没有任何空的房间， 不好意思.\n");
    } else {
        printf("即将入住酒店房间号：%d, 价格：%lf .\n", p->roomNum, p->price);
        GuestInfo *cur = addGuest(*guest);

        cur->guestNum = p->roomNum; // 更新客人的房间编号
        cur->totalCost = cur->stayTime * p->price; // 预计花费
        cur->isLeave = 0; // 更新入住状态

        p->status = 1; // 更新房间状态
        p->guest = cur; // 更新房间的客人信息

        printf("客人：%s, 即将入住酒店房间号：%d, 住店：%d天， 您预计将花费：%lf， 祝您住店愉快.\n", cur->name, cur->guestNum, cur->stayTime, cur->totalCost);
    }
}

// 退房操作
void checkOut(RoomInfo *room, GuestInfo **guest, int num) {
    RoomInfo *p = findRoomByRoomID(room, num);
    if (p == NULL) {
        printf("没找到需要退掉的房间\n");
    } else {
        GuestInfo *g;
        g = findGuestByRoomID(*guest, num);
        if (g == NULL) {
            printf("没找到需要退房的客人\n");
            return;
        } else {
            p->status = 0; // 更新房间状态
            g->isLeave = 1;
            printf("客人：%s, Id：%s, 需要支付的费用：%lf, 欢迎下次继续光临本店.\n", g->name, g->idNum, g->totalCost);
        }
    }
}

// 续房操作
void renewal(RoomInfo *room, GuestInfo **guest, int num) {
    RoomInfo *p;
    p = findRoomByRoomID(room, num);

    if (room == NULL) {
        printf("没找到需要续约的房间\n");
    } else {
        GuestInfo *g;
        g = findGuestByRoomID(*guest, num);
        if (g == NULL) {
            printf("没找到需要续约的客人\n");
            return;
        } else {
            g->checkOutTime = inputTime();
            g->stayTime = getDayDiff(g->checkInTime, g->checkOutTime);
            g->totalCost = g->stayTime * p->price; // 更新花费
        }
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
    int num;
    switch (choice) {
        case 1:
            *room = initHotelRoom(*room);
            printRoomInfo(*room);
            break; // 初始化客房
        case 2:
            *room = addRoomInfo(*room);
            printRoomInfo(*room);
            break; // 添加房间
        case 3:
            printf("请输入您需要删除的房间编号：");
            //如果输入不非法
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
            } else {
                delRoom(room, num);
                printRoomInfo(*room);
            }
            break; // 删除房间
        case 4:
            printf("请输入您需要修改的房间编号： ");
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

void inputRoomInfo(RoomInfo *p) {
    memset(p, 0, sizeof(RoomInfo));
    p->roomNum = roomNum;
    roomNum++;
    printf("输入房间类型: ");
    p->roomType = (char *) malloc(20 * sizeof(char));
    scanf("%s", p->roomType);
    p->status = 0;
    printf("输入房间价格: ");
    scanf("%lf", &p->price);
    p->guest = NULL;
}

// init房间信息函数
RoomInfo *initHotelRoom(RoomInfo *room) {
    printf("注意！！！是否继续，此操作会将原客房信息覆盖且无法恢复（Y/N）: ");
    char choice;//确认操作
    scanf("%*c%c", &choice);
    if (choice != 'Y' && choice != 'y') {
        printf("即将退出初始化过程.\n");
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

    // 输入的尾部大于0则继续，直到输入的值为负
    printf("输入结尾判断: ");
    int t = scanf("%d", &p1->tail);
    while (t == 1 && p1->tail > 0) {
        //空表，接入表头
        if (room == NULL) {
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
        printf("输入结尾判断: ");
        t = scanf("%d", &p1->tail);
    }
    printf("酒店初始化成功，链表创建成功\n");
    return room;
}

// 增添房间函数
RoomInfo *addRoomInfo(RoomInfo *room) {
    RoomInfo *p;
    RoomInfo *head;

    head = room;

    p = (RoomInfo *) malloc(sizeof(RoomInfo)); // 申请一个新节点
    inputRoomInfo(p);
    p->next = NULL;

    if (room == NULL) {
        room = p;
        return room;
    }

    while (head->next != NULL) {
        head = head->next;
    }
    head->next = p;
    printf("成功添加房间\n");
    return room;
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
                printf("该房间号：%d 不能删除， 有客人：%s入住，不能删除.\n", p->guest->guestNum, p->guest->name);
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

    char type[] = "";
    double price;

    while (p->next != NULL) {
        p = p->next;

        if (p->roomNum == num) {
            if (p->status > 0) {
                printf("该房间号：%d 不能删除， 有客人：%s入住，不能修改.\n", p->guest->guestNum, p->guest->name);
                break;
            }
            int choice;
            printf("*****   修改房间类型请输入    1：       ****\n");
            printf("*****   修改房间价格请输入    2：       ****\n");
            printf("*****   退出修改房间请输入    0：       ****\n");

            scanf("%d", &choice);
            switch (choice) {
                case 1:
                    printf("要修改房间类型：");
                    if (scanf("%s", type) != 1) {
                        printf("输入错误！\n");
                        return;
                    } else {
                        p->roomType = type;
                    }
                    break;
                case 2:
                    printf("要修改房间价格：");
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
                printf(", 客人：%s", p->guest->name);
            }
            if (p->next != NULL) {
                printf(" --> ");
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

    char *id;
    char *name;
    TimeInfo *tt = NULL;
    switch (choice) {
        case 1:
            printf("输入客人的身份ID:");
            id = (char *) malloc(20 * sizeof(char));
            if (scanf("%s", id) != 1) {
                printf("输入错误！\n");
                return;
            } else {
                findByIDAndPrint(guest, id);
            }
            break; // 根据身份号码查找客人
        case 2:
            name = (char *) malloc(20 * sizeof(char));
            printf("输入客人的姓名: ");
            if (scanf("%s", name) != 1) {
                printf("输入错误！\n");
                return;
            } else {
                findByNameAndPrint(guest, name);
            }
            break; // 根据姓名查找客人
        case 3:
            tt = inputTime();
            printf("输入客人的入住日期\n");
            findByTimeAndPrint(guest, tt);
            break; // 根据入住时间查找客人
        case 4:
            findAndPrintEmptyRoom(room);
            break; // 查找空的房间
        case 0:
            return; // 返回上一菜单
    }
}

void findAndPrintEmptyRoom(RoomInfo *room) {
    RoomInfo *empty = findEmptyRoom(room);
    if (empty == NULL) {
        printf("没找到空的房间\n");
    } else {
        printf("空的房间编号：%d, 价格：%lf, 房间类型：%s .\n", empty->roomNum, empty->price, empty->roomType);
    }
}

// 按身份证号码进行查询函数
void findByIDAndPrint(GuestInfo *guest, char *id) {
    GuestInfo *p = guest;
    if (p == NULL) {
        printf("当前没有任何客人\n");
    } else {
        while (NULL != p) {
            printf("当前：%s, target:%s \n", p->name, id);
            if (strcmp(p->idNum, id) == 0) {
                printf("id: %s, name: %s, cost: %lf, status: %d , tel: %s, sex: %s ", p->idNum, p->name, p->totalCost,
                       p->isLeave, p->tel, p->sex);
                printf(" 入住时间：%d-%d-%d, ", p->checkInTime->year, p->checkInTime->month, p->checkInTime->day);
                printf(" 离店时间： %d-%d-%d .\n", p->checkOutTime->year, p->checkOutTime->month, p->checkOutTime->day);
                return;
            }
            p = p->next;
        }
    }
    printf("没有找到客人\n");
}

// 按姓名查询函数
void findByNameAndPrint(GuestInfo *guest, char *name) {
    GuestInfo *p = guest;
    if (p == NULL) {
        printf("当前没有任何客人\n");
    } else {
        while (NULL != p) {
            if (strcmp(p->name, name) == 0) {
                printf("id: %s, name: %s, cost: %lf, status: %d , tel: %s, sex: %s ", p->idNum, p->name, p->totalCost,
                       p->isLeave, p->tel, p->sex);
                printf(" 入住时间：%d-%d-%d, ", p->checkInTime->year, p->checkInTime->month, p->checkInTime->day);
                printf(" 离店时间： %d-%d-%d .\n", p->checkOutTime->year, p->checkOutTime->month, p->checkOutTime->day);
                return;
            }
            p = p->next;
        }
    }
    printf("没有找到客人\n");
}

// 按入住时间查询客人
void findByTimeAndPrint(GuestInfo *guest, TimeInfo *tt) {
    printf("查询时间：%d-%d-%d .\n", tt->year, tt->month, tt->day);
    GuestInfo *p = guest;
    if (p == NULL) {
        printf("当前没有任何客人\n");
    } else {
        while (NULL != p) {
            if (p->checkInTime->year == tt->year && p->checkInTime->month == tt->month &&
                p->checkInTime->day == tt->day) {
                printf("id: %s, name: %s, cost: %lf, status: %d , tel: %s, sex: %s ", p->idNum, p->name, p->totalCost,
                       p->isLeave, p->tel, p->sex);
                printf(" 入住时间：%d-%d-%d, ", p->checkInTime->year, p->checkInTime->month, p->checkInTime->day);
                printf(" 离店时间： %d-%d-%d .\n", p->checkOutTime->year, p->checkOutTime->month, p->checkOutTime->day);
                return;
            }
            p = p->next;
        }
    }
    printf("没有找到客人\n");
}

// 信息统计模块
void dataStatsMenu(GuestInfo *guest) {
    printDataStatsMessage();

    int choice;
    printf("请输入您的选择：");
    //如果输入不非法
    if (scanf("%d", &choice) != 1) {
        printf("输入错误！\n已退出！\n");
        return;
    }
    int num;

    TimeInfo *tt = NULL;
    int count;
    double totalCost;
    switch (choice) {
        case 1:
            printf("输入客人的房间编号.\n");
            if (scanf("%d", &num) != 1) {
                printf("输入错误！\n");
                return;
            } else {
                double cost = guestCost(guest, num);
                printf("当前客人，%d, 住店花费： %lf .\n", num, cost);
            }
            break; // 某个客人住店花费
        case 2:
            printf("请输入时间\n");
            tt = inputTime();
            count = totalGuests(guest, tt);
            printf("%d-%d-%d, 住店人数为： %d .\n", tt->year, tt->month, tt->day, count);
            break; // 某天酒店住店人数
        case 3:
            printf("请输入时间\n");
            tt = inputTime();
            totalCost = totalIncome(guest, tt);
            printf("%d-%d-%d, 酒店收入为： %lf .\n", tt->year, tt->month, tt->day, totalCost);
            break; // 某天酒店总的收入
        case 0:
            return; // 返回上一菜单
    }
}

// 某住客的应付多少费用
double guestCost(GuestInfo *guest, int num) {
    GuestInfo *g = findGuestByRoomID(guest, num);
    if (NULL == g) {
        printf("没有找到当前客人，%d .\n", num);
        return 0;
    } else {
        return g->totalCost;
    }
}

// 某天住店总人数
int totalGuests(GuestInfo *guest, TimeInfo *tt) {
    printf("查询时间：%d-%d-%d .\n", tt->year, tt->month, tt->day);
    GuestInfo *p = guest;
    int count = 0;
    if (p == NULL) {
        printf("当前没有任何客人\n");
    } else {
        while (NULL != p) {
            if (p->checkInTime->year == tt->year && p->checkInTime->month == tt->month &&
                p->checkInTime->day == tt->day) {
                if (p->isLeave)
                    count += 1;
            }
            p = p->next;
        }
    }
    return count;
}

double totalIncome(GuestInfo *guest, TimeInfo *tt) {
    printf("查询时间：%d-%d-%d .\n", tt->year, tt->month, tt->day);
    GuestInfo *p = guest;
    double cost = 0;
    if (p == NULL) {
        printf("当前没有任何客人\n");
    } else {
        while (NULL != p) {
            if (p->checkInTime->year == tt->year && p->checkInTime->month == tt->month &&
                p->checkInTime->day == tt->day) {
                cost += p->totalCost;
            }
            p = p->next;
        }
    }
    return cost;
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
    printf("****  根据入住姓名查找客人输入     2： ***\n");
    printf("****  根据入住时间查找客人输入     3： ***\n");
    printf("****  筛选空的客房请输入          4： ***\n");
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

