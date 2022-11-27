#include <stdio.h>
#include "macros.h"
#include "powers.h"

int main (void)
{
    int choice, isitBetw, numoop, i, num;
    int il, iu, ix;
    double dl, du, dx;
    char cl, cu, cx;

    printf("BoUNDs chECKeR\n");

    printf("1. Int 2. Real 3. Char 4. Power of 2: ");  
    scanf("%d\n", &choice);

    switch(choice)
    {
        case 1:
            printf("Please enter your lower number: ");
            scanf("%d\n", &il);
            printf("Please enter your upper number: ");
            scanf("%d\n", &iu);
            printf("Please enter your number: ");
            scanf("%d\n", &ix);
            isitBetw = BETWEEN(il, iu, ix);
            if(isitBetw == FALSE)
            {
                printf("NOPE\n");
            }
            else
            {
                printf("YES\n");
            }
        break;
        case 2:
            printf("Please enter your lower number: ");
            scanf("%lf\n", &dl);
            printf("Please enter your upper number: ");
            scanf("%lf\n", &du);
            printf("Please enter your number: ");
            scanf("%lf\n", &dx);
            isitBetw = BETWEEN(dl, du, dx);
            if(isitBetw == FALSE)
            {
                printf("NOPE\n");
            }
            else
            {
                printf("YES\n");
            }
        break;
        case 3:
            printf("Please enter your lower character: ");
            scanf("%c\n", &cl);
            printf("Please enter your upper character: ");
            scanf("%c\n", &cu);
            printf("Please enter your character: ");
            scanf("%c\n", &cx);
            isitBetw = BETWEEN(cl, cu, cx);
            if(isitBetw == FALSE)
            {
                printf("NOPE\n");
            }
            else
            {
                printf("YES\n");
            }
        break;
        case 4:
            do{
                printf("How many times do you want to run powers of 2");
                scanf("%d\n", &numoop);
            }while(!BETWEEN(1, 31, numoop));
            
            for(i=1; i<=numoop; i++)
            {
               num = power2(); 
            }
            printf("The result is: %d\n", num);
        break;
        default:
            printf("GOODBYE");
    }
    return 0;
}
