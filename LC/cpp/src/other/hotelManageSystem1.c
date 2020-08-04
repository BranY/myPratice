//
// Created by 杨文家 on 2020/8/4.
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

#define LEN 100

int manageFun(struct room *head, struct ID *p, struct ID *head1);      //管理员端主函数声明
void menuRoom(struct ID *head1);                                      //管理员端主菜单函数声明
struct room *read();                                              //将文件中的房间信息读取到链表中函数声明
void write(struct room *head);                                   //将文件中的房间信息写进链表中函数声明
struct room *add(struct room *head);                              //增添房间信息函数声明
void printRoom(struct room *head, struct ID *head1);                      //浏览房间信息函数声明
void change(struct room *head);                                      //修改房间信息主函数声明
void cMenu();                                                      //修改房间信息菜单函数声明
struct room *amend(struct room *head);                              //修改遍历函数声明
void cNum(struct room *head);                                      //修改房间号函数声明
void cType(struct room *head);                                      //修改房型函数声明
void cSite(struct room *head);                                    //修改房间位置函数声明
void cCheckIn(struct room *head);                                 //修改房间入住信息函数声明
void cCost(struct room *head);                                    //修改房间价格函数声明
void cName(struct room *head);                                    //修改住客姓名函数声明
void dispose(struct room *head);                                  //处理住客申请函数声明
struct room *del(struct room *head, struct ID *head1);             //删除房间信息函数声明
void finds(struct room *head, struct ID *head1);                   //查询房间信息函数声明
void fMenu();                                                     //查询主菜单函数声明
void fNum(struct room *head, struct ID *head1);                    //按身份证号码进行查询函数声明
void fType(struct room *head, struct ID *head1);                   //按房型进行查询函数声明
void fSite(struct room *head, struct ID *head1);                   //按位置查询函数声明
void fStatus(struct room *head, struct ID *head1);                 //按状态查询函数声明
void fInputTime(struct room *head, struct ID *head1);              //按加入时间查询函数声明
void fDays(struct room *head, struct ID *head1);                   //按加入天数查询函数声明
void fCost(struct room *head, struct ID *head1);                   //按每天费用查询函数声明
void fName(struct room *head, struct ID *head1);                   //按姓名查询函数声明
void fTel(struct room *head, struct ID *head1);                    //按电话号码查询函数声明
void signFind(struct ID *head);                  //密码找回函数声明
struct ID *logout(struct ID *head);              //密码注销函数声明
struct ID *accountName(struct ID *head);         //用户账号注册函数声明
struct ID *sign(struct ID *head);                //用户登录函数声明
struct ID *loginRead();                          //用户数据读入函数声明
void loginWrite(struct ID *head);                //将用户数据写入文件中函数声明
void loginMenu();                                //登录界面菜单函数声明
struct ID *login();                              //用户登录系统主函数
void changePassword(struct ID *head);            //密码修改函数声明
char *randStr(char str[], int n);                //验证码生成函数声明
int user(struct room *head, struct ID *p, struct ID *head1);        //用户端主函数声明
void umenu(struct ID *p);                        //用户端主菜单函数声明
void reserve(struct room *head, struct ID *p, struct ID *head1);    //用户端预定酒店函数声明
void out(struct room *head, struct ID *p, struct ID *head1);        //用户端退酒店函数声明
void userLook(struct room *head, struct ID *p, struct ID *head1);

void userChange(struct room *head, struct ID *p, struct ID *head1);

void userChangeMenu();

struct ID *userAmend(struct room *head, struct ID *p, struct ID *head1);

void cInputTime(struct room *head, struct ID *p, struct ID *head1);

void cInputName(struct room *head, struct ID *p, struct ID *head1);

void cUserTel(struct room *head, struct ID *p, struct ID *head1);

int scanfPlus(char *st, int m, int n, int x);       //改进版输入函数声明
void visitor();                                  //游客端函数声明
void viewMenu();                                 //游客端菜单
void viewLook();                                 //游客端查看
void load();                                     //进度条加载

void form2(int n);                               //打房间完整信息表格函数声明
void form3(struct room *p1, int i);               //打房间完整信息表格中的数据函数声明
int form4(int n, struct room *head);              //打房间部分信息表格加数据函数声明
struct ID *tranday(struct ID *p);                //天数转化函数声明
void status(struct room *p1, struct ID *x1);      //状态更改函数声明

void reset(struct room *head);                   //重置信息函数声明
int arrows(int x, char min, char max);             //方向键指向函数声明
int cColor(int y, int x, int judge);               //箭头移动信息同步变化函数声明
void inversion(int *i, int y, int *x, int a, int b, char *s);//箭头方向倒置函数声明
void remind(struct ID *p, struct ID *head1);       //提醒函数声明

//int choice;                                    //选择全局变量函数声明
/***********创建房间信息的链表***********/
struct room {
    char num[10];                        //房间号
    char type[11];                       //房型
    char site[10];                       //位置
    char status[10];                     //状态
    char check[10];                      //入住
    char checkOut[10];                   //退房
    char inputTime[14];                  //加入时间
    int year;                            //加入的年
    int mon;                             //月
    int day;                             //日
    int days;                            //天数
    char cost[10];                       //费用
    char name[10];                       //姓名
    char tel[12];                        //电话
    char idnum[20];                      //身份证号
    struct room *next;                   //下一个结点地址
};
/************创建用户信息的链表**********/
struct ID {
    char power[10];                      //权限(管理员or普通用户)
    char account[20];                    //账号
    char key[20];                        //密码
    char keyProtect[20];                 //密保
    char check[10];                      //入住
    char checkOut[10];                   //退房
    char name[10];                       //姓名
    char idnum[20];                      //身份证号
    char num[10];                        //房间号
    char sex[3];                         //性别
    char inputName[10];                  //入住时间
    char inputTel[12];                   //入住电话
    int year;                            //入住时的年
    int mon;                             //入住时的月
    int day;                             //入住时的日
    int years;                           //离开时的年
    int month;                           //离开时的月
    int days;                            //离开时的日
    int numDay;                          //预定天数
    int daymax;                          //离开日期的天数
    int daymin;                          //入住日期的天数
    int cost;                            //房间每日的价格
    char information[100];               //信息
    struct ID *next;                    //下一个结点地址
};

/************主函数***************/
int main() {

    system("color F1");
//	load();
    struct ID *p, *head1;
    struct room *head = NULL;
    head = read();                   //房间信息读入链表中
    head1 = loginRead();
    p = login();               //登录后返回该用户信息块
    while (1) {
        if (strcmp(p->power, "管理员") == 0)   //判断用户身份并选择进入相应端口
        {
            if (manageFun(head, p, head1) == 0)          //判断用户否是退出登录
                p = login();
        } else if (user(head, p, head1) == 0)
            p = login();
    }
    return 0;
}


/*****管理端主函数******/
int manageFun(struct room *head, struct ID *p, struct ID *head1) {
    int x, i;
    char t;
    system("cls");
    if (strcmp(p->sex, "男") == 0)
        printf("尊敬的%s管理员先生欢迎您登录酒店网上订房系统\n", p->name);
    else
        printf("尊敬的%s管理员女士欢迎您登录酒店网上订房系统\n", p->name);
    system("pause");
    head = read();
    A:
    system("cls");
    write(head);
    head = read();
    head1 = loginRead();
    menuRoom(head1);
    x = 8;
    i = 0;
    while (1) {
        t = arrows(x, '0', '7');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(89, x, 0);
                    if (x == 8)
                        puts("1.添加房间信息");
                    else if (x == 10)
                        puts("2.查看房间信息");
                    else if (x == 12)
                        puts("3.修改房间信息");
                    else if (x == 14)
                        puts("4.处理客户申请");
                    else if (x == 16)
                        puts("5.删除房间信息");
                    else if (x == 18)
                        puts("6.查找房间信息");
                    else
                        puts("7.退出登录");
                    i--;
                } else
                    inversion(&i, 89, &x, 7, 22, "0.退出系统");
                break;

            case 80:
                if (i < 7) {
                    x = cColor(89, x, 1);
                    if (x == 10)
                        puts("2.查看房间信息");
                    else if (x == 12)
                        puts("3.修改房间信息");
                    else if (x == 14)
                        puts("4.处理客户申请");
                    else if (x == 16)
                        puts("5.删除房间信息");
                    else if (x == 18)
                        puts("6.查找房间信息");
                    else if (x == 20)
                        puts("7.退出登录");
                    else
                        puts("0.退出系统");
                    i++;
                } else
                    inversion(&i, 89, &x, 0, 8, "1.添加房间信息");
                break;
            case 13:
                system("cls");
                if (x == 8)
                    head = add(head);
                else if (x == 10)
                    printRoom(head, head1);
                else if (x == 12)
                    change(head);
                else if (x == 14)
                    dispose(head);
                else if (x == 16)
                    head = del(head, head1);
                else if (x == 18)
                    finds(head, head1);
                else if (x == 20)
                    return 0;
                else
                    exit(0);
                goto A;

            case 49:
                system("cls");
                head = add(head);
                goto A;
            case 50:
                system("cls");
                printRoom(head, head1);
                goto A;
            case 51:
                system("cls");
                change(head);
                goto A;
            case 52:
                system("cls");
                dispose(head);
                goto A;
            case 53:
                system("cls");
                head = del(head, head1);
                goto A;
            case 54:
                system("cls");
                finds(head, head1);
                goto A;
            case 55:
                return 0;
            case 48:
                exit(0);
        }
    }
    return 0;
}

