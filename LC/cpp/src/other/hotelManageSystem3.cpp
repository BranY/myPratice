#include<iostream>
#include<string>
#include<fstream>

using namespace std;

//住客信息类
class Customer {
public:
    string name;  //姓名
    string id;      //身份证号
    string sex;//性别
    int age;     //年龄
    int roomnum;  //入住房号
    string ruzhum; //入住日期
    string ruzhut;//入住时间
    string lidianm;//离店日期
    string lidiant;//离店时间
    int staytime;  //入住天数

    Customer() {
        name = "无";
        id = "0";
        sex = "0";
        age = -1;
        roomnum = 0;
        ruzhum = "0";
        ruzhut = "0";
        lidianm = "0";
        lidiant = "0";
        staytime = 0;
    };

    ~Customer() {};
};

//客房类
class Room {
public:
    int num;//房号
    int price;  //价格
    string type;  //类型
    int renshu;   //入住人数
    int yajin;    //客房押金

    Room() {
        num = 0;
        price = 0;
        type = 50;
        renshu = 0;
        yajin = 0;
    };

    ~Room() {};
};

//无房，添房函数
void addRoomInfo(Room *room);

//有房，添加房间函数
void addRoomInfo2(Room *room);

//删除功能
void deleteRoom(Room *room, Customer *customer);

//入住登记功能
void checkIn(Room *room, Customer *customer);

void modifyRoomInfo(Room *room);

//退房客功能
void checkOut(Room *room, Customer *customer);

//查询菜单
void findRoomByCondition(Room *room, Customer *customer);

//打印全部客房信息
void printAllRoomInfo(Room *room);

//打印已住客房信息
void printAllRoomInfo1(Room *room, Customer *customer);

//打印空房信息
void printAllRoomInfo2(Room *room, Customer *customer);

//房号查询
void findRoomByNumber(Room *room, Customer *customer);

//姓名查询
void findRoomByName(Room *room, Customer *customer);

//类型查询
void findRoomByType(Room *room, Customer *customer);

//保存房间信息
void writeRoomInfoToFile(Room *room, Customer *customer);

// 读入文件功能
void readRoomInfoFromFile(Room *room, Customer *customer);

int main(int argc, char *argv[]) {

    Room room[50];
    Customer customer[50];
    readRoomInfoFromFile(room, customer);

    bool flag = true;
    while (flag) {
        cout << "\n\n       *****欢迎进入客房管理系统*****             \n\n";
        cout << "      ----------------------------------   \n";
        cout << "               1.客房信息的录入                 \n\n";
        cout << "               2.客人入住登记                    \n\n";
        cout << "               3.客房信息的修改                    \n\n";
        cout << "               4.客人退房结算                    \n\n";
        cout << "               5.查询客房信息                \n\n";
        cout << "               6.删除客房信息                \n\n";
        cout << "               7.退 出 系 统                   \n\n";
        cout << "  请选择：";
        int choose = 0;
        cin >> choose;
        system("cls");
        switch (choose)                                     //用户的选择
        {
            case 1:
                addRoomInfo(room);
                break;       //输入客房信息
            case 2:
                checkIn(room, customer);
                break;           //客人入住登记
            case 3:
                modifyRoomInfo(room);
                break;     //修改客房信息
            case 4:
                checkOut(room, customer);
                break;                      //客户退房
            case 5:
                findRoomByCondition(room, customer);
                break;     //查询菜单
            case 6:
                deleteRoom(room, customer);
                break;     //删除客房信息
            case 7:
                writeRoomInfoToFile(room, customer);
                break;
            case 8:
                flag = false;
                break;
            default :
                break;
        }
    }
    return 0;
}

//无房，添房函数
void addRoomInfo(Room *room) {
    if (room[0].num == 0) {
        int size;
        static int Number = 300;

        cout << "目前暂无房间信息，请添加！" << endl << endl;
        cout << "请输入要添加的房间数：";
        cin >> size;
        for (int i = 0; i < size; i++) {
            room[i].num = Number++;
            cout << "房间号码：" << room[i].num;
            cout << endl;
            cout << "房间类型：";
            cin >> room[i].type;
            cout << "房间价格：";
            cin >> room[i].price;
            cout << endl << endl;
        }

        cout << "客房信息录入成功!" << endl << endl;
    } else {
        string P;
        cout << "客房信息已有，是否还要添加房间？（Y/N）";
        cin >> P;
        if (P == "Y")
            addRoomInfo2(room);

        else if (P == "N")
            cout << "已取消房间添加." << endl << endl;

        else cout << "此操作非法！" << endl << endl;
    }
    system("pause");
    system("cls");
}

