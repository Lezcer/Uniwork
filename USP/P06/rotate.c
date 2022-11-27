/* rotate.c */
#include <termios.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <signal.h>

struct termios old;
FILE* fp;

void revert (int num)
{
    tcsetattr(fileno(fp), TCSANOW, &old);
	printf("Exiting\n");
    exit(0);
}
int main ()
{
	int c;
    struct termios settings;
    fp = fopen(ctermid(NULL), "r+");
    tcgetattr(fileno(fp), &old);
    settings = old;

    settings.c_lflag &= ~ECHO; /*Don't display input*/
    settings.c_lflag &= ~ICANON; /*Turn off canonical mode*/
	settings.c_cc[VTIME] = 20; /*read input for 2 seconds*/
    tcsetattr(fileno(fp), TCSANOW, &settings);
    signal(SIGINT, revert);
	while((c = getchar()) != EOF)
	{
		if (c == 'z')
		{
			c = 'a';
		}
		else if (islower (c))
		{
			c++;
		}
		putchar (c);
	}
	tcsetattr(fileno(fp), TCSANOW, &old);
}

