//
// Created by 杨文家 on 2020/8/4.
//

#ifndef CPP_HOTELMANAGESYSTEM_H
#define CPP_HOTELMANAGESYSTEM_H

#define MAX 1000  //哈希表的表长
typedef int Index; // hash地址类型
typedef void Status; // 返回类型

// 时间结构体
typedef struct TimeInfo {
    int year;
    int mon;
    int day;
    int hour;
    int minute;
    int second;
} TimeInfo;

// 房客信息
typedef struct GuestInfo {
    int guestNum; // 客人住房编号
    char name[20]; // 客人姓名
    char idNum[18]; // 客人身份证号
    char tel[13]; // 电话号码
    char sex[8]; // 客人性别
    TimeInfo checkInTime; // 入住时间
    TimeInfo checkOutTime; // 离开时间
    int stayTime; // 租房时长 (入住天数）
    double cost; // 房间每日的价格

    struct GuestInfo *next; // 下一个结点地址
} GuestInfo;

/**
 * 单人间
 * 双人间
 * 房间信息
 */
typedef struct RoomInfo {
    int roomNum; // 客房编号
    char roomType[20]; // 房间类型
    int maxNum; // 最大入住人数
    int curNum; // 已入住人数
    int status;  // 是否有人入住
    double price;  // 费用
    double deposit;  // 押金

    char name[20];   // 姓名
    char tel[13];    // 电话
    char idNum[18];  // 身份证号

    struct RoomInfo *next;   //下一个结点地址
} RoomInfo;

GuestInfo guestInfo[MAX]; // 定义客人的哈希表
RoomInfo roomsInfo[MAX]; // 定义客房的哈希表

// 主菜单管理函数
int managementFun(RoomInfo *room, GuestInfo *guest, GuestInfo *head1);

// 管理员端主菜单函数
void menuRoomInfo(GuestInfo *root);

// 修改房间信息菜单函数
void changeRoomMenu();

// 修改房间信息主函数
void changeRoomInfo(RoomInfo *room);

// 浏览房间信息函数
void printRoomInfo(RoomInfo *room, GuestInfo *guest);

// 增添房间信息函数
RoomInfo *addRoomInfo(RoomInfo *head);

// 修改Room函数
RoomInfo *modifyRoom(RoomInfo *room);

// 删除Room函数
RoomInfo *delRoom(RoomInfo *room);

// 修改房间信息 (位置，入住信息，价格等）
void modifyRoomInfo(RoomInfo *room, int type);

// 查询主菜单函数
void queryMenu();

// 按身份证号码进行查询函数
void findByID(RoomInfo *room, GuestInfo *guest);

//按电话号码查询函数声明
void findByTel(RoomInfo *room, GuestInfo *guest);

// 按姓名查询函数
void findByName(RoomInfo *room, GuestInfo *guest);

// 按房型进行查询函数
void findByType(RoomInfo *room, GuestInfo *guest);

// 按状态查询函数
void findByStatus(RoomInfo *room, GuestInfo *guest);

// 按加入时间查询函数
void findByInputTime(RoomInfo *room, GuestInfo *guest);

// 按每天费用查询函数
void findByCost(RoomInfo *room, GuestInfo *guest);

#endif //CPP_HOTELMANAGESYSTEM_H
