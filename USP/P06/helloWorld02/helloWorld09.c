/* File:       helloWorld09.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    Demonstrate 2D animation by bouncing a message around 
**             the screen  defined by some parameters
**  
**             user input:
**                s slow down x component, S: slow y  component
**                f speed up x component,  F: speed y component
**                q quit
**
**             Blocks on read, but timer tick sets SIGALRM which are
**             caught by move
**
**             This program really should be split into a few files.  
**             At the very least a header file, perhaps and ADT for 
**             "the ball"
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <curses.h>
#include <signal.h>
#include <sys/time.h>
#include <string.h>

/* some parameters */
#define BLANK           "              "
#define MESSAGE         " Hello World! "
#define DFL_message      '*'
#define TOP_ROW         5
#define BOT_ROW         20
#define LEFT_EDGE       5
#define RIGHT_EDGE      65
#define X_INIT          10      /* starting col         */
#define Y_INIT          10      /* starting row         */
#define TICKS_PER_SEC   90      /* affects speed        */
#define XTICK           5
#define YTICK           8
#define STOP            0

struct ball
{
   int y, 
       yTick, 
       yTime, 
       yDirection, 
       x, 
       xTick, 
       xTime, 
       xDirection;
   char * message;
};

struct ball theBall;

void doMove ();
void setTick (long msec);
void edgeCollision (struct ball *bp);

int main (void)
{
   int delay = 1000;            /* 1000 ms = 1 second   */
   int ndelay = 0;
   char cmd;

   theBall.y = Y_INIT;
   theBall.x = X_INIT;
   theBall.yTime = theBall.yTick = YTICK;
   theBall.xTime = theBall.xTick = XTICK;
   theBall.yDirection = 1;
   theBall.xDirection = 1;
   theBall.message = MESSAGE;

   /*  Initialize ncurses  */
   if (initscr() == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }
   crmode ();
   noecho ();
   refresh ();
   signal (SIGINT, SIG_IGN);
   signal (SIGALRM, doMove);
   setTick (delay / TICKS_PER_SEC); /* send millisecs per tick */

   while (cmd != 'q')
   {
      cmd = getch ();
      switch (cmd)
      {
      case 'f':
         theBall.xTick--;
         break;
      case 'F':
         theBall.yTick--;
         break;
      case 's':
         theBall.xTick++;
         break;
      case 'S':
         theBall.yTick++;
         break;
      default:
         break;
      }
   }

   /*  Clean up after ourselves  */
   refresh ();
   endwin ();
   setTick (STOP);
   return EXIT_SUCCESS;
}



void doMove ()
{
   int y_cur, x_cur, moved;

   signal (SIGALRM, SIG_IGN);   /* dont get caught now  */
   y_cur = theBall.y;      
   x_cur = theBall.x;
   moved = 0;

   if ((theBall.yTick > 0) && (theBall.yTime-- == 1))
   {
      theBall.y += theBall.yDirection;   /* move */
      theBall.yTime = theBall.yTick; /* reset */
      moved = 1;
   }

   if ((theBall.xTick > 0) && (theBall.xTime-- == 1))
   {
      theBall.x += theBall.xDirection;   /* move */
      theBall.xTime = theBall.xTick; /* reset */
      moved = 1;
   }

   if (moved)
   {
      move (y_cur, x_cur);
      addstr (BLANK);
      move (theBall.y, theBall.x);
      addstr (theBall.message);
      edgeCollision (&theBall);
      refresh ();
   }
   signal (SIGALRM, doMove); /* for unreliable systems */
}


void edgeCollision (struct ball *bp)
{
   if (bp->y == TOP_ROW)
   {
      bp->yDirection = 1;
   }
   else if (bp->y == BOT_ROW)
   {
      bp->yDirection = -1;
   }

   if (bp->x == LEFT_EDGE)
   {
      bp->xDirection = 1;
   }
   else if (bp->x == RIGHT_EDGE)
   {
      bp->xDirection = -1;
   }
}


void setTick (long msecs)
{
   struct itimerval value, ovalue, pvalue;
   int which = ITIMER_REAL;
   long n_sec, n_usecs;

   /* Calculate interval and ensure integer number of intervals */
   n_sec = msecs / 1000;
   n_usecs = (msecs % 1000) * 1000L;

   /* Set a real time interval timer to repeat every 200 milliseconds */
   value.it_interval.tv_sec = n_sec;
   value.it_interval.tv_usec = n_usecs;
   value.it_value.tv_sec = n_sec;
   value.it_value.tv_usec = n_usecs;

   setitimer (which, &value, &ovalue);
}
