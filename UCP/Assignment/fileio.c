#include "fileio.h"
/*This file does file IO
reads numberof lines which is then used to return a malloc'd
2D char array*/
int countLines(char* filename)
{
    int ch=0, lines=0;
    FILE* in = fopen(filename, "r");
    if(ferror(in))
        perror("Does the input file exist\n");

    while(!feof(in))
    {
        ch=fgetc(in);
        if(ch == '\n')/*end of line reached*/
            lines++;
    }
    lines++;
    fclose(in);

    return lines;
}

char** readFile(char* filename, int LineNums)
{
    char ** characters;
    int i;

    FILE* in = fopen(filename, "r");
    if(ferror(in))
        perror("Does the input file exist\n");

    characters = (char**)malloc(LineNums*sizeof(char*));
    for(i=0; i<LineNums; i++)
    {
        characters[i] = (char*)malloc(INPUT_SIZE*sizeof(char));
    }
    /*Allocating space on the heap for each line*/

    rewind(in);
    i=0;
    while(fgets(characters[i], INPUT_SIZE, in) != NULL)
    {
        i++;
    }
    /*Stored file into line by line strings*/
    
    if(ferror(in))
        perror("Error reading from file\n");
    
    fclose(in);

    return characters;
}

void freeFile(char** file, int LineNums)
{
    int i;   
    for(i=0; i<LineNums; i++)
    {
        free(file[i]);
        file[i] = NULL;
        /*freeing each line*/
    }
    free(file);
    file = NULL;
    /*freeing parent pointer ;)*/
}