//有房，添加房间函数
void addRoomInfo2(Room *room) {
    static int Number = 300;
    string P;
    do {
        // int Number=300;
        for (int i = 0; i < 30; i++) {
            if (room[i].num == 0) {
                room[i].num = Number++;
                cout << "房间号码：" << room[i].num;
                cout << endl;
                cout << "房间类型：";
                cin >> room[i].type;
                cout << endl;
                cout << "房间价格：";
                cin >> room[i].price;
                cout << endl << endl;
                cout << "房间添加成功，是否继续添加？（Y/N）:";
                cin >> P;
                break;
            } else Number++;
        }
    } while (P == "Y");
    if (P == "N")
        cout << "已取消添加。" << endl << endl;
    else cout << "此操作非法！" << endl << endl;
}

void checkIn(Room *room, Customer *customer) {
    int p = 0;
    string b;
    cout << "今日本酒店剩余空房如下：" << endl;
    for (int i = 0; i < 30; i++) {
        if (room[i].renshu == 0 && room[i].num != 0) {
            cout << "房间号码：";
            cout << room[i].num << "\t";
            cout << "房间类型:";
            cout << room[i].type << "\t";
            cout << "房间价格:";
            cout << room[i].price << endl;

            p++;
        }
    }
    if (p == 0)cout << "抱歉，今日暂无空房！" << endl;
    else {
        cout << "请问,需要入住吗？（Y/N）" << endl;
        cin >> b;

        if (b == "Y") {
            int Num;
            int t = 0;

            cout << "请输入入住房间号码：" << endl;
            cin >> Num;
            for (int i = 0; i < 30; i++) {

                if (Num == room[i].num && room[i].renshu == 0 && room[i].num != 0) {
                    room[i].renshu = 1;
                    customer[i].roomnum = Num;

                    cout << "请输入客户信息！" << endl << endl;
                    cout << "客户姓名：";
                    cin >> customer[i].name;
                    cout << "客户身份证号码:";
                    cin >> customer[i].id;

                    cout << "年龄:";
                    cin >> customer[i].age;
                    cout << "性别：";
                    cin >> customer[i].sex;
                    cout << "客房押金：";
                    cin >> room[i].yajin;

                    cout << "入住日期：";
                    cin >> customer[i].ruzhum;
                    cout << "入住时间：";
                    cin >> customer[i].ruzhut;
                    cout << "离店日期：";
                    cin >> customer[i].lidianm;
                    cout << "离店时间：";
                    cin >> customer[i].lidiant;
                    cout << "入住天数：";
                    cin >> customer[i].staytime;
                    cout << endl;
                    cout << "信息录入成功!" << endl;

                    t++;
                } else if (Num == room[i].num && room[i].renshu != 0 && room[i].num != 0) {

                    cout << "抱歉，此房已住";
                    t++;
                } else if (t == 0 && i == 29)
                    cout << "查无此房";

            }

        } else if (b == "N") cout << "订房已取消。";

        else cout << "查无此房！";
    }
    system("pause");
    system("cls");

}

void modifyRoomInfo(Room *room) {
    int Num;
    int _num;
    int _price;
    string _type;
    int _renshu;
    int _yajin;

    printAllRoomInfo(room);
    cout << "请输入要修改的房间号码:" << endl;
    cin >> Num;

    int t = 0;
    for (int i = 0; i < 30; i++) {

        if (Num == room[i].num) {
            cout << "房间号:" << room[i].num << "\t" << "房间类型:" << room[i].type << "\t" << "价格:" << room[i].price << endl
                 << endl;
            cout << "房间信息已找到!" << endl;
            cout << "是否确定要修改房间信息？（Y/N）";
            string p;
            cin >> p;
            if (p == "Y") {
                cout << "房间类型改为：";
                cin >> _type;
                room[i].type = _type;
                cout << endl;
                cout << "房间价格改为：";
                cin >> _price;
                room[i].price = _price;
                cout << endl;

                //	Save_Customer_info(Room);
                cout << "修改成功!" << endl << endl;
            } else if (p == "N") cout << "已取消修改。";
            else cout << "操作非法！";
            t++;
        } else if (t == 0 && i == 29 && room[i].num == 0)
            cout << "查无此房！" << endl << endl;

    };
    system("pause");
    system("cls");
}

