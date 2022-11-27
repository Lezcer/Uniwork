#include <stdio.h>

int factorial(int);

int main(void)
{
    int number;
    int result;
    do
    {
        printf("Please enter the number you wish to factorialise \n");
        printf("Please make sure its either 0 or greater than it \n");
        scanf("%d", &number);
    }
    while(number < 0);
    
    result = factorial(number);
    printf("The factorial is %d\n" , result);

    return 0;
}

int factorial(int n)
{
    int i;
    int result = 1;

    for(i=n; i>0; i--)
    {
        result = result * i;
    }
    return result;
}

