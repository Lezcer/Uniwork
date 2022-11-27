/* File:       helloWorld01.c
** Author:     Michael Borck
** Username:   michael
** Unit:       SPD251/551
** Purpose:    Simple ncurses program.  Create a window, add some text,
**             display window and finally clean up.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <curses.h>

int main ()
{
   initscr ();               /* Start curses mode              */
   addstr ("Hello World!");  /* Print Hello World! to buffer   */
   refresh ();               /* Print buffer to screen         */
   sleep(3);                 /* Wait for 3 seconds             */
   endwin ();                /* End curses mode                */
   return 0;
}
