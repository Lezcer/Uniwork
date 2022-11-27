/* File:       helloWorld05.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    This program extends "Hello, world!" and demonstrates simple
**             animation by showing how to erase draw. And toggle the
**             direction when it hits the edge.
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <curses.h>

#define LEFTEDGE        10
#define RIGHTEDGE       30
#define ROW             10

int main (void)
{
   WINDOW *mainwin;
   char msg[] = " Hello World! ";   /* notice padding spaces */
   int dir = +1;
   int pos = LEFTEDGE;
   int i;

   /*  Initialize ncurses  */
   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }

   clear ();
   while (1)
   {
      move (ROW, pos);
      addstr (msg);                /* draw it */
      move (LINES - 1, COLS - 1);  /* park the cursor      */
      refresh ();
      pos += dir;                  /* advance position     */
      if (pos >= RIGHTEDGE)        /* check for bounce     */
      {
         dir = -1;
      }
      if (pos <= LEFTEDGE)
      {
         dir = +1;
      }
      sleep (1);
   }

   /*  Clean up after ourselves  */
   delwin (mainwin);
   endwin ();
   refresh ();
   return EXIT_SUCCESS;
}
