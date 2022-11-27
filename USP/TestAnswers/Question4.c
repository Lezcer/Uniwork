/*haven't tested this, time restraint*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <sys/mman.h>

#define _GNU_SOURCE
#include <sched.h>

#define SIZESTACK 4096

char* str;
int do_work(void*)
{
    int fp;
    read(fp, str, 20);
    write(1, str, 20);
    munmap(str, sizeof(char)*20);
    return 0;
}

int main(void)
{
    int fp;
    char* str;
    void* stack;
    str = mmap(NULL, sizeof(char)*20, PROT_READ | PROT_WRITE, MAP_SHARED, 0, 0);

    stack = mmap(NULL, SIZESTACK, PROT_READ | PROT_WRITE, MAP_SHARED, 0, 0);
    clone(&do_work, (char*)stack + SIZESTACK, CLONE_VM,0);
    fork();

    munmap(stack, SIZESTACK);
    return 0;
}