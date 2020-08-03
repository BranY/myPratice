//
// Created by 杨文家 on 2020/8/3.
//

/*住宿管理模块：包括客房预订、入住登记、客人续往、调房登记、退房结账
客房管理模块：包括客房设置、客房查询、宿费提醒
查询统计模块：包括预订房查询、住宿查询、退房查询、客房宿费统计
*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

#define MAX 1000 //哈希表的表长
typedef int Index;//hash地址类型
typedef void Status;//返回类型
Status weihu();//客房信息的维护
Status InputRoom();//初始化客房信息
Status writetotxt();//将客房信息写入到kefangxinxi.txt文件中
Status write1totxt();//将客人信息写入到kerenxinxi.txt文件中
Status readfromtxt();//将客房信息从kefangxinxi.txt文件中读出到hash表中
Status read1fromtxt();//将客人信息从kerenxinxi.txt读出到hash1表中
Index HashIndex(int num);//查找num在hash函数中的位置（取num的后三位并返回后三位）
Status kefangxinxi();//输出客房信息
Status xiugaikefang();//修改客房信息
Status tainjiakefang();//添加客房
Status shanchukefang();//删除客房
Status zhusuguanli();//住宿管理菜单
Status ruzhu();//入住操作
Status tuifang();//退房操作
Status xufang();//续房操作
Status search();//查找模块
Status findprice();// 筛选符合需求的客房
Status findkefang();//查找空的客房
Status findkeren();//查找客人
Status findofroom();//按照客房查找客人
Status findofname();//按照姓名查找客人
Status kerenxinxi();//输出客人信息
Status daoqitixing();//输出即将到期的客人
/*住宿管理模块：包括客房预订、
入住登记、客人续住、调房登记、退房结账*/
typedef struct
{
    int year;
    int mon;
    int day;
}ruzhutime;//入住时间的结构体
typedef struct patron//客官信息
{
    int guenum;//客人住房编号
    char name[20];//客人姓名
    char idnum[19];//客人身份证号
    char phone[12];//电话号码
    char sex[5];//客人性别
    ruzhutime time;//入住时间
    int timelong;//租房时长
    long int s;
}parton;
parton hash1[MAX];//定义客人的哈希表
typedef struct Guest//住房
{
    int guenum;//客房编号
    int maxnum;//最大入住人数
    int shifouruzhu;//是否有人入住
    double zujin;//房租
    int num;//已入住人数
}Guest;
Guest hash[MAX];//定义客房的哈希表

int main()//主函数
{
    int i;
    for(i=0;i<MAX;i++)
    {
        hash[i].guenum=0;
        hash1[i].guenum=0;
    }
    readfromtxt();
    read1fromtxt();
    while(1)
    {
        printf("*************************************\n");
        printf("****    欢迎使用住宿管理系统    *****\n\n");
        printf("****    住宿管理请输入 1：      *****\n");
        printf("****    客房管理请输入 2：      *****\n");
        printf("****    查询统计请输入 3：      *****\n");
        printf("****    退出请输入     0：      *****\n\n");
        printf("*************************************\n");
        int choice;
        printf("请输入您的选择：\n");
        if(scanf("%d",&choice)!=1) //如果输入不非法
        {
            printf("输入错误！\n已退出！\n");
            exit(0);
        }
        switch(choice)
        {
            case 1:zhusuguanli();break;//住宿管理模块
            case 2: weihu();break;//客房维护模块
            case 3: search();break;//查找信息模块
            case 0: printf("\n感谢使用本旅店管理系统\n\nhaut,掌握先进科技！\n");
                exit(0);//退出
        }
    }
    return 0;
}