/************房间信息按顺序从小到大顺序读取*********/
struct room *read() {
    struct room *head = NULL, *p1, *p2, *p;
    FILE *fp;
    if ((fp = fopen("房间信息", "rb")) == NULL)
        return head;
    B:
    p = (struct room *) malloc(sizeof(struct room));     //分配空间
    fread(p, sizeof(struct room), 1, fp);                 //将文件中的房间信息读取到链表中
    if (feof(fp))                                        //判断是否读到文件结尾
    {
        fclose(fp);
        return head;
    }
    if (head == NULL)                                           //由于空链表的特殊性单独读取第一个房间信息
    {
        head = p;
        p->next = NULL;
        goto B;
    }
    p1 = head;
    if (p1->next == NULL)                       //判断链表之中是否只有一个头结点
    {
        if (strcmp(p->num, p1->num) <= 0)          //比较该房号与头结点房号的大小判断此房间信息写在头结点之前还是之后
        {
            head = p;
            p->next = p1;
        } else {
            p1->next = p;
            p->next = NULL;
        }
        goto B;
    }
    p2 = p1;
    while (p2 != NULL) {
        if (strcmp(p->num, p2->num) < 0) {
            if (p2 == head) {
                head = p;
                p->next = p2;
                goto B;
            } else {
                p1->next = p;
                p->next = p2;
                goto B;
            }
        } else {
            p1 = p2;
            p2 = p2->next;
        }
    }
    p1->next = p;
    p->next = NULL;
    goto B;
}

/***********房间信息写入****************/
void write(struct room *head) {
    FILE *fp;
    struct room *p1;
    p1 = head;
    fp = fopen("房间信息", "wb");
    while (p1 != NULL)            // 遍历链表中的信息写入文件中
    {
        fwrite(p1, sizeof(struct room), 1, fp);
        p1 = p1->next;
    }
    fclose(fp);
}

/**************管理端主菜单*******/
void menuRoom(struct ID *head1) {
    struct ID *x1;
    int i = 0;
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->checkOut, "是") == 0)
            ++i;
        x1 = x1->next;
    }
    if (i > 0)
        printf("你有%d个待处理的退房申请\n\n\a", i);
    //system("pause");
    puts("红尘客栈");
    puts("2.查看房间信息");
    puts("3.修改房间信息");
    puts("4.处理客户申请");
    puts("5.删除房间信息");
    puts("6.查找房间信息");
    puts("7.退出登录");
    puts("0.退出系统");
    puts("1.添加房间信息");
}

/********添加房间信息*********/
struct room *add(struct room *head) {
    struct room *p1, *p2;
    p1 = (struct room *) malloc(sizeof(struct room));
    A:
    p2 = head;
    puts("添加房间信息");
    puts("请输入添加房间信息:");
    printf("房间号:");
    if (scanfPlus(p1->num, 1, 9, 0) == 1) //按ESC键执行返回操作if判断是否执行返回操作
        return head;
    while (p2 != NULL) {

        if (strcmp(p2->num, p1->num) == 0) {
            printf("输入房间号重复请重新输入！");
            system("cls");
            goto A;
        }
        p2 = p2->next;
    }
    printf("房型:");
    if (scanfPlus(p1->type, 1, 10, 0) == 1)
        return head;
    printf("位置:");
    if (scanfPlus(p1->site, 1, 9, 0) == 1)
        return head;
    printf("费用(每天):");
    if (scanfPlus(p1->cost, 1, 9, 0) == 1)
        return head;
    strcpy(p1->status, "空闲");
    strcpy(p1->check, "未入住");
    p1->year = 1;
    p1->days = 0;
    strcpy(p1->name, "无");
    strcpy(p1->tel, "无");
    if (head == NULL) {
        head = p1;
        p1->next = NULL;
        return head;
    }
    p2 = head;
    while (p2->next != NULL)
        p2 = p2->next;
    p2->next = p1;
    p1->next = NULL;
    printf("添加成功！");
    return head;
}

/**********浏览房间信息********/
void printRoom(struct room *head, struct ID *head1) {
    int i, n = 0;
    int s = time(0);
    int d = s / 86400 - 10956;
    struct ID *x1;
    struct room *p2;
    if (head == NULL) {
        printf("无任何房间信息！");
        return;
    }
    printf("浏览房间信息");
    p2 = head;
    while (p2 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (x1->daymin <= d && x1->daymax > d && strcmp(x1->num, p2->num) == 0) {

                strcpy(p2->status, "已被预订");
                strcpy(p2->name, x1->inputName);
                strcpy(p2->tel, x1->inputTel);
                p2->year = x1->year;
                p2->mon = x1->mon;
                p2->day = x1->day;
                p2->days = x1->numDay;
                break;
            }
            x1 = x1->next;
        }
        n++;
        p2 = p2->next;
    }
    form2(n);
    p2 = head;
    i = 0;
    while (p2 != NULL) {
        form3(p2, i);
        p2 = p2->next;
        i++;
    }
    reset(head);
    system("pause");
}

/*************修改房间信息************/
void change(struct room *head) {
    int x, i;
    char t;
    A:
    system("cls");
    write(head);
    x = 8;
    i = 0;
    cMenu();
    while (1) {
        t = arrows(x, '0', '5');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(90, x, 0);
                    if (x == 8)
                        puts("1.房间号");
                    else if (x == 10)
                        puts("2.房型");
                    else if (x == 12)
                        puts("3.位置");
                    else if (x == 14)
                        puts("4.入住");
                    else
                        puts("5.费用");
                    i--;
                } else
                    inversion(&i, 90, &x, 5, 18, "0.返回上一级界面");
                break;
            case 80:
                if (i < 5) {
                    x = cColor(90, x, 1);
                    if (x == 10)
                        puts("2.房型");
                    else if (x == 12)
                        puts("3.位置");
                    else if (x == 14)
                        puts("4.入住");
                    else if (x == 16)
                        puts("5.费用");
                    else
                        puts("0.返回上一级界面");
                    i++;
                } else
                    inversion(&i, 90, &x, 0, 8, "1.房间号");
                break;
            case 13:
                system("cls");
                if (x == 8)
                    cNum(head);
                else if (x == 10)
                    cType(head);
                else if (x == 12)
                    cSite(head);
                else if (x == 14)
                    cCheckIn(head);
                else if (x == 16)
                    cCost(head);
                else
                    return;
                goto A;
            case 49:
                system("cls");
                cNum(head);
                goto A;
            case 50:
                system("cls");
                cType(head);
                goto A;
            case 51:
                system("cls");
                cSite(head);
                goto A;
            case 52:
                system("cls");
                cCheckIn(head);
                goto A;
            case 53:
                system("cls");
                cCost(head);
                goto A;
            case 48:
                return;
        }
    }
}

/******房间信息修改********/
void cMenu() {
    puts("客栈房间信息更改");
    puts("2.房型");
    puts("3.位置");
    puts("4.入住");
    puts("5.费用");
    puts("0.返回上一级界面");
    puts("1.房间号");
}

/**********房间信息修改通式********/
struct room *amend(struct room *head) {
    struct room *p1;
    char amendNum[20];
    A:
    printf("请输入要修改房间信息的房间号:");
    if (scanfPlus(amendNum, 1, 9, 0) == 1)
        return NULL;
    p1 = head;
    while (p1 != NULL)                    //遍历链表直到遍历到要修改的房间信息的位置
    {
        if (strcmp(p1->num, amendNum) == 0) {
            printf("该房间信息如下:\n\n");
            puts("┌───────────────┬────────────────┬───────────────┬───────────────┬───────────────┐");
            puts("│               │                │               │               │               │");
            puts("├───────────────┼────────────────┼───────────────┼───────────────┼───────────────┤");
            puts("│               │                │               │               │               │");
            puts("└───────────────┴────────────────┴───────────────┴───────────────┴───────────────┘");
            printf("房间号");
            printf("房型");
            printf("位置");
            printf("入住");
            printf("费用(天)");
            printf("%s", p1->num);
            printf("%s", p1->type);
            printf("%s", p1->site);
            printf("%s", p1->check);
            printf("%s", p1->cost);
            return p1;
        }
        p1 = p1->next;
    }
    printf("未查到此房间号请重新输入");
    system("cls");
    goto A;
}

/****修改房间号******/
void cNum(struct room *head) {
    struct room *p1, *p2;
    if ((p1 = amend(head)) == NULL)
        return;
    A:
    printf("请输入修改后的房间号:");
    if (scanfPlus(p1->num, 1, 9, 0) == 1)
        return;
    p2 = head;
    while (p2 != NULL) {
        if (strcmp(p1->num, p2->num) == 0 && p1 != p2)  //判断房间号是否重复
        {
            printf("此房间号已存在请重新输入");
            system("cls");
            goto A;
        }
        p2 = p2->next;
    }
    printf("处理成功");
}

