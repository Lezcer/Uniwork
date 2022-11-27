#include <stdio.h>
#include "Question4.h"

void displayArray(int* array, int length)
{
    int i;
    printf("[");
    for(i=0;i<length;i++)
    {
        if(i != (length - 1))
        {
            printf("%d, ", array[i]);
        }
        else 
        {
            printf("%d", array[i]);
        }
    }
    printf("]\n");
}
