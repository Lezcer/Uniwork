#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char** argv)
{
        
    if(argc==2)
    {
        int sourceFd, destinationFd, ret;
        char *buff = (char*)malloc(100*sizeof(char)); 
     
        
        char filename[25] = "Copied_";
        strncat(filename, argv[1], 7);

        sourceFd = open(argv[1], O_RDONLY);

        if(sourceFd<0)/*-1 meaning error*/
        {
            printf("Original File doesn't exist.\n");
        }
        else
        {
            destinationFd = open(filename, O_CREAT);
            ret = read(sourceFd, buff, 100);
            while(ret>0)
            {
                write(destinationFd, buff, 100);
                ret = read(sourceFd, buff, 100);
            }/*read and write here*/
            close(destinationFd);
            close(sourceFd);
        }
        
    }
    else
    {
        printf("Please enter 3 command line arguments;\n");
        printf("./cdfile <filesSource> <Destination>\n");
    }
    return 0;
}