//退房客功能
void checkOut(Room *room, Customer *customer) {
    int Num;
    printAllRoomInfo1(room, customer);
    int t = 0;

    cout << "请输入要退掉的房间号：";
    cin >> Num;


    for (int i = 0; i < 30; i++) {
        //*****************************************
        for (int j = 0; j < 30; j++) {
            if (room[i].num == Num && room[i].num == customer[j].roomnum) {
                string B, C;
                int n;
                n = customer[j].staytime * room[i].price - room[i].yajin;

                cout << "房间已找到：" << endl;
                cout << "房间类型:";
                cout << room[i].type << endl;
                cout << "房间价格:";
                cout << room[i].price << endl << endl;
                t++;

                cout << "房客信息如下：" << endl;

                cout << "客户姓名：";
                cout << customer[j].name << endl;
                cout << "客户身份证号码:";
                cout << customer[j].id << endl << endl;
                cout << "客户共入住：" << customer[j].staytime << "天,共需交费：" << n << "元整。" << endl;

                cout << "确定要退掉客房？(Y/N)";
                cin >> B;
                cout << endl;
                if (B == "Y") {
                    customer[j].name = "无";
                    customer[j].id = "0";
                    customer[j].sex = "0";
                    customer[j].age = -1;
                    customer[j].roomnum = 0;
                    customer[j].ruzhum = "0";
                    customer[j].ruzhut = "0";
                    customer[j].lidianm = "0";
                    customer[j].lidiant = "0";
                    customer[j].staytime = 0;

                    room[i].renshu = 0;
                    room[i].yajin = 0;
                    cout << "退房成功！" << endl;

                } else if (B == "N") {
                    cout << "取消退房成功。" << endl;
                    break;
                } else {
                    cout << "非法的操作！" << endl;
                    break;
                }

            } else if (t == 0 && i == 29 && j == 29) { cout << "错误，找不到客房信息!" << endl << endl; }

        }
    }
    system("pause");
    system("cls");

}

//查询菜单
void findRoomByCondition(Room *room, Customer *customer) {

    bool p = true;
    while (p) {
        cout << "\n                客房管理系统             \n ";
        cout << "\n      ---------------------------------- \n  ";
        cout << "\n               1.通过房号查询客房        \n  ";
        cout << "\n               2.通过姓名查询客房        \n  ";
        cout << "\n               3.通过类型查询客房        \n       ";
        cout << "\n               4.查询所有客房            \n ";
        cout << "\n               5.查询已住客房            \n             ";
        cout << "\n               6.查询所有空房            \n            ";
        cout << "\n               7.返回主菜单              \n           " << endl;
        cout << "  请选择：";
        int choose = 0;
        cin >> choose;
        system("cls");
        switch (choose) {
            case 1:
                findRoomByNumber(room, customer);
                break;            //房号查询
            case 2:
                findRoomByName(room, customer);
                break;             //姓名查询
            case 3:
                findRoomByType(room, customer);
                break;         //类型查询
            case 4:
                printAllRoomInfo(room);
                break;                      //查询所有客房
            case 5:
                printAllRoomInfo1(room, customer);
                break;           //查询已住客房
            case 6:
                printAllRoomInfo2(room, customer);
                break;           //查询所有空房

            case 7:
                p = false;
                break;
            default :;
                break;
        }
    }
}

void printAllRoomInfo(Room *room) {
    int t = 0;

    cout << "全部客房信息如下:" << endl;
    for (int i = 0; i < 30; i++) {

        if (room[i].num != 0) {

            cout << "房间号:" << room[i].num << "\t" << "房间类型:" << room[i].type << "\t" << "价格:" << room[i].price << endl
                 << endl;
            t++;
        }
        if (t == 0 && i == 29) cout << "暂无任何客房信息!" << endl << endl;

    }
    system("pause");
    system("cls");
}