/**************修改房型**************/
void cType(struct room *head) {
    struct room *p1;
    if ((p1 = amend(head)) == NULL)
        return;
    printf("请输入修改后的房型:");
    if (scanfPlus(p1->type, 1, 19, 0) == 1)
        return;
    printf("处理成功");
}

/*****修改房间位置******/
void cSite(struct room *head) {
    struct room *p1;
    if ((p1 = amend(head)) == NULL)
        return;
    printf("请输入修改后的位置:");
    if (scanfPlus(p1->site, 1, 9, 0) == 1)
        return;
    printf("处理成功");
}

/*************修改房间入住情况***********/
void cCheckIn(struct room *head) {
    struct room *p1;
    if ((p1 = amend(head)) == NULL)
        return;
    A:
    printf("请输入修改后的入住情况:");
    if (scanfPlus(p1->check, 1, 9, 0) == 1)
        return;
    if (strcmp(p1->check, "已入住") != 0 && strcmp(p1->check, "未入住") != 0) {
        printf("非法输入请重新输入!");
        system("cls");
        goto A;
    }
    printf("处理成功");
}

/*************修改一天的费用*******************/
void cCost(struct room *head) {
    struct room *p1;
    if ((p1 = amend(head)) == NULL)
        return;
    printf("请输入修改后的费用(每天):");
    if (scanfPlus(p1->cost, 1, 15, 0) == 1)
        return;
    printf("处理成功");
}

/*******************管理端对申请进行处理******************/
void dispose(struct room *head) {
    struct ID *head1, *x1;
    struct room *p1;
    char judge[3], name[20];
    head1 = loginRead();
    x1 = head1;
    while (x1 != NULL)              //遍历客户信息检索申请
    {
        if (strcmp(x1->checkOut, "是") == 0) {
            p1 = head;
            while (p1 != NULL)          //遍历到该客户所退的房间
            {
                if (strcmp(p1->num, x1->num) == 0)
                    break;
                p1 = p1->next;
            }
            strcpy(p1->status, "已预订");
            strcpy(name, x1->name);
            strcpy(p1->name, x1->inputName);
            strcpy(p1->tel, x1->inputTel);
            p1->year = x1->year;
            p1->mon = x1->mon;
            p1->day = x1->day;
            p1->days = x1->numDay;
            A:
            form2(1);
            form3(p1, 0);

            strcpy(x1->checkOut, "已退房");
            strcpy(x1->num, "无");
            //	x1->daymax = 0;
            p1->days = 0;
            p1->year = 1;
            strcpy(p1->tel, "无");
            strcpy(p1->idnum, "无");
            strcpy(p1->status, "空闲");
            strcpy(p1->check, "未入住");
            strcpy(p1->name, "无");
            printf("处理成功");
            loginWrite(head1);

            return;
        }
        x1 = x1->next;
    }
    printf("无待处理的申请");
}

/********删除房间信息***********/
struct room *del(struct room *head, struct ID *head1) {
    struct room *p2, *p3;
    struct ID *x1;
    char delnum[20];
    if (head == NULL) {
        printf("无任何房间信息!");
        return head;
    }
    printf("请输入要删除房间信息的房间号:");
    if (scanfPlus(delnum, 1, 9, 0) == 1)
        return head;
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->num, delnum) == 0) {
            printf("该房间有人预定不能删除!");
            return head;
        }
        x1 = x1->next;
    }
    p2 = head;
    while (p2 != NULL) {
        if (strcmp(delnum, p2->num) == 0) {
            A:
            system("cls");
            form2(1);
            form3(p2, 0);

            if (p2 == head) {
                head = p2->next;
                free(p2);
                printf("删除成功!");
                return head;
            } else {
                p3->next = p2->next;
                free(p2);
                printf("删除成功!");
                return head;
            }
            return head;

        }
        p3 = p2;
        p2 = p2->next;
    }
    printf("查无此房间信息!");
    return head;
}

/**************根据房间信息进行查找*************/
void finds(struct room *head, struct ID *head1) {
    int x, i;
    char t;
    A:
    system("cls");
    x = 6;
    i = 0;
    fMenu();
    while (1) {
        t = arrows(x, '0', '9');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(92, x, 0);
                    if (x == 6)
                        puts("1.房间号");
                    else if (x == 8)
                        puts("2.房型");
                    else if (x == 10)
                        puts("3.位置");
                    else if (x == 12)
                        puts("4.房间状态");
                    else if (x == 14)
                        puts("5.入住时间");
                    else if (x == 16)
                        puts("6.天数");
                    else if (x == 18)
                        puts("7.费用");
                    else if (x == 20)
                        puts("8.入住客户姓名");
                    else if (x == 22)
                        puts("9.电话");
                    i--;
                } else
                    inversion(&i, 92, &x, 9, 24, "0.返回上一级界面");
                break;
            case 80:
                if (i < 9) {
                    x = cColor(92, x, 1);
                    if (x == 8)
                        puts("2.房型");
                    else if (x == 10)
                        puts("3.位置");
                    else if (x == 12)
                        puts("4.房间状态");
                    else if (x == 14)
                        puts("5.入住时间");
                    else if (x == 16)
                        puts("6.天数");
                    else if (x == 18)
                        puts("7.费用");
                    else if (x == 20)
                        puts("8.入住客户姓名");
                    else if (x == 22)
                        puts("9.电话");
                    else
                        puts("0.返回上一级界面");
                    i++;
                } else
                    inversion(&i, 92, &x, 0, 6, "1.房间号");
                break;
            case 13:
                system("cls");
                if (x == 6)
                    fNum(head, head1);
                else if (x == 8)
                    fType(head, head1);
                else if (x == 10)
                    fSite(head, head1);
                else if (x == 12)
                    fStatus(head, head1);
                else if (x == 14)
                    fInputTime(head, head1);
                else if (x == 16)
                    fDays(head, head1);
                else if (x == 18)
                    fCost(head, head1);
                else if (x == 20)
                    fName(head, head1);
                else if (x == 22)
                    fTel(head, head1);
                else
                    return;
                goto A;
            case 49:
                system("cls");
                fNum(head, head1);
                goto A;
            case 50:
                system("cls");
                fType(head, head1);
                goto A;
            case 51:
                system("cls");
                fSite(head, head1);
                goto A;
            case 52:
                system("cls");
                fStatus(head, head1);
                goto A;
            case 53:
                system("cls");
                fInputTime(head, head1);
                goto A;
            case 54:
                system("cls");
                fDays(head, head1);
                goto A;
            case 55:
                system("cls");
                fCost(head, head1);
                goto A;
            case 56:
                system("cls");
                fName(head, head1);
                goto A;
            case 57:
                system("cls");
                fTel(head, head1);
                goto A;
            case 48:
                return;
        }
    }
}

/***********根据不同局部房间信息查找的菜单**************/
void fMenu() {
    form(3, 10);
    puts("查询客栈房间信息");
    puts("2.房型");
    puts("3.位置");
    puts("4.房间状态");
    puts("5.入住时间");
    puts("6.天数");
    puts("7.费用");
    puts("8.入住客户姓名");
    puts("9.电话");
    puts("0.返回上一级菜单");
    puts("1.房间号");


}

