#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define INPUT_SIZE 512
void freeFile(char**, int);
int countLines(char*);
char** readFile(char* filename, int NumLines);
