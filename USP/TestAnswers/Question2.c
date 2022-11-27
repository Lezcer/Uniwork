#include <termios.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <signal.h>

/*don't have enough time to test sory*/

struct termios old;
FILE* fp;

int revert()
{
    tcsetattr(fileno(fp), TCSANOW, &old);
	printf("Exiting\n");
    exit(0);
}
int main()
{
    char* username, *password;

    /*copy current settings to old global var*/
    struct termios settings;
    fp = fopen(ctermid(NULL), "r+");
    tcgetattr(fileno(fp), &old);
    settings = old;

    /*ask for username*/
    printf("Enter username: ");
    str = (char*)malloc(sizeof(char)*20);
    scanf("%s", str);
    /*output: enter password:*/
    printf("\nEnter password: ");

    /*enter mode*/
    settings.c_lflag &= ~ECHO; /*Don't display input*/
    settings.c_lflag &= ~ICANON; /*Turn off canonical mode*/
	settings.c_cc[VMIN] = 5; /*read input for 2 seconds*/
    tcsetattr(fileno(fp), TCSANOW, &settings);
    signal(SIGINT, revert);

    /*input for password*/
    scanf("%s", password);

    /*reset settings*/
    tcsetattr(fileno(fp), TCSANOW, &old);
    return 0;
}