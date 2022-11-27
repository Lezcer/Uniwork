/*Name: Salah Mahamod
  Purpose: Contains main() and does the following:
    (1) Read File                      ipc.c line 39
    (2) Store lines into Linked list   ipc.c line 71
    (3) fork()                         ipc.c line 88
    (4) execve() given command         ipc.c line 111
    (5) Final output? stdout or file   ipc.c line 114
  Notes for marker: works as expected but no sys call for malloc() and also not
                    freeing() at all
  Date: 23/05/2021
*/

#include "ipc.h"
int main(int argc, char** argv, char** envp)
{
    char **line, character;
    char **args;
    int inFD, outFD, i, j, k, pid, check, num;
    void* buff;
    LinkedList* commands;
    char* environ[] = {"PATH=./", NULL};
    char* readme[] = {"cat", "README", NULL};
    char* pathname;
    int** readpipe;

    if(argc!=2 && argc!=3)
    {
        write(1,"Not Enough commands, Please read USAGE below.\n", 46);
        execve("/bin/cat", readme, environ);
    }/*going to execute "cat README" for the user*/
    outFD = DEFAULTOUTFD;/*set this as the stdout file descriptor by default*/
    if(argc==3)
    {        
        outFD = open(argv[2], O_WRONLY | O_CREAT);
    }/*if there's a third argument change the output file descriptor to opened file*/
    inFD = open(argv[1], O_RDONLY);
    buff = (char*)malloc(sizeof(char));/*not sys call*/
    commands = createLinkedList();
    /*PART 1: Read File*/
    num=0;
    do
    {
        line = (char**)malloc(2*ARGNUM*sizeof(char*));/*not sys call*/
        for(i=0; i<ARGNUM; i++)
        {
            line[i] = (char*)malloc(2*ARGCHAR*sizeof(char));/*not sys call*/
        }/*2D array malloc*/
        i=0;
        do
        {
            j=0;/*reset position of string to 0*/
            do
            {
                check = read(inFD, buff, 1);
                character = *((char*)buff);
                if(character != ' ')
                    line[i][j] = character;
                    printf("%c_", character);
                j++;/*indicates position with in string*/
            }while(character != ' ' && character != '\n' && check>0);
            /*iterates through each character within an arg*/
            printf("%c_", character);
            line[i][j-1] = '\0';/*set null byte at the end*/
            i++;
        }while(character != '\n' && check>0);
        line[i] = NULL;
        num++;
        /*PART 2: Store lines in a linked list*/
        
        print(line);
        insertLast(commands, line);
    }while(check>0);
    insertandRemoveTest(commands);
    close(inFD);
    /*iterates through lines in the file*/
    /*PART 2: Create child process using fork() glibc wrapper system call*/
    readpipe = (int**)malloc(num*sizeof(int*));/*not sys call*/
    for(k=0; k<num; k++)
    {
        readpipe[k] = (int*)malloc(2*sizeof(int*));/*not sys call*/
        pipe(readpipe[k]);
    }/*k-1 pipes are required for ipc as each process needs a write and/or read*/

    j=0;
    i=0;/*index for pipes*/
    while(!isEmpty(commands))
    {
        pathname  = (char*)malloc(20*sizeof(char));/*not sys call*/
        resetpathname(pathname);
        args =  (char**)removeStart(commands);
        strcat(pathname, args[0]);/*concatenate the command name to "/bin/<command>"*/
        /*PART 3: fork() child creation*/
        pid = fork();
        if (pid==0) 
        {
            if(j==0)/*first iteration - 1st process requires no input: write*/
            {
                close(readpipe[i][0]);
                dup2(readpipe[i][1],1);/*write operation*/

            }/*first iteration - 1st process requires no input*/
            else if(j==1 && !isEmpty(commands))
            {
                close(readpipe[i-1][1]);
                dup2(readpipe[i-1][0],0);/*read operation*/

                close(readpipe[i][0]);
                dup2(readpipe[i][1],1);/*write op*/
            }/*2nd iteration to <last>-1th iteration: read and write*/
            else if(isEmpty(commands))
            {
                close(readpipe[i-1][1]);
                dup2(readpipe[i-1][0],0);/*read operation*/
                /*PART 5: FINAL COMMAND EXECUTION, output to file or stdout?*/
                dup2(outFD,1);/*write op: either to file or stdout*/
            }/*last iteration: read*/
            /*PART 4: execve() executing a file*/
            printf("%s ", args[0]);
            execve(pathname, args, environ);
        }
        else if (pid>0)/*PARENT PROCESS*/
        {
            
            if(j==0)
            {
                close(readpipe[i][1]);
            }
            else if(j==1 && !isEmpty(commands))
            {
                close(readpipe[i-1][0]);
                close(readpipe[i][1]);
            }
            else if(isEmpty(commands))
            {
                close(readpipe[i-1][0]);
                close(outFD);
            }/*parrent process closes the end used by the child process*/     
            wait(NULL);       
        }
        j=1;/*flag for reading redirected output. first process doesn't read*/ 
        i++;/*increment current pipe location*/
    }
    return 0;
}
void resetpathname(char* pathname)
{
    pathname[0] = '/';
    pathname[1] = 'b';
    pathname[2] = 'i';
    pathname[3] = 'n';
    pathname[4] = '/';
    pathname[5] = '\0';
}/*resets the pathname variable back to "/bin/\0" for string concatentaion*/