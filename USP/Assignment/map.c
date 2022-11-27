#include <sys/mman.h>

int malloc(int sizeM)
{
    void* pointer;
    pointer = (char*)mmap(NULL, sizeM, PROT_READ | PROT_WRITE, MAP_SHARED, 0, 0);
    return pointer;
}
void free(void* pointer, int sizeM)
{
    munmap(pointer, sizeM);
    pointer = NULL;
}