#ifndef IPC_H
#define IPC_H

#include "LinkedList.h"
#include "testharness.h"

#include<sys/types.h>
#include<sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <string.h>
#include <errno.h>

#define FALSE 0
#define TRUE !FALSE

void resetpathname(char* path);

#define LENGTH 100
#define ARGNUM 5
#define ARGCHAR 30
#define DEFAULTOUTFD 1

#endif