/***************根据房间号进行查找********************/
void fNum(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rNum[10];
    int flag = 0, i, n = 0;
    printf("请输入待查找房间的房间号:");
    if (scanfPlus(rNum, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strstr(p1->num, rNum) != 0)
            n++;
        p1 = p1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    do {
        if (flag == 1)
            form2(i);
        p1 = head;
        i = 0;
        while (p1 != NULL) {
            if (strstr(p1->num, rNum) != 0) {
                x1 = head1;
                while (x1 != NULL) {
                    if (strcmp(p1->num, x1->num) == 0) {
                        status(p1, x1);
                        form3(p1, i);
                        i++;
                    }
                    x1 = x1->next;
                }
                if (strcmp(p1->status, "空闲") == 0) {
                    form3(p1, i);
                    i++;
                }
            }
            p1 = p1->next;
        }
        flag++;
    } while (flag == 1);
    system("pause");
}

/************根据房型进行查找*************/
void fType(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rType[10];
    int flag = 0, i, n = 0;
    printf("请输入待查找房间的房型:");
    if (scanfPlus(rType, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strstr(p1->type, rType) != 0)
            n++;
        p1 = p1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    do {
        if (flag == 1)
            form2(i);
        p1 = head;
        i = 0;
        while (p1 != NULL) {
            if (strstr(p1->type, rType) != 0) {
                x1 = head1;
                while (x1 != NULL) {
                    if (strcmp(p1->num, x1->num) == 0) {
                        status(p1, x1);
                        form3(p1, i);
                        i++;
                    }
                    x1 = x1->next;
                }
                if (strcmp(p1->status, "空闲") == 0) {
                    form3(p1, i);
                    i++;
                }
            }
            p1 = p1->next;
        }
        flag++;
    } while (flag == 1);
    system("pause");
}

/**************根据房间位置进行查找*************/
void fSite(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rSite[10];
    int flag = 0, i, n = 0;
    printf("请输入待查找房间的位置:");
    if (scanfPlus(rSite, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strstr(p1->site, rSite) != 0)
            n++;
        p1 = p1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    do {
        if (flag == 1)
            form2(i);
        p1 = head;
        i = 0;
        while (p1 != NULL) {
            if (strstr(p1->site, rSite) != 0) {
                x1 = head1;
                while (x1 != NULL) {
                    if (strcmp(p1->num, x1->num) == 0) {
                        status(p1, x1);
                        form3(p1, i);
                        i++;
                    }
                    x1 = x1->next;
                }
                if (strcmp(p1->status, "空闲") == 0) {
                    form3(p1, i);
                    i++;
                }
            }
            p1 = p1->next;
        }
        flag++;
    } while (flag == 1);
    system("pause");
}

/****************根据房间状态进行查找************/
void fStatus(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rStatus[10];
    int flag = 0, flag2 = 0, i, n = 0;
    printf("请输入待查找房间的状态:");
    if (scanfPlus(rStatus, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(p1->num, x1->num) == 0)
                strcpy(p1->status, "已预订");
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strstr(p1->status, rStatus) != 0)
            n++;
        p1 = p1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    do {
        if (flag == 1)
            form2(i);
        p1 = head;
        i = 0;
        while (p1 != NULL) {
            if (strstr(p1->status, rStatus) != 0) {
                x1 = head1;
                while (x1 != NULL) {
                    if (strcmp(p1->num, x1->num) == 0) {
                        status(p1, x1);
                        form3(p1, i);
                        i++;
                        flag2 = 1;
                    }
                    x1 = x1->next;

                }
                if (flag2 == 0) {
                    form3(p1, i);
                    i++;
                }
                flag2 = 0;
            }
            p1 = p1->next;
        }
        flag++;
    } while (flag == 1);
    system("pause");
}

/************根据入住时间进行查找***********/
void fInputTime(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    int i, n = 0;
    char tran[5];
    int year, mon, day;
    printf("请输入待查房间客户的入住时间:");
    printf("年");
    printf("月");
    printf("日");
    if (scanfPlus(tran, 1, 4, 0) == 1)
        return;
    year = atoi(tran);
    if (scanfPlus(tran, 1, 2, 0) == 1)
        return;
    mon = atoi(tran);
    if (scanfPlus(tran, 1, 2, 0) == 1)
        return;
    day = atoi(tran);
    x1 = head1;
    while (x1 != NULL) {
        if (x1->year == year && x1->mon == mon && x1->day == day)
            n++;
        x1 = x1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    form2(n);
    p1 = head;
    i = 0;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(p1->num, x1->num) == 0) {
                if (x1->year == year && x1->mon == mon && x1->day == day) {
                    status(p1, x1);
                    form3(p1, i);
                    i++;
                }
            }
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    system("pause");
}

/*****************根据入住天数进行查找**************/
void fDays(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char tran[10];
    int rDays;
    int i, n = 0;
    printf("请输入待查房间客户入住的天数:");
    if (scanfPlus(tran, 1, 9, 0) == 1)
        return;
    rDays = atoi(tran);
    x1 = head1;
    while (x1 != NULL) {
        if (x1->numDay == rDays)
            n++;
        x1 = x1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    form2(n);
    p1 = head;
    i = 0;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(p1->num, x1->num) == 0) {
                if (x1->numDay == rDays) {
                    status(p1, x1);
                    form3(p1, i);
                    i++;
                }
            }
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    system("pause");
}

/****************根据住一天的房间费用进行查找**********/
void fCost(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rCost[10];
    int flag = 0, i, n = 0;
    printf("请输入待查找房间每天的费用:");
    if (scanfPlus(rCost, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strstr(p1->cost, rCost) != 0)
            n++;
        p1 = p1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    do {
        if (flag == 1)
            form2(i);
        p1 = head;
        i = 0;
        while (p1 != NULL) {
            if (strstr(p1->cost, rCost) != 0) {
                x1 = head1;
                while (x1 != NULL) {
                    if (strcmp(p1->num, x1->num) == 0) {
                        status(p1, x1);
                        form3(p1, i);
                        i++;
                    }
                    x1 = x1->next;
                }
                if (strcmp(p1->status, "空闲") == 0) {
                    form3(p1, i);
                    i++;
                }
            }
            p1 = p1->next;
        }
        flag++;
    } while (flag == 1);
    system("pause");
}

/***********根据入住客户姓名进行查找************/
void fName(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rName[20];
    int i, n = 0;
    printf("请输入要查找的房间信息的入住客户名:");
    if (scanfPlus(rName, 1, 9, 0) == 1)
        return;
    x1 = head1;
    while (x1 != NULL) {
        if (strstr(x1->inputName, rName) != 0)
            n++;
        x1 = x1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    form2(n);
    p1 = head;
    i = 0;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(p1->num, x1->num) == 0) {
                if (strstr(x1->inputName, rName) != 0) {
                    status(p1, x1);
                    form3(p1, i);
                    i++;
                }
            }
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    system("pause");
}

/**********根据电话号码进行查找*********/
void fTel(struct room *head, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    char rTel[12];
    int i, n = 0;
    printf("请输入要查找的房间信息的电话:");
    if (scanfPlus(rTel, 1, 11, 3) == 1)
        return;
    x1 = head1;
    while (x1 != NULL) {
        if (strstr(x1->inputTel, rTel) != 0)
            n++;
        x1 = x1->next;
    }
    if (n == 0) {
        printf("未查到此房间信息!");
        return;
    }
    form2(n);
    p1 = head;
    i = 0;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(p1->num, x1->num) == 0) {
                if (strstr(x1->inputTel, rTel) != 0) {
                    status(p1, x1);
                    form3(p1, i);
                    i++;
                }
            }
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    system("pause");
}

/************登录主函数*****************/
struct ID *login() {
    struct ID *head = NULL, *p;
    head = loginRead();
    int x, i;
    char t;
    A:
    system("cls");
    loginWrite(head);
    loginMenu();
    x = 9;
    i = 0;
    while (1) {
        t = arrows(x, '0', '6');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(93, x, 0);
                    if (x == 9)
                        puts("1.登录");
                    else if (x == 11)
                        puts("2.注册");
                    else if (x == 13)
                        puts("3.注销");
                    else if (x == 15)
                        puts("4.密码找回");
                    else if (x == 17)
                        puts("5.修改密码");
                    else if (x == 19)
                        puts("6.游客登录");
                    i--;
                } else {
                    inversion(&i, 93, &x, 6, 21, "0.退出");
                }
                break;
            case 80:
                if (i < 6) {
                    x = cColor(93, x, 1);
                    if (x == 11)
                        puts("2.注册");
                    else if (x == 13)
                        puts("3.注销");
                    else if (x == 15)
                        puts("4.密码找回");
                    else if (x == 17)
                        puts("5.修改密码");
                    else if (x == 19)
                        puts("6.游客登录");
                    else
                        puts("0.退出");
                    i++;
                } else {
                    inversion(&i, 93, &x, 0, 9, "1.登录");
                }
                break;
            case 13:
                system("cls");
                if (x == 9) {
                    if ((p = sign(head)))
                        return p;            //登录后返回该账号的基本信息
                }
                if (x == 11)
                    head = accountName(head);
                if (x == 13)
                    head = logout(head);
                if (x == 15)
                    signFind(head);
                if (x == 17)
                    changePassword(head);
                if (x == 19)
                    visitor();
                if (x == 21)
                    exit(0);
                goto A;
            case 49:
                system("cls");
                if ((p = sign(head)))
                    return p;
                goto A;
            case 50:
                system("cls");
                head = accountName(head);
                goto A;
            case 51:
                system("cls");
                head = logout(head);
                goto A;
            case 52:
                system("cls");
                signFind(head);
                goto A;
            case 53:
                system("cls");
                changePassword(head);
                goto A;
            case 54:
                system("cls");
                visitor();
                goto A;
            case 48:
                exit(0);
        }
    }
}

/******登录界面菜单********/
void loginMenu() {
    system("cls");
    form(6, 7);
    puts("红尘客栈");
    puts("2.注册");
    puts("3.注销");
    puts("4.密码找回");
    puts("5.修改密码");
    puts("6.游客登录");
    puts("0.退出");
    puts("注释:在录入数据的过程中按ESC键可直接取消录入返回上一级");
    puts("1.登录");

}

/************将账户信息写入文件*********/
void loginWrite(struct ID *head) {
    FILE *fp;
    struct ID *p1;
    fp = fopen("用户", "wb");
    if (head == NULL)
        return;
    p1 = head;

    while (p1 != NULL)           //遍历链表依次将账户信息写入文件中
    {
        fwrite(p1, sizeof(struct ID), 1, fp);
        p1 = p1->next;
    }
    fclose(fp);
}

/***************将文件中的账户信息读出************/
struct ID *loginRead() {
    FILE *fp;
    char ch;
    struct ID *head = NULL, *p1, *p2;          //将头指针初始化为空
    if ((fp = fopen("用户", "rb")) == NULL)
        return head;
    A:
    p1 = (struct ID *) malloc(sizeof(struct ID));
    fread(p1, sizeof(struct ID), 1, fp);
    if (feof(fp))          //判断文件是否为空
    {
        fclose(fp);
        return head;
    }

    if (head == NULL)                   //因空链表特殊性，头结点需单独读取
    {
        head = p1;
        p1->next = NULL;
        p2 = p1;
        goto A;
    }
    p2->next = p1;                       //遍历链表依次从文件中读取数据到链表
    p1->next = NULL;
    p2 = p1;
    goto A;
}

/***********登录**********/
struct ID *sign(struct ID *head) {
    int i = 2;
    struct ID *p1 = NULL, *p2;
    char accountt[20];
    char key1[20];
    char testGetCode[7];
    char code[7];
    if (head == NULL) {
        printf("未注册任何账号请注册后重试!");
        return NULL;
    }
    A:
    form(9, 2);
    puts("登录");
    puts("账号:");
    puts("密码:");
    if (p1 == NULL) {
        if (scanfPlus(accountt, 1, 18, 0) == 1)
            return NULL;
    } else
        puts(accountt);
    p1 = head;
    while (1) {
        if (strcmp(accountt, p1->account) == 0)
            break;
        p1 = p1->next;
        if (p1 == NULL) {
            printf("无此账号请重新输入");
            system("cls");
            goto A;
        }
    }
    if (scanfPlus(key1, 1, 18, 1) == 1)
        return NULL;
    C:
    system("cls");
    form(9, 2);
    printf("验证");
    printf("验证码:%s\n", randStr(testGetCode, 4));
    printf("请输入验证码(区分大小写):");
    if (scanfPlus(code, 1, 4, 0) == 1)
        return NULL;
    if (strcmp(testGetCode, code) != 0) {
        if (i > 0) {
            system("cls");
            printf("验证码输入错误你还有%d次机会请重新输入!", i);
            i--;
            system("pause");
        } else {
            printf("验证码输入错误你已经没有机会了!即将返回上一级界面");
            system("cls");
            return NULL;
        }
        goto C;
    }
    if (strcmp(key1, p1->key) != 0) {
        if (i > 0) {
            system("cls");
            printf("密码输入错误你还有%d次机会请重新输入！", i);
            system("pause");
            system("cls");
            i--;
        } else {
            printf("密码输入错误你已经没有机会了!即将返回上一级界面");
            system("cls");
            return NULL;
        }
        goto A;
    }
    return p1;
}

/*************注册***********/
struct ID *accountName(struct ID *head) {
    struct ID *p1, *p2;
    char keyy[20];
    char protect[20];
    char numm[20], code[20];
    p1 = (struct ID *) malloc(sizeof(struct ID));
    printf("请输入注册账户等级(管理员or普通用户):");
    C:
    if (scanfPlus(p1->power, 1, 10, 0) == 1)
        return head;
    if (strcmp(p1->power, "管理员") != 0 && strcmp(p1->power, "普通用户") != 0) {
        system("cls");
        printf("非法输入！请重新输入:");
        goto C;
    }
    if (strcmp(p1->power, "管理员") == 0) {
        G:
        printf("请输入激活码:");
        if (scanfPlus(code, 1, 19, 3) == 1)
            return head;
        if (strcmp(code, "258711551") != 0) {
            printf("输入激活码错误!请重新输入");
            system("cls");
            goto G;
        }
    }

    B:
    printf("请输入你要注册的账号(账号为8~18位、汉字算两位)：");
    if (scanfPlus(p1->account, 8, 18, 0) == 1)
        return head;
    p2 = head;
    while (1) {
        if (p2 == NULL)
            break;
        if (strcmp(p2->account, p1->account) == 0) {
            printf("该账号已被注册！请重新输入");
            system("cls");
            goto B;
        }
        p2 = p2->next;
    }
    printf("请输入身份证上姓名:");
    if (scanfPlus(p1->name, 1, 18, 0) == 1)
        return head;
    F:
    printf("请输入性别(男or女):");
    if (scanfPlus(p1->sex, 1, 2, 0) == 1)
        return head;
    if (strcmp(p1->sex, "男") != 0 && strcmp(p1->sex, "女") != 0) {
        printf("非法输入请重新输入!");
        system("cls");
        goto F;
    }
    E:
    printf("请输入身份证号(18位):");
    if (scanfPlus(p1->idnum, 18, 18, 1) == 1)
        return head;
    p2 = head;
    while (p2 != NULL) {
        if (strcmp(p2->idnum, p1->idnum) == 0) {
            printf("该身份证号已被其它账号绑定请重新输入!");
            system("cls");
            goto E;
        }
        p2 = p2->next;
    }
    printf("请再次输入身份证号:");
    if (scanfPlus(numm, 18, 18, 1) == 1)
        return head;
    if (strcmp(p1->idnum, numm) != 0) {
        printf("两次输入身份证号不一致请重新输入！");
        system("cls");
        goto E;
    }
    A:
    printf("请输入密码(8~18位):");
    if (scanfPlus(p1->key, 8, 18, 1) == 1)
        return head;
    printf("请再次输入密码:");
    if (scanfPlus(keyy, 8, 18, 1) == 1)
        return head;
    if (strcmp(keyy, p1->key) != 0) {
        printf("两次输入密码不一致请重新输入！");
        system("cls");
        goto A;
    }
    puts("密保设置:");
    D:
    printf("你最喜欢的东西是什么？(密保为8~18位、汉字算两位):");
    if (scanfPlus(p1->keyProtect, 1, 18, 0) == 1)
        return head;
    printf("请再次输入你最喜欢的东西:");
    if (scanfPlus(protect, 1, 18, 0) == 1)
        return head;
    if (strcmp(p1->keyProtect, protect) != 0) {
        system("cls");
        printf("两次输入密保不一致请重新输入！");
        goto D;
    }
    printf("注册成功");
    //p1->daymax = 0;
    p2 = head;
    if (head == NULL) {
        head = p1;
        p1->next = NULL;
        return head;
    }
    while (p2->next != NULL)
        p2 = p2->next;
    p2->next = p1;
    p1->next = NULL;
    return head;
}

/***************注销**********/
struct ID *logout(struct ID *head) {
    struct ID *p1, *p2, *p3;
    char delaccount[20];
    char key1[20];
    char protect[20];
    int i = 2;
    if (head == NULL) {
        printf("没有可注销的账户!");
        return head;
    }
    A:
    printf("请输入注销账户:");
    if (scanfPlus(delaccount, 1, 18, 0) == 1)
        return head;
    p2 = head;
    while (p2 != NULL) {
        if (strcmp(delaccount, p2->account) == 0) {
            if (p2 == head) {
                printf("请输入账户密码:");
                B:
                if (scanfPlus(key1, 1, 18, 1) == 1)
                    return head;
                if (strcmp(key1, p2->key) != 0) {
                    printf("输入错误!你还有%d次机会:", i);
                    if (i == 0) {
                        printf("密码输入错误你已经没有机会了!即将返回上一级菜单");
                        return head;
                    }
                    i--;
                    goto B;
                }
                printf("你最喜欢的东西是什么:");
                E:
                if (scanfPlus(protect, 1, 18, 0) == 1)
                    return head;
                if (strcmp(protect, p2->keyProtect) != 0) {
                    printf("密保答案输入错误!你还有%d次机会请再次输入:", i);
                    if (i == 0) {
                        printf("密保输入错误你已经没有机会了!即将返回上一级菜单");
                        return head;
                    }
                    i--;
                    goto E;
                }
                printf("注销成功!");
                head = p2->next;
                free(p2);
                return head;
            }
            printf("请输入账户密码:");
            C:
            if (scanfPlus(key1, 1, 18, 1) == 1)
                return head;
            if (strcmp(key1, p2->key) != 0) {
                printf("输入错误!你还有%d次机会请再次输入:", i);
                if (i == 0) {
                    printf("密码输入错误你已经没有机会了!即将返回上一级菜单");
                    return head;
                }
                i--;
                goto C;
            }
            i = 2;
            printf("你最喜欢的东西是什么:");
            D:
            if (scanfPlus(protect, 1, 18, 0) == 1)
                return head;
            if (strcmp(protect, p2->keyProtect) != 0) {
                printf("密保答案输入错误!你还有%d次机会请再次输入:", i);
                if (i == 0) {
                    printf("密保输入错误你已经没有机会了!即将返回上一级菜单");
                    return head;
                }
                i--;
                goto D;
            }
            p3->next = p2->next;
            free(p2);
            printf("注销成功!");
            return head;
        }
        p3 = p2;
        p2 = p2->next;
    }
    printf("注销失败!为查询到该账户请重新输入");
    system("cls");
    goto A;
    return head;
}

/**************密码找回**********/
void signFind(struct ID *head) {
    struct ID *p1;
    char accountt[20];
    char protect[20], key1[20];
    int i = 2;
    if (head == NULL) {
        printf("无任何账号信息");
        return;
    }
    printf("请输入你要找回密码的账号:");
    if (scanfPlus(accountt, 1, 18, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(accountt, p1->account) == 0) {
            puts("密保问题:");
            puts("你最喜欢的东西是什么?");
            printf("请输入答案:");
            A:
            if (scanfPlus(protect, 1, 18, 0) == 1)
                return;
            if (strcmp(protect, p1->keyProtect) != 0) {
                printf("密保答案输入错误!你还有%d次机会请再次输入:", i);
                if (i == 0) {
                    printf("密保输入错误你已经没有机会了!即将返回上一级菜单");
                    return;
                }
                i--;
                goto A;
            }
            B:
            printf("请输入新密码(密码8~18位):");
            if (scanfPlus(p1->key, 8, 18, 1) == 1)
                return;
            printf("请再次输入新密码:");
            if (scanfPlus(key1, 8, 18, 1) == 1)
                return;
            if (strcmp(p1->key, key1) != 0) {
                printf("两次输入密码不一致请重新输入");
                system("cls");
                goto B;
            }
            printf("注销成功");
            return;
        }
        p1 = p1->next;
    }
    printf("未检索到此账号请检查是否输入正确!");
}

/****************修改密码**********/
void changePassword(struct ID *head) {
    int i = 2;
    char accountt[20];
    char key1[20];
    struct ID *p1;
    printf("请输入账号:");
    if (scanfPlus(accountt, 8, 18, 0) == 1)
        return;
    if (head == NULL) {
        printf("无任何账号信息");
        return;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(accountt, p1->account) == 0) {
            printf("请输入该账号密码:");
            A:
            if (scanfPlus(key1, 8, 18, 1) == 1)
                return;
            if (strcmp(key1, p1->key) != 0) {
                printf("输入错误!你还有%d次机会请再次输入:", i);
                if (i == 0) {
                    printf("密码输入错误你已经没有机会了!即将返回上一级菜单");
                    return;
                }
                i--;
                goto A;
            }
            printf("请输入新密码(密码为8~18位):");
            B:
            if (scanfPlus(p1->key, 8, 18, 1) == 1)
                return;
            printf("请再次输入新密码:");
            if (scanfPlus(key1, 8, 18, 1) == 1)
                return;
            if (strcmp(p1->key, key1) != 0) {
                printf("两次输入密码不一致请重新输入！");
                system("cls");
                goto B;
            }
            return;
        }
        p1 = p1->next;
    }
    printf("未检索到此账号请检查是否输入正确");
}