void printAllRoomInfo1(Room *room, Customer *customer) {
    int t = 0;

    cout << "已住客房信息如下:" << endl;
    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 30; j++) {
            if (room[i].num != 0 && room[i].renshu != 0 && customer[j].roomnum == room[i].num) {

                cout << "房间号:" << room[i].num << "\t" << "房间类型:" << room[i].type << "\t" << "价格:" << room[i].price
                     << endl;
                cout << "客户姓名：" << customer[j].name << "\t"
                     << "客户身份证号码：" << customer[j].id << "\t"
                     << "押金：" << room[i].yajin << endl
                     << "订房日期：" << customer[j].ruzhum << "\t" << "订房时间：" << customer[j].ruzhut << endl
                     << "退房日期：" << customer[j].lidianm << "\t" << "退房时间：" << customer[j].lidiant << endl
                     << "将于" << customer[j].staytime << "天后退房。" << endl << endl << endl;
                t++;
            }

        }
        if (t == 0 && i == 29) cout << "暂无任何客房信息!" << endl << endl;

    }
    system("pause");
    system("cls");
}

void printAllRoomInfo2(Room *room, Customer *customer) {
    int t = 0;

    cout << "全部空房信息如下:" << endl;
    for (int i = 0; i < 30; i++) {

        if (room[i].num != 0 && room[i].renshu == 0) {

            cout << "房间号:" << room[i].num << "\t" << "房间类型:" << room[i].type << "\t" << "价格:" << room[i].price << endl
                 << endl;
            t++;
        }
        if (t == 0 && i == 29) cout << "暂无任何客房信息!" << endl << endl;


    }
    system("pause");
    system("cls");
}

void findRoomByNumber(Room *room, Customer *customer) {
    int Num;
    int t = 0;
    cout << "输入要查找的房间号码：";
    cin >> Num;

    for (int i = 0; i < 30; i++) {
        if (room[i].num == Num) {
            string B, C;
            cout << "房间已找到：" << endl;
            cout << "房间类型:";
            cout << room[i].type << endl;
            cout << "房间价格:";
            cout << room[i].price << endl;
            t++;
            for (int j = 0; j < 30; j++) {
                //情况1，若客房下还有房客
                if (room[i].num == customer[j].roomnum) {


                    cout << "客户姓名：";
                    cout << customer[j].name << endl;
                    cout << "客户身份证号码:";
                    cout << customer[j].id << endl;
                }
            }

        } else if (t == 0 && i == 29) { cout << "错误，找不到客房信息!" << endl << endl; }

    }

    system("pause");
    system("cls");

}

void findRoomByName(Room *room, Customer *customer) {
    string Name;
    int t = 0;
    cout << "输入要查找的姓名：";
    cin >> Name;

    for (int i = 0; i < 30; i++) {
        if (customer[i].name == Name) {
            string B, C;
            cout << "客户已找到：" << endl;
            cout << "客户姓名：";
            cout << customer[i].name << endl;
            cout << "客户身份证号码:";
            cout << customer[i].id << endl;

            t++;
            for (int j = 0; j < 30; j++) {
                //情况1，若客房下还有房客
                if (room[j].num == customer[i].roomnum) {
                    cout << "入住房间号:";
                    cout << room[j].num << endl;

                    cout << "房间类型:";
                    cout << room[j].type << endl;
                    cout << "房间价格:";
                    cout << room[j].price << endl;
                }
            }

        } else if (t == 0 && i == 29) { cout << "错误，找不到该名字!" << endl << endl; }

    }
    system("pause");
    system("cls");
}


void findRoomByType(Room *room, Customer *customer) {
    string Num;
    int t = 0;
    cout << "输入要查找的房间类型：";
    cin >> Num;

    for (int i = 0; i < 30; i++) {
        //查找房间号
        if (room[i].type == Num) {
            string B, C;
            cout << "房间已找到：" << endl;
            cout << "房号:";
            cout << room[i].num << endl;

            cout << "房间价格:";
            cout << room[i].price << endl;
            t++;
            for (int j = 0; j < 30; j++) {
                //情况1，若客房下还有房客
                if (room[i].num == customer[j].roomnum) {

                    cout << "入住客户姓名：";
                    cout << customer[j].name << endl;
                    cout << "入住客户身份证号码:";
                    cout << customer[j].id << endl << endl;
                }
            }
        } else if (t == 0 && i == 29) { cout << "错误，找不到该类型客房!" << endl << endl; }
    }

    system("pause");
    system("cls");
}

