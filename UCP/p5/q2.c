#include <stdio.h>
#include <string.h>
#define LENGTH 500

int main(int argc, char** argv)
{
    if(argc != 2)
    {
        printf("Please enter 2 command-line arguments.\n");
        printf("./<program_name> <infile>.<ext>\n");
    }
    else
    {
        FILE* in = fopen(argv[1], "r");
        char month[LENGTH], process[LENGTH], message[LENGTH];

        int day, hour, min, sec, total;
        
        if(ferror(in))
            perror("Does the input file exist\n");

        while(fscanf(in, "%s %d %d:%d:%d %[^:]: %[^\n]\n", month, &day, &hour,
        &min, &sec, process, message) != EOF)
        {
            total = sec + (min * 60) + (hour * 3600);
            printf("TIME: %d, MESSAGE: %s\n", total, message);
        }

        if(ferror(in))
            perror("Error reading from file\n");
        
        fclose(in);
    }
    return 0;
}
