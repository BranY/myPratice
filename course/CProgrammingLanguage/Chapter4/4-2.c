#include <stdio.h>
#include <ctype.h>
#define MAXLINE 100
/* change string to double */
double atof(char s[]){
    int i, sign, valexp;
    double val, power;

    for (i = 0; isspace(s[i]); i++) ;    /* skip space */   
    sign = (s[i] == '-')? -1: 1;
    if (s[i] == '+' || s[i] == '-')
        i++;
    for (val = 0.0; isdigit(s[i]); i++)
        val = val * 10 + (s[i] - '0');

    if (s[i] == '.')
        i++;
	for (power = 1.0; isdigit(s[i]); i++){
		val = val * 10 + (s[i] - '0');
        power *= 10;
	}
	
    return sign * val / power;
}
