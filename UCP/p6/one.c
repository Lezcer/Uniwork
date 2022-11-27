#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv)
{
    if(argc == 2)    
    {
        typedef struct {
                            int day;
                            int month;
                            int year;} Date;        
        typedef struct {
                            Date date;
                            char* string;} Entry;

        FILE* in = fopen("Journal", "r");
        int entries, j, num, eDay, eMonth, eYear;
        int index = atoi(argv[1]) - 1;
        Date entryDate;
        char* entryString;
        Entry entry;
        Entry* array;
        
        if(ferror(in))
            perror("Does the input file exist?\n");
    
        num = fscanf(in, "%d", &entries);
        array = (Entry*)malloc(entries*sizeof(Entry));
        for(j=0; j<entries; j++)
        {
            /*Read date - store each component into a date struct*/
            num = fscanf(in, "%d/%d/%d\n", &eDay, &eMonth, &eYear);
            entryDate.day = eDay;
            entryDate.month = eMonth;
            entryDate.year = eYear;
            /*printf("%d", eDay);*/
            /*Read String*/
            entryString = (char*)malloc(500*sizeof(char));
            num = fscanf(in, "%[^\n] ", entryString); 
            /*Store Date and String into the struct array*/
            array[j].date=entryDate;
            array[j].string=entryString;
        }
        printf("%d/%02d/%02d: ", 
        array[index].date.year,
        array[index].date.month,
        array[index].date.day);
        printf("%s\n", array[index].string);
        
        if(ferror(in))
            perror("Does the input file exist?\n");
        fclose(in);  
    }
    else
    {
        printf("Wrong format: ./<executable file> <index-no-on-journal>");
    }

    return 0;
}
