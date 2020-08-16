//
// Created by 杨文家 on 2020/8/4.
//

#ifndef CPP_HOTELMANAGESYSTEM_H
#define CPP_HOTELMANAGESYSTEM_H

// 时间结构体
typedef struct TimeInfo {
    int year;
    int month;
    int day;
} TimeInfo;

// 房客信息
typedef struct GuestInfo {
    int guestNum; // 客人住房编号
    char* name; // 客人姓名
    char* idNum; // 客人身份证号
    char* tel; // 电话号码
    char* sex; // 客人性别
    TimeInfo *checkInTime; // 入住时间
    TimeInfo *checkOutTime; // 离开时间
    int stayTime; // 租房时长 (入住天数）
    int isLeave; // 是否已经离店
    double totalCost; // 住店预计花费

    struct GuestInfo *next; // 下一个结点地址
} GuestInfo;

/**
 * 单人间
 * 双人间
 * 房间信息
 */
typedef struct RoomInfo {
    int roomNum; // 客房编号
    char* roomType; // 房间类型 （初始化后是固定值，不可修改）
    int status;  // 是否有人入住, 0没人住，1有人住
    double price;  // 费用
    int tail; // 判断是否为当前输入的尾部

    GuestInfo *guest; // 该房间内的房客信息
    struct RoomInfo *next;   //下一个结点地址
} RoomInfo;

// 时间输入
TimeInfo *inputTime();

// 计算时间差
int getDayDiff(TimeInfo *in, TimeInfo *out);

// 住宿管理菜单模块
void managementMenu(RoomInfo *room, GuestInfo **guest);

// 酒店链表中的第一个空房
RoomInfo *findEmptyRoom(RoomInfo *room);

// 根据房间ID查找房间
RoomInfo *findRoomByRoomID(RoomInfo *room, int guestNum);

// 根据房间ID查找客人
GuestInfo *findGuestByRoomID(GuestInfo *head, int num);

// 入住操作
void checkIn(RoomInfo *room, GuestInfo **guest);

GuestInfo *addGuest(GuestInfo *guest);

// 输入客人信息基本信息
void inputGuestInfo(GuestInfo *p);

// 退房操作
void checkOut(RoomInfo *room, GuestInfo **guest, int guestNum);

// 续房操作
void renewal(RoomInfo *room, GuestInfo **guest, int guestNum);

// 房间信息管理菜单函数
void roomManageMenu(RoomInfo **room);

void inputRoomInfo(RoomInfo *p);

// init房间信息函数
RoomInfo *initHotelRoom(RoomInfo *room);

// 增添房间函数
RoomInfo *addRoomInfo(RoomInfo *room);

// 删除Room函数
void delRoom(RoomInfo **room, int num);

// 修改Room函数
void modifyRoom(RoomInfo **room, int num);

// 打印房间信息函数
void printRoomInfo(RoomInfo *room);

// 查询主菜单函数
void queryMenu(RoomInfo *room, GuestInfo *guest);

// 按身份证号码进行查询函数
void findByIDAndPrint(GuestInfo *guest, char *id);

// 按姓名查询函数
void findByNameAndPrint(GuestInfo *guest, char *name);

// 按入住时间查询客人
void findByTimeAndPrint(GuestInfo *guest, TimeInfo *time);

void findAndPrintEmptyRoom(RoomInfo *room);

// 信息统计模块
void dataStatsMenu(GuestInfo *guest);

// 某住客的应付多少费用
double guestCost(GuestInfo *guest, int num);

// 某天住店总人数
int totalGuests(GuestInfo *guest, TimeInfo *tt);

// 某天酒店总收入
double totalIncome(GuestInfo *guest, TimeInfo *tt);

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
int checkSexLegal(char sex[]);

// todo
/**
 * 1. 实现上面4个信息合法性校验函数
 * 2. 从文件读取客户和房间信息，建立链表
 * 3. 每次房间信息修改或者退出，保存信息到文件
 * 4. 添加其他函数，完善管理系统
 * 5. 通过数据库管理房间和客人信息
 * 6. 管理员登录本系统时进行账号管理以及账号、密码合法性校验
 * 6. 通过web页面管理
 */
#endif
