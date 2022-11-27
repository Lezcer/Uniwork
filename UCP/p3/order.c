#include <stdio.h>
#include "order.h"

static void ascending2(int* ptr1, int* ptr2)
{
	int temp;
    if(*ptr1 > *ptr2)
    {
        temp = *ptr1;
        *ptr1 = *ptr2;
        *ptr2 = temp;
    }
}
void ascending3(int* ptr1, int* ptr2, int* ptr3)
{
    ascending2(ptr1, ptr2);
    ascending2(ptr2, ptr3);
    ascending2(ptr1, ptr2);
}
void descending3(int* ptr1, int* ptr2, int* ptr3)
{
    ascending3(ptr3, ptr2, ptr1);
}
