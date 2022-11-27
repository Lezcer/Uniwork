/* File:       helloWorld05.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    This program extends "Hello, world!" and demonstrates simple
**             animation by showing how to erase draw.
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <curses.h>

int main (void)
{
   WINDOW *mainwin;
   int i;

   /*  Initialize ncurses  */
   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }

   for (i = 0; i < LINES; i++)
   {
      move (i, i + i);
      addstr ("Hello, world");
      refresh ();
      sleep (1);
      move (i, i + i);          /* move back    */
      addstr ("             "); /* erase line   */
      sleep (1);
      refresh ();
   }

   /*  Clean up after ourselves  */
   delwin (mainwin);
   endwin ();
   refresh ();
   return EXIT_SUCCESS;
}
