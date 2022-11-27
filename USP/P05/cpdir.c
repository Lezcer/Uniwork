#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>

int main(int argc, char* argv)
{
    if(argc==3)
    {
        /*Open file directories to see if they exist
        man opendir*/

        DIR *sourceDir, *destinationDir;
        struct dirent *directory;
        int fd;
        
        sourceDir = opendir(argv[1]);
        destinationDir = opendir(argv[2]);

        if(sourceDir!=NULL||destinationDir!=NULL)/*The files both exist*/
        {
            directory = readdir(sourceDir);
            while(directory!=NULL)
            {
                fd = open(directory->
                /*once done writing*/
                directory = readdir(sourceDir);
            }
        }
        else
        {
            printf("One or more directories don't exist.");
        }
    }
    else
    {
        printf("Please enter 3 command line arguments;");
        printf("./cdpir <filesSource> <Destination>");
    }
    return 0;
}
