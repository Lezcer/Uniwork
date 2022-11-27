#include <stdio.h>
#include "Question4.h"
#include "colour.h"

int sum(int*, int);
int max(int*, int);
void reverse(int*, int);

int main(void)
{
    int array[] = {3,6,7,8,11};
    int arra[] = {3,6,7,6,8};
    int arr[] = {3,6,7,8,45,89,90};
    int nu, n;

    int num = sum(array, 5);
    displayArray(array, 5);
    switchTo("red");
    printf("Sum of all elements is: %d\n", num);  
    switchTo("reset");

    nu = max(arra, 5);
    displayArray(array, 5);
    switchTo("blue");
    printf("The largest element is: %d\n", nu);  
    switchTo("reset");

    n = sum(arr, 7);
    displayArray(arr, 7);
    switchTo("cyan");
    printf("Sum of all elements is: %d\n", n); 
    switchTo("reset");

    
    reverse(arr, 7); 
    displayArray(arr, 7);
    switchTo("cyan");
    printf("Previous Array Reversed!\n"); 
    return 0;
}
int sum(int* array, int LENGTH)
{
    int total = 0;
    int i;
    for(i=0; i<LENGTH; i++)
    {
        total = total + array[i];
    }
    return total;
}
int max(int* array, int LENGTH)
{
    int largest = array[0];
    int i;
    for(i=1; i<LENGTH; i++)
    {
        if(largest<array[i])
        {
            largest = array[i];
        }
    }
    return largest;
}

void reverse(int* array, int LENGTH)
{
    int i, temp;
    for(i=0; i<(LENGTH/2); i++)
    {
        temp = array[i];
        array[i] = array[LENGTH-1-i];
        array[LENGTH-1-i] = temp;
    }
}

