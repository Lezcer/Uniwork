#ifndef BRACKETCHECK_H
#define BRACKETCHECK_H

#include "linkedlist.h"
#include "newSleep.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define FALSE 0
#define TRUE !FALSE

#define DEFAULT 512

#define ROUND "\033[0;41m"
#define SQUARE "\033[0;44m"
#define CURLY "\033[0;42m"
#define ANGLE "\033[0;46m"
#define RESET "\033[0m"
#define ERROR "\033[0;45m"

void display(char**, char*, int, int, int);
void check(char**, int);
void printLL(void* data);
void freeLL(void* data);

#endif