char *randStr(char str[], int n) {
    int i, random;
    char strArray[59] = "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
    srand(time(0)); //从当前时间设置随机数种子
    for (i = 0; i < n; i++) {

        random = rand() % 58;
        str[i] = strArray[random];     //从创建的数组中随机取数
    }
    str[n] = '\0';
    return str;                          //返回数组首地址
}

/******************用户端*****************/
int user(struct room *head, struct ID *p, struct ID *head1) {
    struct room *p1;
    int x, i = 0;
    char t;
    system("cls");
    if (strcmp(p->sex, "男") == 0)
        printf("尊敬的%s先生欢迎您光临红尘客栈\n", p->name);
    else
        printf("尊敬的%s女士欢迎您光临红尘客栈\n", p->name);
    system("pause");
    system("cls");
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->num, p->num) == 0) {
            remind(p, head1);
            break;
        }
        p1 = p1->next;
    }

    A:
    system("cls");
    umenu(p);
    head = read();
    head1 = loginRead();
    x = 12;
    while (1) {
        t = arrows(x, '0', '5');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(91, x, 0);
                    if (x == 12)
                        puts("1.订房");
                    else if (x == 14)
                        puts("2.退房");
                    else if (x == 16)
                        puts("3.查看自己的订房信息");
                    else if (x == 18)
                        puts("4.修改自己的订房信息");
                    else
                        puts("5.退出登录");
                    i--;
                } else {
                    inversion(&i, 91, &x, 5, 22, "0.退出系统");
                }
                break;

            case 80:
                if (i < 5) {
                    x = cColor(91, x, 1);
                    if (x == 14)
                        puts("2.退房");
                    else if (x == 16)
                        puts("3.查看自己的订房信息");
                    else if (x == 18)
                        puts("4.修改自己的订房信息");
                    else if (x == 20)
                        puts("5.退出登录");
                    else
                        puts("0.退出系统");
                    i++;
                } else {
                    inversion(&i, 91, &x, 0, 12, "1.订房");
                }
                break;
            case 13:
                system("cls");
                if (x == 12)
                    reserve(head, p, head1);
                else if (x == 14) {
                    out(head, p, head1);
                    loginWrite(head1);
                } else if (x == 16)
                    userLook(head, p, head1);
                else if (x == 18)
                    userChange(head, p, head1);
                else if (x == 20)
                    return 0;
                else
                    exit(0);
                goto A;

            case 49:
                system("cls");
                reserve(head, p, head1);
                goto A;
            case 50:
                system("cls");
                out(head, p, head1);
                loginWrite(head1);
                goto A;
            case 51:
                system("cls");
                userLook(head, p, head1);
                goto A;
            case 52:
                system("cls");
                userChange(head, p, head1);
                goto A;
            case 53:
                return 0;
            case 48:
                exit(0);
        }
    }

}

