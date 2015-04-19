/*
 * 运用printd函数的思想设计递归版本的itoa函数
 * 通过递归调用吧整数转化为字符串
*/

#include <stdio.h>

int do_itoa(char s[], int n, int index)
{
     if (n < 0) {
	  s[index++] = '-';
	  n = -n;
     }

     if ( n / 10 ) {
	  index = do_itoa(s, n / 10, index);
     }
     s[index++] = (n % 10) + '0';
     return index;
}

void itoa(char s[], int n)
{
    int i = do_itoa (s, n, 0);
    s[i] = '\0';
}

int main(int argc, char *argv[])
{
     char s[255];
     itoa(s, -7845);
     printf ("%s\n", s);
     return 0;
}
