#include <stdio.h>

int main(int argc, char** argv)
{
    if(argc != 3)
    {
        printf("Please enter 3 command-line arguments.\n");
        printf("./<program_name> <infile>.<ext> <outfile>.<ext>\n");
    }
    else
    {
        FILE* in = fopen(argv[1], "r");
        FILE* out = fopen(argv[2], "w");
        int character;
        
        if(ferror(in))
            perror("Does the input file exist\n");

        do
        {
            character = fgetc(in);
            if(character != EOF)
            {
                fputc((char)character, out);
            }
        }
        while(character != EOF);
        
        if(ferror(in))
            perror("Error reading from file\n");
        
        if(ferror(out))
            perror("Error writing to file\n");
        
        fclose(in);
        fclose(out);
    }
    return 0;
}
