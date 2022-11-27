/* File:       helloWorld02.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    Improves on previous version of helloWorld01.c by
**             including some simple error checking.
*/
#include <curses.h>
#include <stdio.h>
#include <stdlib.h>

int main ()
{
   WINDOW * mainwin;

   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (1);
   }
   move(13, 33);                /* place cursor roughly in middle */
   addstr ("Hello World!");     /* Print Hello World              */
   refresh ();                  /* Print it on to the real screen */
   sleep(3);                    /* Sleep 3 seconds                */
   delwin (mainwin);            /* Delete window created          */
   endwin ();                   /* End curses mode                */
   return 0;
}
