#ifndef MY_STDIO_H
#define MY_STDIO_H
#include <stdio.h>
#define E_MSG(STRING,VAL) do{perror(STRING);return (VAL);}while(0)
#endif