/******************用户端菜单**************************/
void umenu(struct ID *p) {
    struct ID *head, *p1;
    char name[10];
    head = loginRead();
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p->account, p1->account) == 0)
            break;
        p1 = p1->next;
    }
    if (strcmp(p1->checkOut, "已退房") == 0) {
        printf("你提交的退房申请已通过!欢迎你再次下榻本客栈!");
        strcpy(p1->checkOut, "否");
        loginWrite(head);
        system("cls");
    }
    if (strcmp(p1->checkOut, "退房失败") == 0) {
        puts("你的退房申请被驳回了原因如下:");
        puts(p1->information);
        printf("如有必要请联系管理员解决相关问题管理员电话为:xxxxxxxxxxx\n\n");
        strcpy(p1->checkOut, "否");
        loginWrite(head);
        system("pause");
        system("cls");
    }
    form(9, 6);
    puts("红尘客栈");
    puts("2.退房");
    puts("3.查看自己的订房信息");
    puts("4.修改自己的订房信息");
    puts("5.退出登录");
    puts("0.退出系统");
    puts("1.订房");
}

/****************用户预订房间***************/
void reserve(struct room *head, struct ID *p, struct ID *head1) {
    struct room *p1, *p2;
    struct ID *x1;
    char tran[10];
    char rNum[10];
    int flag = 1, n = 0, i, d;
    int times[2][13] =
            {
                    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
            };
    if (head == NULL) {
        printf("该客栈没录入房间信息请稍后重试!");
        return;
    }
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->account, p->account) == 0)
            break;
        x1 = x1->next;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->num, x1->num) == 0) {
            printf("你已经预定了客栈房间请勿重复预定!");
            return;
        }
        n++;
        p1 = p1->next;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->status, "已被预订") == 0)
            strcpy(p1->status, "空闲");
        p1 = p1->next;
    }
    puts("请输入你要入住的日期:");
    printf("年");
    printf("月");
    printf("日");
    if (scanfPlus(tran, 4, 4, 3) == 1)
        return;
    p->years = p->year = atoi(tran);
    if (scanfPlus(tran, 1, 2, 3) == 1)
        return;
    p->month = p->mon = atoi(tran);
    if (scanfPlus(tran, 1, 2, 3) == 1)
        return;
    d = p->day = atoi(tran);
    B:
    printf("请输入入住天数:");
    if (scanfPlus(tran, 1, 15, 3) == 1)
        return;
    p->days = p->numDay = atoi(tran);
    if (p->days == 0) {
        printf("非法输入请重新输入!");
        system("cls");
        goto B;
    }
    while (1) {
        i = p->years % 4 == 0 && p->years % 100 != 0 || p->years % 400 == 0;
        if (p->days + d > times[i][p->month]) {
            p->days = p->days + d - times[i][p->month];
            if (p->month != 12) p->month++;
            else {
                p->years++;
                p->month = 1;
            }
            d = 0;
        }
        if (d == 0)
            break;
        else {
            p->days = p->days + d;
            break;
        }
    }
    p = tranday(p);
    p1 = head;
    while (p1 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (strcmp(x1->num, p1->num) == 0) {

                if (p->daymin < x1->daymin && p->daymax > x1->daymin)
                    strcpy(p1->status, "已被预订");
                else if (p->daymin > x1->daymin && p->daymax < x1->daymax)
                    strcpy(p1->status, "已被预订");
                else if (p->daymin < x1->daymax && p->daymax > x1->daymax)
                    strcpy(p1->status, "已被预订");
                else if (p->daymin == x1->daymin)
                    strcpy(p1->status, "已被预订");
            }
            x1 = x1->next;
        }
        p1 = p1->next;
    }
    A:
    i = form4(n, head);
    printf("请输入你想要的订的房间的房间号:");
    if (scanfPlus(rNum, 1, 9, 0) == 1)
        return;
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->num, rNum) == 0) {
            if (strcmp(p1->status, "空闲") != 0) {
                flag = 2;
                break;
            } else {
                flag = 0;
                break;
            }

        }
        p1 = p1->next;
    }
    if (flag == 1) {
        printf("未查到此房间信息请重新输入!");
        system("cls");
        goto A;
    }
    if (flag == 2) {
        printf("该房间已被预定请重新输入!");
        system("cls");
        goto A;
    }
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->idnum, p->idnum) == 0) {
            strcpy(x1->num, p->num);
            x1->year = p->year;
            x1->years = p->years;
            x1->mon = p->mon;
            x1->month = p->month;
            x1->day = p->day;
            x1->days = p->days;
            x1->daymin = p->daymin;
            x1->daymax = p->daymax;
            x1->numDay = p->numDay;
            break;
        }
        x1 = x1->next;
    }
    puts("请填写以下基本信息:");
    printf("姓名:");
    if (scanfPlus(x1->inputName, 1, 9, 0) == 1)
        return;
    printf("电话:");
    if (scanfPlus(x1->inputTel, 11, 11, 3) == 1)
        return;

    printf("订房成功!");
    strcpy(x1->num, rNum);
    write(head);
    loginWrite(head1);
}

