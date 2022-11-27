#include <stdio.h>
#include <stdlib.h>
#include "plot.h"

int main(int argc, char** argv)
{
    if(argc==2)
    {
        FILE* in = fopen(argv[1], "r");
        int num, rows, columns, i, j;
        double** array;
        
        if(ferror(in))
            perror("Does the input file exist?\n");
    
        num = fscanf(in, "%d %d\n", &rows, &columns);
        printf("There are %d integers in the first line!\n", num);
        printf("There are %d rows and %d columns.\n", rows, columns);

        array = (double**)malloc(rows*sizeof(double*));
        for(i=0; i<rows; i++)
        {
            array[i] = (double*)malloc(columns*sizeof(double));
        }
            /**/
        for(i=0; i<rows; i++)
        {
            for(j=0; j<columns; j++)
            {
                num = fscanf(in, "%lf", &array[i][j]);
            }/**/
        }

        if(ferror(in))
            perror("Does the input file exist?\n");

        plot(array, rows, columns);/**/
    
        
        fclose(in);  
    }
    else
    {
        printf("Please enter 2 command line arguments.\n");
        printf("./<program> <inputfile>.<extension>\n");
    }
    return 0;
}
