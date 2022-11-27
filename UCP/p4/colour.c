#include "colour.h"

void switchTo(char* nameOfColour)
{
    if (nameComp(nameOfColour, "white") == TRUE)
    {
        printf("\033[0m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "reset") == TRUE)
    {
        printf("\033[0m");
    }
    else if (nameComp(nameOfColour, "green") == TRUE)
    {
        printf("\033[0;32m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "magneta") == TRUE)
    {
        printf("\033[0;35m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "yellow") == TRUE)
    {
        printf("\033[0;33m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "blue") == TRUE)
    {
        printf("\033[0;34m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "cyan") == TRUE)
    {
        printf("\033[0;36m");
        printf("\033[4m");
    }
    else if (nameComp(nameOfColour, "red") == TRUE)
    {
        printf("\033[0;31m");
        printf("\033[4m");
    }
    else
    {
        printf("\033[1;34m");
        printf("\033[4m");
    }
}
int nameComp(char* input, char* colour)
{
    int i = 0;
    int match = FALSE;/*assume is false*/

    while(input[i] == colour[i] && (input[i] != '\0' || colour[i] != '\0'))
        {i++;}
        /*Keeps looping till the two chars from strings
        aren't the same OR one of the strings reached 
        the end.*/

    if(input[i] == colour[i])
        {match = TRUE;}
        /*The previous loop will end, and once it ends
        two values must be equal, or that means one reached 
        the end. If the two chars were never equal then this
        WILL NOT be run.*/

    return match;
}
