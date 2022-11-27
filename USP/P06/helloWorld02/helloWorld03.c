/*
** (c) Copyright Paul Griffiths 1999
**  Email: mail@paulgriffiths.net
**
** This program extends "Hello, world!" by outputting the phrase a
** number of times, with different foreground and background colors.
**
** We must call start_color() to initialze colour operations in ncurses,
** before using any of the colour handling library functions. After
** doing this, we can call has_color() to find out whether the terminal
** in use supports colour at all.
**
** To change color, we need to call color_set() and specify a "color
** pair" to use. A color pair simply consists of a foreground and
** background color. ncurses has a limited number of color pairs
** available (you can find out how many by checking the value of the
** constant COLOR_PAIRS).
**
** In this program, we set up a number of color pairs using the
** init_pair() function together with the predefined color constants
** ncurses supplies (e.g. COLOR_BLACK, COLOR_GREEN). Once we have
** defined these, we loop through them, using color_set() to switch
** between pairs, printing the "Hello, world!" phrase in each one as we
** go.
*/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>            
#include <curses.h>

int main (void)
{
   WINDOW *mainwin;
   int n = 1;

   /*  Initialize ncurses  */
   if ((mainwin = initscr ()) == NULL)
   {
      fprintf (stderr, "Error initialising ncurses.\n");
      exit (EXIT_FAILURE);
   }

   start_color ();              /*  Initialize colours  */

   move(6,32);

   /*  Make sure we are able to do what we want. If
    ** has_colors() returns FALSE, we cannot use colours.
    ** COLOR_PAIRS is the maximum number of colour pairs
    ** we can use. We use 13 in this program, so we check
    ** to make sure we have enough available.  */
   if (has_colors () && COLOR_PAIRS >= 13)
   {
      /*  Initialize a bunch of colour pairs, where pair is  specified:
       **   init_pair(pair number, foreground, background); */
      init_pair (1, COLOR_RED, COLOR_BLACK);
      init_pair (2, COLOR_GREEN, COLOR_BLACK);
      init_pair (3, COLOR_YELLOW, COLOR_BLACK);
      init_pair (4, COLOR_BLUE, COLOR_BLACK);
      init_pair (5, COLOR_MAGENTA, COLOR_BLACK);
      init_pair (6, COLOR_CYAN, COLOR_BLACK);
      init_pair (7, COLOR_BLUE, COLOR_WHITE);
      init_pair (8, COLOR_WHITE, COLOR_RED);
      init_pair (9, COLOR_BLACK, COLOR_GREEN);
      init_pair (10, COLOR_BLUE, COLOR_YELLOW);
      init_pair (11, COLOR_WHITE, COLOR_BLUE);
      init_pair (12, COLOR_WHITE, COLOR_MAGENTA);
      init_pair (13, COLOR_BLACK, COLOR_CYAN);

      /*  Use them to print of bunch of "Hello, world!"s */
      while (n <= 13)
      {
         color_set (n, NULL);
         mvaddstr (6 + n, 32, " Hello, world! ");
         n++;
      }
   }

   /*  Refresh the screen and sleep for a while to get the 
    **  full screen effect  */
   refresh ();
   sleep (3);

   /*  Clean up after ourselves  */
   delwin (mainwin);
   endwin ();
   refresh ();

   return EXIT_SUCCESS;
}