//删除房间函数
void deleteRoom(Room *room, Customer *customer) {
    int Num;
    printAllRoomInfo(room);
    int t = 0;

    cout << "请输入要删除的房间号：";
    cin >> Num;

    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 30; j++)
            //查找房间号
            if (room[i].num == Num && Num != 0) {
                string B, C;
                cout << "房间已找到：" << endl;
                cout << "房间类型:";
                cout << room[i].type << endl;
                cout << "房间价格:";
                cout << room[i].price << endl;
                t++;

                //情况1，若客房下还有房客
                if (room[i].num == customer[j].roomnum) {
                    cout << "该客房下仍有入住房客，房客信息如下：" << endl;

                    cout << "客户姓名：";
                    cout << customer[j].name << endl;
                    cout << "客户身份证号码:";
                    cout << customer[j].id << endl;

                    cout << "确定仍要删除客房信息？(Y/N)";
                    cin >> B;
                    cout << endl;
                    if (B == "Y") {
                        customer[j].name = "无";
                        customer[j].id = "0";
                        customer[j].sex = "0";
                        customer[j].age = -1;
                        customer[j].roomnum = 0;
                        customer[j].ruzhum = "0";
                        customer[j].ruzhut = "0";
                        customer[j].lidianm = "0";
                        customer[j].lidiant = "0";
                        customer[j].staytime = 0;

                        room[i].num = 0;
                        room[i].price = 0;
                        room[i].type = 50;
                        room[i].renshu = 0;
                        room[i].yajin = 0;


                        cout << "删除成功！" << endl;

                    } else if (B == "N") {
                        cout << "取消删除。" << endl;
                        break;
                    } else {
                        cout << "非法的操作！" << endl;
                        break;
                    }

                } else if (room[i].num != customer[j].roomnum) { //情况2，客房下没有房客
                    cout << "确定要删除客房信息？(Y/N)";

                    cin >> C;
                    cout << endl;
                    if (C == "Y") {
                        room[i].num = 0;
                        room[i].price = 0;
                        room[i].type = 50;
                        room[i].renshu = 0;
                        room[i].yajin = 0;

                        cout << "删除成功！" << endl;

                    } else if (C == "N") {
                        cout << "取消删除。" << endl;
                        break;
                    } else {
                        cout << "非法的操作！" << endl;
                        break;
                    }
                }
            } else if (t == 0 && i == 29 && j == 29) { cout << "错误，找不到客房信息!" << endl << endl; }

    }
    system("pause");
    system("cls");
}

//保存房间信息
void writeRoomInfoToFile(Room *room, Customer *customer) {
    ofstream Str("客房信息.txt", ios_base::trunc);
    ofstream C("客户信息.txt", ios_base::trunc);
    if (!Str || !C) {
        cout << "文件存入失败！";
        return;
    }

    for (int i = 0; i < 30; i++) {
        if (room[i].num != 0) {
            Str << room[i].num << "\t" << room[i].type << "\t" << room[i].price << "\t" << room[i].renshu << "\t"
                << room[i].yajin << endl;
        }
    }

    for (int j = 0; j < 30; j++) {
        if (customer[j].roomnum != 0) {
            C << customer[j].roomnum << "\t" << customer[j].name << "\t" << customer[j].id << "\t" << customer[j].sex
              << "\t" << customer[j].age << "\t" << customer[j].ruzhum << "\t" << customer[j].ruzhut
              << "\t" << customer[j].lidianm << "\t" << customer[j].lidiant << "\t" << customer[j].staytime << endl;
        }
    }
    Str.close();
    C.close();

}

// 读入文件功能
void readRoomInfoFromFile(Room *room, Customer *customer) {
    int i = 0;
    int j = 0;

    ifstream readroom("客房信息.txt");
    ifstream readcustomer("客户信息.txt");
    if (!readroom || !readcustomer) {
        cout << "文件打开失败！";
        return;
    }


    while (!readroom.eof()) {

        readroom >> room[i].num >> room[i].type >> room[i].price >> room[i].renshu >> room[i].yajin;
        i++;
    }

    while (!readcustomer.eof()) {

        readcustomer >> customer[j].roomnum >> customer[j].name >> customer[j].id >> customer[j].sex
                     >> customer[j].age >> customer[j].ruzhum >> customer[j].ruzhut
                     >> customer[j].lidianm >> customer[j].lidiant >> customer[j].staytime;
        j++;
    }

    readroom.close();
    readcustomer.close();
}

