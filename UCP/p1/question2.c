#include <stdio.h>

int main(void)
{
    int number1, number2;

    printf("Please enter your first integer. \n");
    scanf("%d", &number1);
    printf("Please enter your second integer. \n");
    scanf("%d", &number2);

    if (number1 % number2 == 0)
    {
        printf("DIVISIBLE \n");
    }
    else
    {
        printf("NOT DIVISIBLE \n");
    }
    return 0;
}