/**************用户退房间*************/
void out(struct room *head, struct ID *p, struct ID *head1) {
    struct room *p1, *p2;
    struct ID *x1;
    char judge[3];
    int cost;
    int s = time(0);
    int d = s / 86400 - 10957;
    if (head == NULL) {
        printf("管理员还未录入任何房间信息!");
        return;
    }
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->idnum, p->idnum) == 0)
            break;
        x1 = x1->next;
    }
    if (strcmp(x1->checkOut, "是") == 0) {
        printf("你已申请退房请等待管理员审核!");
        return;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->num, x1->num) == 0) {
            strcpy(p1->name, x1->inputName);
            strcpy(p1->tel, x1->inputTel);
            strcpy(p1->status, "已被预订");
            p1->year = x1->year;
            p1->mon = x1->mon;
            p1->day = x1->day;
            p1->days = x1->numDay;
            A:
            printf("请核对你的房间信息是否正确\n");
            form2(1);
            form3(p1, 0);
            s = time(0);
            d = s / 86400 - 10956;
            cost = atoi(p1->cost);
            x1->cost = (d - x1->daymin) * cost;
            if (x1->daymax < d)
                printf("你已经超过了你的预定天数你的住宿费用为%d元", x1->cost);
            else if (x1->daymax > d)
                printf("你的住宿时间还未到入现在退房住宿费用为%d元", x1->cost);
            else printf("你的住宿费用为%d元", x1->cost);

            strcpy(x1->checkOut, "是");
            printf("申请已提交请等待管理员审核!");
        }
        p1 = p1->next;
    }
    printf("你还未预定任何房间!");
}

void userLook(struct room *head, struct ID *p, struct ID *head1) {
    struct ID *x1;
    struct room *p1;
    p1 = head;
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->account, p->account) == 0)
            break;
        x1 = x1->next;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(x1->num, p1->num) == 0)
            break;
        p1 = p1->next;
    }
    if (p1 == NULL) {
        printf("你还未预定任何房间请预定后再查看!");
        return;
    }
    status(p1, x1);
    form2(1);
    form3(p1, 0);
    system("pause");
}

/******用户信息更改*******/
void userChange(struct room *head, struct ID *p, struct ID *head1) {
    int x = 11, i = 0;
    char t;
    A:
    system("cls");
    loginWrite(head1);
    head1 = loginRead();
    userChangeMenu();
    while (1) {
        t = arrows(x, '0', '3');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(90, x, 0);
                    if (x == 11)
                        puts("1.入住时间和天数");
                    else if (x == 13)
                        puts("2.入住客户姓名");
                    else
                        puts("3.电话");
                    i--;
                } else
                    inversion(&i, 90, &x, 3, 17, "0.返回上一级界面");
                break;
            case 80:
                if (i < 3) {
                    x = cColor(90, x, 1);
                    if (x == 13)
                        puts("2.入住客户姓名");
                    else if (x == 15)
                        puts("3.电话");
                    else
                        puts("0.返回上一级界面");
                    i++;
                } else
                    inversion(&i, 90, &x, 0, 11, "1.入住时间和天数");
                break;
            case 13:
                system("cls");
                if (x == 11)
                    cInputTime(head, p, head1);
                else if (x == 13)
                    cInputName(head, p, head1);
                else if (x == 15)
                    cUserTel(head, p, head1);
                else
                    return;
                goto A;
            case 49:
                system("cls");
                cInputTime(head, p, head1);
                goto A;
            case 50:
                system("cls");
                cInputName(head, p, head1);
                goto A;
            case 51:
                system("cls");
                cUserTel(head, p, head1);
                goto A;
            case 48:
                return;
        }
    }
}

/*************用户信息更改菜单********/
void userChangeMenu() {
    form(8, 4);
    puts("客栈客户信息更改");
    puts("2.入住客户姓名");
    puts("3.电话");
    puts("0.返回上一级界面");
    puts("1.入住时间和天数");
}

struct ID *userAmend(struct room *head, struct ID *p, struct ID *head1) {
    struct room *p1;
    struct ID *x1;
    int flag = 0;
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->idnum, p->idnum) == 0)
            break;
        x1 = x1->next;
        flag = 1;
    }
    if (flag == 1) {
        printf("你还未预定任何房间!");
        return NULL;
    }
    p1 = head;
    while (p1 != NULL) {
        if (strcmp(p1->num, x1->num) == 0) {
            status(p1, x1);
            break;
        }
        p1 = p1->next;
    }
    printf("你的订房信息如下");
    form2(1);
    form3(p1, 0);
    return x1;
}

/***********用户入住时间修改***********/
void cInputTime(struct room *head, struct ID *p, struct ID *head1) {
    struct ID *x1, *x2;
    struct room *p1;
    char tran[5];
    int year, mon, day;
    int years, month, days, numDay;
    int daymin = 0, daymax = 0;
    int i;
    int leap;
    int times[2][13] =
            {
                    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
            };
    if ((x1 = userAmend(head, p, head1)) == NULL)
        return;
    printf("请输入修改后的入住时间:");
    printf("年");
    printf("月");
    printf("日");
    if (scanfPlus(tran, 4, 4, 3) == 1)
        return;
    years = year = atoi(tran);
    if (scanfPlus(tran, 1, 2, 3) == 1)
        return;
    month = mon = atoi(tran);
    if (scanfPlus(tran, 1, 2, 3) == 1)
        return;
    days = day = atoi(tran);
    A:
    printf("请输入修改后天天数:");
    if (scanfPlus(tran, 1, 9, 3) == 1)
        return;
    numDay = atoi(tran);
    if (numDay == 0) {
        printf("非法输入请重新输入！");
        system("cls");
        goto A;
    }
    while (1) {
        i = years % 4 == 0 && years % 100 != 0 || years % 400 == 0;
        if (days + numDay > times[i][p->month]) {
            days = days + numDay - times[i][p->month];
            if (month != 12) month++;
            else {
                years++;
                month = 1;
            }
            numDay = 0;
        }
        if (numDay == 0)
            break;
        else {
            days = days + numDay;
            break;
        }
    }
    for (i = 2000; i < p->year; i++) {
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
            daymin += 366;
        else
            daymin += 365;
    }
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    for (i = 1; i < mon; i++)
        daymin += times[leap][i];
    daymin += day;
    for (i = 2000; i < years; i++) {
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
            daymax += 366;
        else
            daymax += 365;
    }
    leap = years % 4 == 0 && years % 100 != 0 || years % 400 == 0;
    for (i = 1; i < month; i++)
        daymax += times[leap][i];
    daymax += days;
    p1 = head;
    while (p1 != NULL) {
        x2 = head1;
        while (x2 != NULL) {
            if (strcmp(x2->num, p1->num) == 0) {

                if (daymin < x2->daymin && daymax > x2->daymin && x2->num != x1->num) {
                    printf("修改失败该时间段房间已被预定!");
                    return;
                } else if (daymin > x1->daymin && daymax < x2->daymax && x2->num != x1->num) {
                    printf("修改失败该时间段房间已被预定!");
                    return;
                } else if (daymin < x2->daymax && daymax > x2->daymax && x2->num != x1->num) {
                    printf("修改失败该时间段房间已被预定!");
                    return;
                } else if (daymin == x2->daymin && x2 != p && x2->num != x1->num) {
                    printf("修改失败该时间段房间已被预定!!");
                    return;
                }

            }
            x2 = x2->next;
        }
        p1 = p1->next;
    }
    x1->year = year;
    x1->mon = mon;
    x1->day = day;
    x1->numDay = numDay;
    x1->daymin = daymin;
    x1->daymax = daymax;
    printf("修改成功!");
}

/**********用户入住姓名修改***********/
void cInputName(struct room *head, struct ID *p, struct ID *head1) {
    struct ID *x1;
    if ((x1 = userAmend(head, p, head1)) == NULL)
        return;
    printf("请输入修改后的入住客户姓名:");
    if (scanfPlus(x1->inputName, 1, 9, 0) == 0)
        return;
    printf("修改成功!");
}

/**********用户入住电话修改**************/
void cUserTel(struct room *head, struct ID *p, struct ID *head1) {
    struct ID *x1;
    if ((x1 = userAmend(head, p, head1)) == NULL)
        return;
    printf("请输入修改后的电话:");
    if (scanfPlus(x1->inputName, 1, 9, 0) == 0)
        return;
    printf("修改成功!");
}