Index HashIndex(int num)//查找num在hash函数中的位置（取num的后三位并返回后三位）
{
    return num%MAX;//取后三位
}
Status InputRoom()//初始化房间信息
{
    int i;
    printf("注意！！！是否继续，此操作会将原客房信息覆盖且无法恢复（Y/N）\n");
    char choice;//确认操作
    scanf("%*c%c", &choice);
    if(choice!='Y'&&choice!='y')  return;
    Guest room;
    printf("请输入房间编号，（编号为-1时结束输入）\n");
    while(scanf("%d", &room.guenum),room.guenum!=-1)//输入信息
    {
        i=room.guenum%1000;
        if(hash[i].guenum)//如果已经输入过此房间则重新输入
        {
            printf("已存在此房间！请重新输入！\n");
            continue;
        }
        printf("请输入最大入住人数\n");
        scanf("%d", &room.maxnum);
        printf("请输入租金：\n");
        scanf("%lf", &room.zujin);
        printf("请输入是否有人入住：(1=有；0=没有)\n");
        scanf("%d",&room.shifouruzhu);
        printf("请输入入住人数:\n");
        scanf("%d", &room.num);
        hash[i]=room;
        printf("请输入房间编号，（编号为-1时结束输入）\n");
    }
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    for(i=0;i<MAX;i++)//输出操作后的客房信息
        if(hash[i].guenum)
            printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                   hash[i].shifouruzhu,hash[i].num);
    writetotxt();//将客房信息写入文件
}
Status weihu()//客房信息的维护
{
    while(1)
    {
        printf("************************************\n");
        printf("****  重新输入客房信息请输入 1： ***\n");
        printf("****  修改客房信息请输入     2： ***\n");
        printf("****  添加客房信息请输入     3： ***\n");
        printf("****  删除客房信息请输入     4： ***\n");
        printf("****  返回上一菜单请输入     0： ***\n");
        printf("************************************\n");
        int n;
        printf("请输入您的选择：\n");
        if(scanf("%d", &n)!=1)
        {
            printf("输入错误！\n已退出！\n");
            return;
        }
        switch(n)//对应的选择
        {
            case 1:InputRoom();break;//初始化客房信息
            case 2:xiugaikefang();break;//修改客房信息
            case 3:tainjiakefang();break;//添加客房
            case 4:shanchukefang();break;//删除客房
            case 0:return;//返回上一菜单
        }
    }
}
Status shanchukefang()//删除客房
{
    int i,num;
    printf("请输入要删除的客房编号：\n");
    if(scanf("%d", &num)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    i=HashIndex(num);//哈希地址
    if(!hash[i].guenum)//如果客房编号为0
    {
        printf("此客房不存在！\n");
        return ;
    }
    printf("删除不可恢复，是否要删除！Y/N\n");//确认操作
    char c;
    if(scanf("%*c%c", &c)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    if(c!='Y'&&c!='y')//取消操作
        return;
    hash[i].guenum=0;//将客房信息初始化为0
    writetotxt();//写入文件
    printf("删除成功！\n");
}
Status tainjiakefang()//添加客房信息
{
    int i,num;
    printf("请输入需要添加的客房编号：\n");
    if(scanf("%d", &num)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    i=HashIndex(num);//哈希地址
    if(hash[i].guenum)//房间号不为空
    {
        printf("此房间已存在！\n");
        return;
    }
    hash[i].guenum=num;
    printf("请输入最大入住人数\n");
    scanf("%d", &hash[i].maxnum);
    printf("请输入租金：\n");
    scanf("%lf", &hash[i].zujin);
    printf("请输入是否有人入住：(1=有；0=没有)\n");
    scanf("%d",&hash[i].shifouruzhu);
    printf("请输入入住人数:\n");
    scanf("%d", &hash[i].num);
    printf("添加成功！\n");
    writetotxt();
}
Status xiugaikefang()//修改客房信息
{
    printf("请输入需要修改的客房的编号：\n");
    int num,i,j;
    scanf("%d", &num);
    i=HashIndex(num);//哈希地址
    if(!hash[i].guenum)//客房编号为空
    {
        printf("不存在此房间！\n");
        return;
    }
    printf("此房间信息为：\n");//修改客房之前输出此客房信息便于确认
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
           hash[i].shifouruzhu,hash[i].num);
    printf("是否要修改 是（Y）否（N）\n");
    char c;
    scanf("%*c%c", &c);
    if(c!='y'&&c!='Y')//确认删除
    {
        printf("修改取消！\n");
        return;
    }
    printf("请输入最大入住人数\n");
    scanf("%d", &hash[i].maxnum);
    printf("请输入租金：\n");
    scanf("%lf", &hash[i].zujin);
    printf("请输入是否有人入住：(1=有；0=没有)\n");
    scanf("%d",&hash[i].shifouruzhu);
    printf("请输入入住人数:\n");
    scanf("%d", &hash[i].num);
    printf("修改成功！\n");
    printf("修改后的信息为：\n");
    printf("此房间信息为：\n");
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
           hash[i].shifouruzhu,hash[i].num);
    writetotxt();//将修改后的信息写入文件
}
Status kefangxinxi()//输出客房信息
{
    int i;
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    for(i=0;i<MAX;i++)
        if(hash[i].guenum)//存在客房
            printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                   hash[i].shifouruzhu,hash[i].num);
}
Status search()//查找模块
{
    while(1)
    {
        printf("************************************\n");
        printf("****  查找客房信息请输入     1： ***\n");
        printf("****  查找客人信息请输入     2： ***\n");
        printf("****  浏览所有住房信息请输入 3： ***\n");
        printf("****  浏览所有客人信息请输入 4： ***\n");
        printf("****  筛选客房请输入输入     5： ***\n");
        printf("****  输出快到期的客人请输入 6： ***\n");
        printf("****  返回上一菜单请输入     0： ***\n");
        printf("************************************\n");
        int n;
        printf("请输入您的选择：\n");
        if(scanf("%d", &n)!=1)
        {
            printf("输入错误！\n已退出！\n");
            return;
        }
        switch(n)
        {
            case 1:findkefang();break;//查找客房
            case 2:findkeren();break;//查找客人
            case 3:kefangxinxi();break;//客房信息输出
            case 4:kerenxinxi();break;//客人信息输出
            case 5:findprice();break;//筛选指定的客房
            case 6:daoqitixing();break;
            case 0:return;//返回上一菜单
        }
    }
}
Status daoqitixing()
{
    int i,j=0;
    time_t rawtime;//获取时间标记
    printf("即将到期客人信息为：\n");
    for(i=0;i<MAX;i++)
        if(hash1[i].guenum)
            if(hash1[i].s-time(&rawtime)<=hash1[i].timelong*24*60*60)
            {
                printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                       hash[i].shifouruzhu,hash[i].num);
                j=1;
            }
    if(!j) printf("不存在快到期的客人！\n");
}
Status findprice()//筛选指定客房
{
    printf("请输入指定的价格区间：\n");
    printf("请输入最小的价格：\n");
    double low;
    scanf("%lf", &low);
    printf("请输入最大的价格：\n");
    double high;
    scanf("%lf", &high);
    printf("请输入人数：\n");
    int s;
    scanf("%d",&s);
    int i;
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    for(i=0;i<MAX;i++)
        if(hash[i].zujin>=low&&hash[i].zujin<=high&&hash[i].maxnum>=s)//符合筛选条件
            printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                   hash[i].shifouruzhu,hash[i].num);
}
Status kerenxinxi()//输出客人信息
{
    int i,j=0;
    for(i=0;i<MAX;i++)
        if(hash1[i].guenum)//如果有客人
        {
            printf("住宿房间号：%d 姓名：%s 身份证号：%s 电话号码： %s 性别： %s \n",hash1[i].guenum,hash1[i].name,
                   hash1[i].idnum,hash1[i].phone,hash1[i].sex);
            j=1;//标记有客人
        }
    if(!j) printf("无人入住！\n");
}
Status findkeren()//查找客人
{
    int i,j=0;
    printf("*********************************\n");
    printf("****   按照房间查找请输入 1  ****\n");
    printf("****   按照姓名查找请输入 2  ****\n");
    printf("*********************************\n");
    printf("请输入您的选择：\n");
    int n;
    scanf("%d", &n);
    switch(n)
    {
        case 1:findofroom();break;//按房间查找
        case 2:findofname();break;//按姓名查找
    }
}
Status findofname()//按姓名查找
{
    char name[20];
    printf("请输入客人姓名：\n");
    scanf("%s", name);
    int i,j=0;
    for(i=0;i<MAX;i++)
        if(!strcmp(hash1[i].name,name))//要查找的姓名与客人相同
        {
            printf("住宿房间号：%d 姓名：%s 身份证号：%s 电话号码： %s 性别： %s \n",hash1[i].guenum,hash1[i].name,
                   hash1[i].idnum,hash1[i].phone,hash1[i].sex);
            j=1;//找到标记
        }
    if(!j )printf("查无此人！！\n");
}
Status findofroom()//按客房查找
{
    int i,num;
    printf("请输入房间编号：\n");
    scanf("%d", &num);
    i=HashIndex(num);//哈希地址
    if(!hash1[i].guenum)//房间号不存在
    {
        printf("无人入住!!\n") ;
        return;
    }
    printf("住宿房间号：%d 姓名：%s 身份证号：%s 电话号码： %s 性别： %s \n",hash1[i].guenum,hash1[i].name,
           hash1[i].idnum,hash1[i].phone,hash1[i].sex);
}
Status findkefang()//查找空余客房
{
    int i,j=0;
    printf("空余客房为：\n");
    printf("房间编号 最大入住人数 租金 是否有人 入住人数\n");
    for(i=0;i<MAX;i++)
        if(!hash[i].shifouruzhu&&hash[i].guenum){
            printf("%d             %d       %.2f     %d        %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                   hash[i].shifouruzhu,hash[i].num);
            j=1;//找到标记
        }
    if(!j) printf("客房爆满！！！\n");
}
Status zhusuguanli()//住宿管理
{
    printf("************************************\n");
    printf("*****   入住请输入    1：       ****\n");
    printf("*****   退房请输入    2：       ****\n");
    printf("*****   续房请输入    3：       ****\n");
    printf("*****   退出请输入    0：       ****\n");
    printf("************************************\n");
    int n;
    printf("请输入您的选择：\n");
    scanf("%d", &n);
    switch(n)
    {
        case 1:ruzhu();break;//入住操作
        case 2:tuifang();break;//退房操作
        case 3:xufang();break;//续房操作
        case 0:return;//返回上一菜单
    }
}
Status xufang()//续房操作
{
    printf("请输入需要续房的房间号：\n");
    int num,i;
    if(scanf("%d", &num)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    i=HashIndex(num);//哈希地址
    if(!hash1[i].guenum)//若未租出
    {
        printf("此房间尚未租出！！\n");
        return;
    }
    printf("住宿房间号：%d 姓名：%s 入住时间：%d年%d月%d日 入住时长：%d天\n",hash1[i].guenum,hash1[i].name,
           hash1[i].time.year,hash1[i].time.mon,hash1[i].time.day,hash1[i].timelong);
    printf("是否续费？ Y/N\n");
    char c;
    if(scanf("%*c%c", &c)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    if(c!='y'&&c!='Y') return;//确认操作
    printf("请输入续费的天数：\n");
    int day;
    scanf("%d", &day) ;
    hash1[i].timelong+=day;//修改延长后住宿天数
    printf("修改后的信息为：\n");
    printf("住宿房间号：%d 姓名：%s 入住时间：%d年%d月%d日 入住时长：%d天\n",hash1[i].guenum,hash1[i].name,
           hash1[i].time.year,hash1[i].time.mon,hash1[i].time.day,hash1[i].timelong);
    write1totxt();//修改后写入文件
    printf("续费成功！！\n");
}
Status tuifang()//退房操作
{
    printf("请输入将要退房的房间号：\n");
    int num,i;
    if(scanf("%d", &num)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    i=HashIndex(num);//哈希地址
    if(!hash1[i].guenum)//没有租出
    {
        printf("此房间没有租出！！\n");
        return;
    }
    hash1[i].guenum=0;//将此客人删除
    write1totxt();//操作后的客人信息 写入文件
    hash[i].shifouruzhu=0;//客房修改为没有人入住
    hash[i].num=0;//入住人数修改为0
    writetotxt();//将修改后的 客房信息写入文件
    printf("退房成功,欢迎下次光临！！！\n");
}
Status ruzhu()//入住操作
{
    findprice();
    printf("请输入需要预定的房间编号：\n");
    parton keren;
    if(scanf("%d", &keren.guenum)!=1)
    {
        printf("输入错误！\n已退出！\n");
        exit(0);
    }
    int i;
    i=HashIndex(keren.guenum);//哈希地址
    if(!hash[i].guenum)
    {
        printf("查无此房!!\n");
        return;
    }
    if(hash[i].shifouruzhu)
    {
        printf("此房间已经有人!\n");
        return;
    }
    int n;
    printf("请输入几人入住：\n");
    if(scanf("%d", &n)!=1)
    {
        printf("输入错误！\n已退出！\n");
        return;
    }
    if(hash[i].maxnum<n)//超过入住人数上限
    {
        printf("超过人数上限！！\n请重新输入：\n");
        return;
    }
    printf("请输入客人姓名：\n");
    scanf("%s", keren.name);
    printf("请输入客人身份证号：\n");
    scanf("%s", keren.idnum);
    printf("请输入客人电话号码：\n");
    scanf("%s", keren.phone);
    printf("请输入客人性别：\n");
    scanf("%s", keren.sex);
    printf("请输入入住时间：（年月日），中间用空格隔开\n");
    scanf("%d%d%d", &keren.time.year,&keren.time.mon,&keren.time.day);
    printf("请输入入住时长：\n");
    scanf("%d", &keren.timelong);
    time_t rawtime;//获取时间标记
    keren.s=time(&rawtime);
    hash1[i]=keren;//入住
    hash[i].shifouruzhu=1;//客房信息改为有人入住
    hash[i].num=n;//入住人数
    writetotxt();//写入修改后的客人和客房信息
    write1totxt();
}

Status write1totxt()//将客人信息写入kerenxinxi.txt文件
{
    FILE *fp;//定义文件
    fp=fopen("kerenxinxi.txt","w");//以写的方式打开
    int i;
    for(i=0;i<MAX;i++)
        if(hash1[i].guenum)
            fprintf(fp,"%d %s %s %s %s %d %d %d %d %d\n",hash1[i].guenum,hash1[i].name,hash1[i].idnum,hash1[i].phone,
                    hash1[i].sex,hash1[i].time.year,hash1[i].time.mon,hash1[i].time.day,hash1[i].timelong,hash1[i].s);
    fclose(fp);//关闭文件
}
Status read1fromtxt()//从文件kerenxinxi.txt文件读出客人信息到hash1数组中
{
    parton keren;//暂存的客人
    FILE *fp;//定义文件指针
    fp=fopen("kerenxinxi.txt","r");//以读的方式打开文件
    int i;
    while(!feof(fp))//不到文件尾
    {
        fscanf(fp,"%d %s %s %s %s %d %d %d %d %d\n", &keren.guenum,keren.name,keren.idnum,keren.phone,
               keren.sex,&keren.time.year,&keren.time.mon,&keren.time.day,&keren.timelong,&keren.s);
        i=HashIndex(keren.guenum);//哈希地址
        hash1[i]=keren;//赋值
    }
    fclose(fp);//关闭文件
}
Status readfromtxt()//从文件中读取客房信息
{
    Guest room;
    FILE *fp;//定义文件
    if((fp=fopen("roomxinxi.txt","r"))==NULL)
    {
        printf("打开文件失败！！！\n");
        exit(0);
    }//读的方式打开文件
    int i;
    while(!feof(fp))//不到文件尾
    {
        fscanf(fp,"%d %d %lf %d %d\n", &room.guenum,&room.maxnum,&room.zujin,
               &room.shifouruzhu,&room.num);
        i=HashIndex(room.guenum);//哈希地址
        hash[i]=room;
    }
    if(fclose(fp))
    {
        printf("关闭文件失败！！！\n");
        exit(0);
    }//关闭文件
}

Status writetotxt()//将客房信息写入文件
{
    int i;
    FILE *fp;
    if((fp=fopen("roomxinxi.txt","w"))==NULL)//读的方式打开文件
    {
        printf("打开文件失败！！！\n");
        exit(0);
    }
    for(i=0;i<MAX;i++)
        if(hash[i].guenum)//有客房
            fprintf(fp,"%d %d %lf %d %d\n", hash[i].guenum,hash[i].maxnum,hash[i].zujin,
                    hash[i].shifouruzhu,hash[i].num);
    fclose(fp);//关闭文件
}