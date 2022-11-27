/* File:       helloWorld07.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    This program demonstrates how to use more precise
**             timings through the use of signals.
**
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <curses.h>
#include <signal.h>
#include <sys/time.h>
#include <string.h>

#define MESSAGE " Hello World! "

void onTick ();
int setAlarm (long msec);

int main (void)
{
   WINDOW *mainwin;
   int delay = 100;      /* 1000 ms = 1 second   */

   /*  Initialize ncurses  */
   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }

   clear ();
   signal (SIGALRM, onTick);
   setAlarm (delay);
   while (1)
   {
       /* endless loop */
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
   static int direction = 1;    /* where we are going   */
   signal (SIGALRM, onTick); /* reset, just in case  */
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


int setAlarm (long msecs)
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

   getitimer (which, &pvalue);
   setitimer (which, &value, &ovalue);
   /* The interval timer value returned  by setitimer() should be
    ** identical to the timer value returned by getitimer().  **/
   if (ovalue.it_interval.tv_sec != pvalue.it_interval.tv_sec ||
       ovalue.it_interval.tv_usec != pvalue.it_interval.tv_usec ||
       ovalue.it_value.tv_sec != pvalue.it_value.tv_sec ||
       ovalue.it_value.tv_usec != pvalue.it_value.tv_usec)
   {
      printf ("Real time interval timer mismatch\n");
      result = -1;
   }
   return result;
}
