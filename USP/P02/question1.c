#include <stdio.h>
#include <unistd.h>

int main()
{
    int n;
    printf("starting... \n");
    while(1)
    {
        printf("input: ");
        scanf("%d",&n);

        printf(".\n");
        /*fflush(stdout);*/
        sleep(1);
    }
    
    return 0;
}
