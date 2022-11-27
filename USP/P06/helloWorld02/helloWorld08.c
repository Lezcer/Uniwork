/* File:       helloWorld08.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    With now have greater control over time this
**             program begins to process user input as commands.
**             The movement/redraw is handled in the signal handler and
**             the main loop is used to process user commands.
**             What is happening here is that we have a function
**             blocking on on user input but a ticking timer cause the 
**             message to appear at a steady rate.  This program is
**             doing two things at once.
** 
**             user input:
**                  s slow down x component, S: slow y  component
**                  f speed up x component,  F: speed y component
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <curses.h>
#include <signal.h>
#include <sys/time.h>
#include <string.h>

#define MESSAGE " Hello World! "
int direction = 1;    /* where we are going   */

void onTick ();
int setTick (long msec);

int main (void)
{
   WINDOW *mainwin;
   int delay = 100;             /* 1000 ms = 1 second   */
   int ndelay = 0;
   char cmd;

   /*  Initialize ncurses  */
   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }

   crmode ();
   noecho ();

   clear ();

   setTick (delay);
   signal (SIGALRM, onTick);
   while (cmd != 'q')
   {
      ndelay = 0;
      cmd = getch ();
      switch (cmd)
      {
      case 'Q':
         break;
      case ' ':
         direction = -direction;
         break;
      case 'f':
         if (delay > 2)
         {
            ndelay = delay / 2;
         }
         break;
      case 's':
         ndelay = delay * 2;
         break;
      default:
         break;
      }
      if (ndelay > 0)
      {
         setTick (delay = ndelay);
      }

   }

   /*  Clean up after ourselves  */
   delwin (mainwin);
   endwin ();
   refresh ();
   return EXIT_SUCCESS;
}



void onTick ()
{
   static int cur_row = 10;     /* current row          */
   static int cur_col = 0;      /* current column       */
   signal (SIGALRM, onTick);    /* reset, just in case  */
   cur_col += direction;        /* move to new column   */
   move (cur_row, cur_col);     /* then set cursor      */
   addstr (MESSAGE);            /* redo message         */
   refresh ();                  /* and show it          */

   /** Now handle borders            */
   if (direction == -1 && cur_col == 0)
   {
      direction = 1;
   }
   else if (direction == 1 && cur_col + strlen (MESSAGE) >= COLS)
   {
      direction = -1;
   }


}


int setTick (long msecs)
{
   struct itimerval value, ovalue, pvalue;
   int which = ITIMER_REAL;
   int result = 0;
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
   return result;
}
