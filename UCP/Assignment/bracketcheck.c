#include "bracketcheck.h"
void check(char** file, int numOfLines)
{
    LinkedList* list = createLinkedList();    
    int i=0, charAt=0, match=TRUE;
    
    char* message;

    while(i<numOfLines && match)
    {
        charAt =0;
        while(charAt<strlen(file[i]) && match)
        {
            switch(file[i][charAt])
            {
                case '{': 
                    insertStart(list, &file[i][charAt]); 
                    #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                    #endif
                break;
                case '}': 
                    if(isEmpty(list))   
                    {
                        match = FALSE;
                        message = ERROR "There are no opening brackets before\n" RESET;
                    }
                    else if (*(char*)removeStart(list) != '{')
                    {
                        match = FALSE;
                        message = ERROR "'{' was expected before.\n" RESET;
                    }
                    else
                    {
                        #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                        
                        #endif
                    }
                break;
                case '(': 
                    insertStart(list, &file[i][charAt]); 
                    #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                    
                    #endif
                break;
                case ')': 
                    if(isEmpty(list))   
                    {
                        match = FALSE;
                        message = ERROR "There are no opening brackets before\n" RESET;
                    }
                    else if(*(char*)removeStart(list) != '(')
                    {
                        match = FALSE;
                        message = ERROR "'(' was expected before.\n" RESET;
                    }
                    else
                    {
                        #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                        
                        #endif
                    }
                break;
                case '[': 
                    insertStart(list, &file[i][charAt]); 
                    #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                    
                    #endif
                break;
                case ']': 
                    if(isEmpty(list))   
                    {
                        match = FALSE;
                        message = ERROR "There are no opening brackets before\n" RESET;
                    }
                    else if(*(char*)removeStart(list) != '[')
                    {
                        match = FALSE;
                        message = ERROR "'[' was expected before.\n" RESET;
                    }
                    else
                    {
                        #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                        
                        #endif
                    }
                break;
                case '<': 
                    insertStart(list, &file[i][charAt]); 
                    #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                    
                    #endif
                break;
                case '>': 
                    if(isEmpty(list))   
                    {
                        match = FALSE;
                        message = ERROR "There are no opening brackets before\n" RESET;
                    }
                    else if(*(char*)removeStart(list) != '<')
                    {
                        match = FALSE;
                        message = ERROR "'<' was expected before.\n" RESET;
                    }
                    else
                    {
                        #ifdef STACK
                        if(!isEmpty(list))
                        {
                        system("clear");
                        printLinkedList(list, printLL);    
                        printf("\n");
                        }
                        #endif
                    }
                break;
                default: 
                break; 
                /*For every character check if its a bracket;
                if it is a bracket then either insert if 
                its an opening one or removefirst and check if
                a the bracket is a closing one, do nothing if 
                its any other char*/
            }
            charAt++;
            newSleep(0.05);
            system("clear");
            display(file, message, numOfLines, i, charAt);
            /*After we traverse over a single char in the 2D
            array we need to 'refresh' the screen with the
            cursor*/
        }
        i++;
    }
    if(list->count == 0 && match)
        printf(CURLY "All Good!\n" RESET);
    else if(message[0] == '\0')
        printf(ROUND "EOF Character reached\n" RESET);
    /*Any other logic if all chars were traversed through*/
}

void display(char** file, char* errorMessage, int numLines, int currentLine, int currentChar)
{
    /*Display is separated in 5 parts
    1. Displaying all lines that are before currentline with color
    2. Displaying all the characters up to currentchar with color
    3. Display rest of the currentline with no color
    4. Display spaces and then the cursor (AND MESSAGE if error)
    5. Display all the other lines after current with no color*/

    int i = 0, charAt;
    while(i<currentLine)
    {
        for(charAt=0; charAt<strlen(file[i]); charAt++)
        {
            switch(file[i][charAt])
            {
                case '{': printf(CURLY "%c" RESET, file[i][charAt]); break;
                case '}': printf(CURLY "%c" RESET, file[i][charAt]); break;
                case '(': printf(ROUND "%c" RESET, file[i][charAt]); break;
                case ')': printf(ROUND "%c" RESET, file[i][charAt]); break;
                case '[': printf(SQUARE "%c" RESET, file[i][charAt]); break;
                case ']': printf(SQUARE "%c" RESET, file[i][charAt]); break;
                case '<': printf(ANGLE "%c" RESET, file[i][charAt]); break;
                case '>': printf(ANGLE "%c" RESET, file[i][charAt]); break;
                default: printf("%c", file[i][charAt]); break;
            }
                 
        }/*checking character at a time - highlighting brackets*/
        i++;
    }/*This prints out all the lines before the current line*/
    
    for(charAt=0; charAt<currentChar; charAt++)
    {
        switch(file[currentLine][charAt])
        {
            case '{': printf(CURLY "%c" RESET, file[currentLine][charAt]); break;
            case '}': printf(CURLY "%c" RESET, file[currentLine][charAt]); break;
            case '(': printf(ROUND "%c" RESET, file[currentLine][charAt]); break;
            case ')': printf(ROUND "%c" RESET, file[currentLine][charAt]); break;
            case '[': printf(SQUARE "%c" RESET, file[currentLine][charAt]); break;
            case ']': printf(SQUARE "%c" RESET, file[currentLine][charAt]); break;
            case '<': printf(ANGLE "%c" RESET, file[currentLine][charAt]); break;
            case '>': printf(ANGLE "%c" RESET, file[currentLine][charAt]); break;
            default: printf("%c", file[currentLine][charAt]); break;
        }/*I KNOW I JUST REPEATED CODE ;-;*/
             
    }/*checking character at a time - highlighting brackets*/

    for(charAt=currentChar; charAt<strlen(file[currentLine]); charAt++)
    {
        printf("%c", file[currentLine][charAt]);
    }/*Print the rest of the 'current line' out*/

    for(i=1; i<currentChar; i++)
    {
        printf(" ");
    }
    printf("^\n");
    printf("%s", errorMessage);

    for(i=(currentLine+1); i<numLines; i++)
    {
        for(charAt=0; charAt<strlen(file[i]); charAt++)
        {
            printf("%c", file[i][charAt]);
        }/*Print the rest of the file out*/
    }
}
void printLL(void* data)
{
   /*display LL*/
   char ch = *(char*)data;
   printf("%c ", ch);
}
void freeLL(void* data)
{
   free(data);
}
