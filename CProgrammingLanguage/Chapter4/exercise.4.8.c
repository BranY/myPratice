/*
 * 假定最多只压回一个字符。请修改getch和ungetch 
*/


#define EMPTY   -2
 
int buf = EMPTY;

int getch(void){
    int c;
    c = (buf != EMPTY)?  buf : getchar();       
    buf = EMPTY;
    return c;
}

void ungetch(int c){
    buf = c;       
}

