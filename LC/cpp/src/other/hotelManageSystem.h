//
// Created by 杨文家 on 2020/8/4.
//

#ifndef CPP_HOTELMANAGESYSTEM_H
#define CPP_HOTELMANAGESYSTEM_H

#define MAX 1000
typedef int Status; // 返回类型

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
    int idNum[18]; // 客人身份证号
    int tel[13]; // 电话号码
    char sex[8]; // 客人性别
    TimeInfo checkInTime; // 入住时间
    TimeInfo checkOutTime; // 离开时间
    int stayTime; // 租房时长 (入住天数）
    double cost; // 花费
    int isLeave; // 是否已经离店

    struct GuestInfo *next; // 下一个结点地址
} GuestInfo;

/**
 * 单人间
 * 双人间
 * 房间信息
 */
typedef struct RoomInfo {
    int roomNum; // 客房编号
    char roomType[20]; // 房间类型 （初始化后是固定值，不可修改）
    int status;  // 是否有人入住, 0没人住，1有人住
    double price;  // 费用

    GuestInfo *guest; // 该房间内的房客信息
    struct RoomInfo *next;   //下一个结点地址
} RoomInfo;

// 住宿管理菜单模块
void managementMenu(RoomInfo *room, GuestInfo **guest);

// 房间信息管理菜单函数
void roomManageMenu(RoomInfo **room);

void inputRoomInfo(RoomInfo *p);

// init房间信息函数
RoomInfo *initHotelRoom(RoomInfo *room);

// 增添房间函数
void addRoomInfo(RoomInfo **room);

// 删除Room函数
void delRoom(RoomInfo **room, int num);

// 修改Room函数
void modifyRoom(RoomInfo **room, int num);

// 打印房间信息函数
void printRoomInfo(RoomInfo *room);

// 查询主菜单函数
void queryMenu(RoomInfo *room, GuestInfo *guest);

// 按身份证号码进行查询函数
Status findByID(RoomInfo *room, GuestInfo *guest);

//按电话号码查询函数声明
Status findByTel(RoomInfo *room, GuestInfo *guest);

// 按姓名查询函数
Status findByName(RoomInfo *room, GuestInfo *guest);

// 查询入住快过期的客人函数
Status findExpiredGuest(RoomInfo *room, GuestInfo *guest);

// 按入住时间查询客人
Status findByTime(RoomInfo *room, GuestInfo *guest);

// 房间当前有那些空床
void printRoomEmptyBed(RoomInfo *room);

// 信息统计模块
void dataStatsMenu(RoomInfo *room, GuestInfo *guest);

// 某住客的应付多少费用
double guestCost(RoomInfo *room, GuestInfo *guest);

// 某天住店总人数
int totalGuests(RoomInfo *room, GuestInfo *guest);

// 某天酒店总收入
double totalIncome(RoomInfo *room, GuestInfo *guest);

// 主菜单提示信息
void printMangeMenuMessage();

// 入住等提示信息
void printCheckInRoomManageMessage();

// 房间管理提示信息
void printRoomManageMessage();

// 查询模块提示信息
void printSearchMessage();

// 数据统计提示信息
void printDataStatsMessage();

// 检查身份证号码是否合法函数
int checkIdentityIDLegal(int id[]);

// 检查电话号码是否合法函数
int checkTelPhoneNumberLegal(int tel[]);

// 检查时间是否合法函数
int checkTimeInfoLegal(TimeInfo timeInfo);

// 检查性别是否合法函数
int checkSexLegal(char sex[8]);

// todo
/**
 * 1. 实现几个信息合法性校验函数
 * 2. 从文件读取客户和房间信息，建立链表
 * 3. 每次房间信息修改或者退出，保存信息到文件
 * 4. 添加其他函数，完善管理系统
 * 5. 通过数据库管理房间和客人信息
 * 6. 管理员登录本系统时进行账号管理以及账号、密码合法性校验
 * 6. 通过web页面管理
 */
#endif // CPP_HOTELMANAGESYSTEM_H