/***************游客端*************/
void visitor() {
    int x, i;
    char t;
    A:
    system("cls");
    x = 12;
    i = 0;
    viewMenu();
    while (1) {
        t = arrows(x, '0', '2');
        switch (t) {
            case 72:
                if (i > 0) {
                    x = cColor(89, x, 0);
                    if (x == 12)
                        puts("1.查看房间信息");
                    else
                        puts("2.登录");
                    i--;
                } else
                    inversion(&i, 89, &x, 2, 16, "0.退出系统");
                break;
            case 80:
                if (i < 2) {
                    x = cColor(89, x, 1);
                    if (x == 14)
                        puts("2.登录");
                    else
                        puts("0.退出系统");
                    i++;
                } else
                    inversion(&i, 89, &x, 0, 12, "1.查看房间信息");
                break;
            case 13:
                system("cls");
                if (x == 12)
                    viewLook();
                else if (x == 14)
                    return;
                else
                    exit(0);
                goto A;
            case 49:
                system("cls");
                viewLook();
                goto A;
            case 50:
                return;
            case 48:
                exit(0);
        }
    }
}

/**************游客菜单***************/
void viewMenu() {
    system("cls");
    puts("亲！欢迎您进光临本酒店");
    puts("2.登录");
    puts("0.退出系统");
    puts("1.查看房间信息");
}

/***************游客端房间信息查看******************/
void viewLook() {
    int i, n = 0;
    int s = time(0);
    int d = s / 86400 - 10957;
    struct ID *head1, *x1, *x2;
    struct room *head, *p1, *p2;
    head = read();
    head1 = loginRead();
    if (head == NULL) {
        system("cls");
        printf("无任何房间信息!");
        system("pause");
        return;
    }
    printf("浏览房间信息");
    p2 = head;
    while (p2 != NULL) {
        x1 = head1;
        while (x1 != NULL) {
            if (x1->daymin <= d && x1->daymax > d && strcmp(x1->num, p2->num) == 0) {
                strcpy(p2->status, "已被预订");
                break;
            }
            x1 = x1->next;
        }
        n++;
        p2 = p2->next;
    }
    i = form4(n, head);
    p2 = head;
    while (p2 != NULL) {
        strcpy(p2->status, "空闲");
        p2 = p2->next;
    }
    system("pause");
}

/***********改进版输入函数************/
int scanfPlus(char *st, int m, int n, int x)     //st该字符串变量名，m代表该字符串最短长度，n代表该字符串最长长度,x代表以什么形式显示字符串
{
    int i = 0;
    char ch;
    while (1) {
        ch = getchar();
        if (ch == 27)        // 判断输入是否为ESC键
            return 1;
        if (ch == 13)         //判断输入是否为回车键
        {
            if (i > m - 1) {
                st[i] = '\0';
                printf("\n");
                return 0;
            }
            continue;
        }
        if (ch == 8)        //判断输入是否为BACKSPACE键
        {
            if (i > 0) {
                if (st[i - 1] < 0)  //判断输入BACKSPACE键前一个键是否为汉字
                {
                    printf("\b\b  \b\b");  //汉字占两个字节
                    i = i - 2;
                } else {
                    printf("\b \b");
                    i--;
                }
            }
            continue;
        }
        if (ch == -32) {
            getchar();
            continue;
        }
        if (ch == 9)
            continue;
        if (ch < 0 && x == 1)       //当x=1时只能输入字符不能输入汉字
            continue;
        if (x == 3)                 //当x=3时只能输入数字
            if (ch < 47 || ch > 58)
                continue;
        if (i < n) {
            if (x == 0 || x == 3) printf("%c", ch);
            else printf("*");    //当x=1 是用"*"表示输入
            st[i] = ch;
            i++;
        } else
            continue;
    }
}

/****************加载进度条**********/
void load() {
    float p; //进度条刻度
    float progLen; //程序总耗时
    int i, j;
    progLen = 15.0;
    for (i = 1; i <= progLen; i++) {
        system("cls");
        p = (i / progLen) * LEN; //计算程序当前进度之于进度条的比例
        for (j = 1; j <= (int) (p + 0.5); j++) //打印已执行刻度
            printf("■");
        for (j = 1; j <= LEN - (int) (p + 0.5); j++) //打印待执行刻度
        {
            printf("□");
        }
        printf(" %.1f%%", (i / progLen) * 100); //计算程序当前进度的百分比
    }
    // putchar ('\n');
    printf("加载完成");
    // getch ();
}

/*********房间完整信息表格打印************/
void form2(int n) {
    int i;
    puts("┌───────────────┬────────────────┬───────────────┬───────────────┬───────────────┬───────────────┬───────────────┬───────────────┬───────────────┬───────────────┐");
    puts("│               │                │               │               │               │               │               │               │               │               │");
    for (i = 0; i < n; i++) {
        puts("├───────────────┼────────────────┼───────────────┼───────────────┼───────────────┼───────────────┼───────────────┼───────────────┼───────────────┼───────────────┤");
        puts("│               │                │               │               │               │               │               │               │               │               │");
    }
    puts("└───────────────┴────────────────┴───────────────┴───────────────┴───────────────┴───────────────┴───────────────┴───────────────┴───────────────┴───────────────┘");
    printf("房间号");
    printf("房型");
    printf("位置");
    printf("房间状态");
    printf("入住");
    printf("入住时间");
    printf("天数");
    printf("费用(天)");
    printf("入住客户");
    printf("电话");
}

/**************房间完整信息打印***********/
void form3(struct room *p1, int i) {
    printf("%s", p1->num);
    printf("%s", p1->type);
    printf("%s", p1->site);
    printf("%s", p1->status);
    printf("%s", p1->check);
    if (p1->year == 1)
        printf("无");
    else {
        printf("%d", p1->year);
        fputs("-", stdout);
        printf("%d", p1->mon);
        fputs("-", stdout);
        printf("%d", p1->day);
    }
    printf("%d", p1->days);
    printf("%s", p1->cost);
    printf("%s", p1->name);
    printf("%s", p1->tel);
}

/******房间部分信息表格及数据打印******/
int form4(int n, struct room *head) {
    struct room *p2;
    int i;
    puts("┌───────────────┬────────────────┬───────────────┬───────────────┬───────────────┐");
    puts("│               │                │               │               │               │");
    for (i = 0; i < n; i++) {
        puts("├───────────────┼────────────────┼───────────────┼───────────────┼───────────────┤");
        puts("│               │                │               │               │               │");
    }
    puts("└───────────────┴────────────────┴───────────────┴───────────────┴───────────────┘");
    printf("房间号\t房型\t位置\t房间状态\t费用(天)");
    p2 = head;
    i = 0;
    while (p2 != NULL) {
        printf("%s", p2->num);
        printf("%s", p2->type);
        printf("%s", p2->site);
        printf("%s", p2->status);
        printf("%s", p2->cost);
        p2 = p2->next;
        i++;
    }
    return i;
}

/*************将日期转化为天数**********/
struct ID *tranday(struct ID *p) {
    int i;
    int leap;
    int times[2][13] =
            {
                    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
                    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
            };
    p->daymin = 0;
    p->daymax = 0;
    for (i = 2000; i < p->year; i++) {
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
            p->daymin += 366;
        else
            p->daymin += 365;

    }
    leap = p->year % 4 == 0 && p->year % 100 != 0 || p->year % 400 == 0;
    for (i = 1; i < p->mon; i++)
        p->daymin += times[leap][i];
    p->daymin += p->day;
    for (i = 2000; i < p->years; i++) {
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
            p->daymax += 366;
        else
            p->daymax += 365;

    }
    leap = p->years % 4 == 0 && p->years % 100 != 0 || p->years % 400 == 0;
    for (i = 1; i < p->month; i++)
        p->daymax += times[leap][i];
    p->daymax += p->days;
    return p;
}

/***********房间信息覆盖*************/
void status(struct room *p1, struct ID *x1) {
    strcpy(p1->status, "已被预订");
    strcpy(p1->name, x1->inputName);
    strcpy(p1->tel, x1->inputTel);
    p1->year = x1->year;
    p1->mon = x1->mon;
    p1->day = x1->day;
    p1->days = x1->numDay;
}

/**************重置房间信息************/
void reset(struct room *head) {
    struct room *p2;
    p2 = head;
    while (p2 != NULL) {
        strcpy(p2->status, "空闲");
        strcpy(p2->name, "无");
        strcpy(p2->tel, "无");
        strcpy(p2->name, "无");
        p2->year = 1;
        p2->days = 0;
        p2 = p2->next;
    }
}

/*************打印箭头*********/
int arrows(int x, char min, char max) {
    char t;
    printf("->");
    while (t = getchar()) {
        if (t >= min && t <= max || t == 72 || t == 80 || t == 13)
            break;

    }

    system("color F1");
    printf("  ");
    return t;
}

/*************随箭头变化数据同步变化***********/
int cColor(int y, int x, int judge) {
    if (judge == 0)
        x -= 2;
    else
        x += 2;
    return x;
}

/*********箭头方向倒置************/
void inversion(int *i, int y, int *x, int a, int b, char *s) {
    *i = a;
    *x = b;
    puts(s);
}

/*******************提醒用户退房**************/
void remind(struct ID *p, struct ID *head1) {
    struct ID *x1;
    int s = time(0);
    int d = s / 86400 - 10956;
    x1 = head1;
    while (x1 != NULL) {
        if (strcmp(x1->idnum, p->idnum) == 0)
            break;
        x1 = x1->next;
    }
    if (d > x1->daymax)
        printf("%s", "你预定的退房时间到了请办理退房手续以免造成不必要的损失!");
}
