#include <stdio.h>
#include <stdlib.h>
#include "fileio.h"
#include "bracketcheck.h"

int main(int argc, char** argv)
{
    if(argc == 2)
    {
        int lineNums;
        char** characters;

        lineNums = countLines(argv[1]);
        /*Acquire number of lines*/
        characters = readFile(argv[1], lineNums);
        /*Read file and store all chars in a 2D array*/
        check(characters, lineNums);
        /*Call to Bracket Check*/
        freeFile(characters, lineNums);
        /*Now free the 2D char array*/
    }
    else
    {
        printf("To run the program ./<program> <file>\n");
    }
    return 0;
}
