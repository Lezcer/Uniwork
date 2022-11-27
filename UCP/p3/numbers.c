#include <stdio.h>
#include "order.h"

char readInt(int*, int*, int*);
int main(void)
{
    int num1, num2, num3;
    char choice = readInt(&num1, &num2, &num3);
    switch(choice)
    {
        case 'A': ascending3(&num1, &num2, &num3); break; 
        case 'D': descending3(&num1, &num2, &num3); break; 
    }
    printf("Your input in order that you chose is: ");
    printf(" %d, %d and last but not least %d\n", num1, num2, num3);
    return 0;
}
char readInt(int* ptr1, int* ptr2, int* ptr3)
{
    char choice;

    printf("Welcome to Read Integers!\n");

    printf("Please enter the first number: ");  
    scanf("%d", ptr1);
    printf("Please enter the second number: ");  
    scanf("%d", ptr2);
    printf("Please enter the third number: ");  
    scanf("%d", ptr3);
    printf("Please enter either 'A'scending or 'D'escending: ");  
    scanf(" %c", &choice);
    return choice;
}
