package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Contains Maze object
 * @author Salah
 */
public class Maze 
{
    protected Location[][] locations;
    protected Location start;
    protected Location end;
    //protected HashMap<Integer, Location> end = new HashMap<Integer, Location>();
    // The key for this hashmap will be an integer, which is the coordinated of the Location in 1D array form instead of 2D


    private static final String HORIZONTALWALL = "\u2500\u2500\u2500";
    private static final String VERTICALWALL   = "\u2502";
    private static final String HORIZONTALDOOR = "\u2592\u2592\u2592";
    private static final String VERTICALDOOR   = "\u2592";

    private static final String TOPLEFTCORNER     = "\u250c";
    private static final String TOPRIGHTCORNER    = "\u2510";
    private static final String BOTTOMLEFTCORNER  = "\u2514";
    private static final String BOTTOMRIGHTCORNER = "\u2518";
    
    private static final String T           = "\u252c";
    private static final String UPSIDEDOWNT = "\u2534";
    private static final String RIGHTT      = "\u251c";
    private static final String LEFTT       = "\u2524";
    private static final String PLUST       = "\u253c";
    private static final String NOT         = "\u2500";
    private static final String EMPTY       = " ";

    private static final String KEY    = " \u2555 ";
    private static final String PLAYER = " P ";
    private static final String SPACE  = "   ";
    private static final String END    = " E ";
    // BOX DRAWING CHARACTERS
    
    private static final String RED     = "\033[31m"; // COLOUR 1 
    private static final String GREEN   = "\033[32m"; // COLOUR 2
    private static final String YELLOW  = "\033[33m"; // COLOUR 3
    private static final String BLUE    = "\033[34m"; // COLOUR 4
    private static final String MAGNETA = "\033[35m"; // COLOUR 5
    private static final String CYAN    = "\033[36m"; // COLOUR 6
    private static final String RESET   = "\033[m";   // Reset to default
    protected static HashMap<Integer, String> colours = new HashMap<>(); // Strategy Pattern?
    // COLOURS

    private static final String CLEAR = "\033[2J";
    private static final String REPOSITION = "\033[H";
    // CLEAR SCREAN

    // Logging
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(Maze.class.getName());

    /*Constructor*/
    public Maze(int m, int n)
    {
        locations = new Location[m][n];
        for(int ii = 0; ii < m; ii++)
        {
            for(int jj = 0; jj < n; jj++)
            {
                locations[ii][jj] = new Location(ii, jj);
            }
        }
        /* Creates a 2D array of size m * n, calls the constructor of each location storing their individual coordinates*/

        start = locations[0][0];
        // Set default start location (0,0)

        end = locations[m-1][n-1];
        // Set default end location (m-1, n-1)
    }
    public Maze()
    {
        // Default constructor
    }

    /*Accessors*/
    public Location getLocation(int m, int n)
    {
        return locations[m][n];
    }// Return a Location object using the index
    public int[] getSize()
    {
        int[] size = new int[2];
        size[0] = locations.length;
        size[1] = locations[0].length;
        return size;
    }// Return the dimensions of the maze
    public int[] getStart()
    {
        return start.getCoordinates();
    }// Return the coordinates of the maze's starting location
    public int[] getEnd()
    {
        return end.getCoordinates();
    }// Return the coordinates of the maze's ending location 

    /*Mutators*/
    public void setStart(int m, int n)
    {
        start = locations[m][n];
    }
    public void setEnd(int m, int n)
    {
        end = locations[m][n];
    }

    private static void initColors()
    {
        colours.put(1, RED);
        colours.put(2, GREEN);
        colours.put(3, YELLOW);
        colours.put(4, BLUE);
        colours.put(5, MAGNETA);
        colours.put(6, CYAN);
    }
    public void displayMaze(int[] current, int[] end)
    {
        // Note: m,m is the coordinates of the player's current position
        initColors(); // Maps ints to colours
        String line = "";
        
        System.out.print(CLEAR + REPOSITION); // Clear the screen and repositions cursor

        for(int i = 0; i < locations.length; i++)
        {
            // ITERATES THROUGH ROWS
            line = "";

            if(i == 0) // Displays the Top edge of the maze
            {
                for(int jj = 0; jj < locations[0].length; jj++)
                {
                    // ITERATES THROUGH THE LOCATIONS IN THE FIRST ROW TO SEE WHETHER THEY HAVE AND WALLS TO THE WEST
                    line = line + HORIZONTALWALL;
                    if(jj+1 != locations[0].length) // i.e. next element exists
                    {
                        if(locations[0][jj+1].verticalBarrier() != 0) // DOES 0, jj+1 have a vertical barrier?
                        {
                            line = line + T;
                        }
                        else
                        {
                            line = line + NOT;
                        }
                    }
                }
                line = TOPLEFTCORNER + line + TOPRIGHTCORNER + "\n";
                System.out.print(line);
                line = "";
            }


            for(int j = 0; j < locations[i].length; j++) // Displays the locations and not horizontal walls / doors in between rows
            {
                // ITERATES THROUGH EVERY LOCATION
                if(i == current[0] && j == current[1]) // i.e. if current i and j are coordinates of player location
                {
                    line = line + PLAYER;
                }
                else if(locations[i][j].hasKey())
                {
                    line = line + colours.get(locations[i][j].getKeyColour()) + KEY + RESET;
                }
                else if(i == end[0] && j == end[1])
                {
                    line = line + END;
                }
                else
                {
                    line = line + SPACE;
                }

                if(j+1 != locations[i].length) // i.e. next element exists
                {
                    if(locations[i][j+1].verticalBarrier() == -1) // DOES i, j+1 have a wall west?
                    {
                        line = line + VERTICALWALL;
                    }
                    else if(locations[i][j+1].verticalBarrier() > 0) // DOES i, j+1 have a door(s) west?
                    {
                        line = line + colours.get(locations[i][j+1].getVerticalDoorColour()) + VERTICALDOOR + RESET;
                    }
                    else // there's no barrier
                    {
                        line = line + EMPTY;
                    }
                }
            }
            line = VERTICALWALL + line + VERTICALWALL + "\n";
            System.out.print(line);
            line = "";

            // Once every location in a specific row has been, the horizontal walls are displayed here
            // i.e. every 2nd row.
            if(i+1 < locations.length) // i.e. next row exists
            {
                for(int jjj = 0; jjj < locations[0].length; jjj++) // Handles what's between rows but not columns
                {
                    // ITERATES THROUGH THE LOCATIONS TO DISPLAY BOX DRAWING CHARACTERS BETWEEN ROWS

                    if(jjj == 0 && locations[i+1][jjj].horizontalBarrier() != 0)
                    {
                        line = RIGHTT;
                    }
                    else if(jjj == 0 && locations[i+1][jjj].horizontalBarrier() == 0)
                    {
                        line = VERTICALWALL;
                    }

                    if(locations[i+1][jjj].horizontalBarrier() == -1) // DOES i+1, jjj have a wall north?
                    {

                        line = line + HORIZONTALWALL;
                    }
                    else if(locations[i+1][jjj].horizontalBarrier() > 0) // DOES i+1, jjj have a door(s) north?
                    {
                        line = line + colours.get(locations[i+1][jjj].getHorizontalDoorColour()) + HORIZONTALDOOR + RESET;
                    }
                    else // there's no barrier
                    {
                        line = line + SPACE;
                    }

                    if(jjj+1 != locations[i+1].length) // i.e. next element exists. this handles what's between columns
                    {
                        if(i == 2 && jjj == 2)
                        {
                            //System.out.println(locations[2][3].verticalBarrier() +" "+ locations[3][2].horizontalBarrier() +" "+ locations[3][3].verticalBarrier() +" "+ locations[3][3].horizontalBarrier());
                        }
                        if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj+1].horizontalBarrier() == 0)
                        {
                            line = line + BOTTOMRIGHTCORNER;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + UPSIDEDOWNT;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() == 0 && locations[i+1][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + BOTTOMLEFTCORNER;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj].horizontalBarrier() == 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + TOPLEFTCORNER;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + T;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() == 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() == 0)
                        {
                            line = line + TOPRIGHTCORNER;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() == 0)
                        {
                            line = line + LEFTT;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() == 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + RIGHTT;
                        }
                        else if(locations[i][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj].horizontalBarrier() != 0 && locations[i+1][jjj+1].verticalBarrier() != 0 && locations[i+1][jjj+1].horizontalBarrier() != 0)
                        {
                            line = line + PLUST;
                        }
                        else if(locations[i+1][jjj].horizontalBarrier() == 0 && locations[i+1][jjj+1].horizontalBarrier() == 0 && (locations[i][jjj+1].verticalBarrier() != 0 || locations[i+1][jjj+1].verticalBarrier() != 0))
                        {
                            line = line +  VERTICALWALL;
                        }
                        else if(locations[i+1][jjj+1].verticalBarrier() == 0 && locations[i][jjj+1].verticalBarrier() == 0 && (locations[i+1][jjj+1].horizontalBarrier() != 0 || locations[i+1][jjj].horizontalBarrier() != 0))
                        {
                            line = line + NOT;
                        }
                        else
                        {
                            line = line + EMPTY;
                        }
                    }
                    else if(locations[i+1][jjj].horizontalBarrier() != 0)
                    {
                        line = line + LEFTT;
                    }
                    else if(locations[i+1][jjj].horizontalBarrier() == 0)
                    {
                        line = line + VERTICALWALL;
                    }
                    
                }
                System.out.println(line);
                line = "";
            }
            

            if(i == locations.length - 1) // Displays the Bottom edge of the maze
            {
                for(int jj = 0; jj < locations[0].length; jj++)
                {
                    // ITERATES THROUGH THE LOCATIONS IN THE LAST ROW TO SEE WHETHER THEY HAVE AND WALLS TO THE WEST
                    line = line + HORIZONTALWALL;
                    if(jj+1 != locations[0].length) // i.e. next element exists
                    {
                        if(locations[locations.length - 1][jj+1].verticalBarrier() != 0) // DOES 0, jj+1 have a vertical barrier?
                        {
                            line = line + UPSIDEDOWNT;
                        }
                        else
                        {
                            line = line + NOT;
                        }
                    }
                }
                line = BOTTOMLEFTCORNER + line + BOTTOMRIGHTCORNER + "\n";
                System.out.print(line);
                line = "";
            }
        }

    }
    //Displays Maze

    public boolean validMove(int[] previous, int[] updated, ArrayList<Integer> keys)
    {
        boolean isValid = false;
        Location locat;

        if(updated[0] < locations.length && updated[1] < locations[0].length && updated[0] >= 0 && updated[1] >= 0)
        {
            if(previous[1] == updated[1]) // m = m, In this case it's either North or South
            {
                if(previous[0] == (updated[0]-1)) // i.e. South
                {
                    locat = getLocation(updated[0], updated[1]);
                }
                else // Otherwise North
                {
                    locat = getLocation(previous[0], previous[1]);
                }

                if(locat.horizontalBarrier() == -1) // i.e. wall
                {
                    isValid = false;
                    /**
                    * LOGGING: Invalid because there's a wall South or North
                    */
                    logger.info("Invalid because there's a wall South or North.");
                }
                else if(locat.horizontalBarrier() == 0) // i.e. no barrier
                {
                    isValid = true;
                }
                else if(locat.horizontalBarrier() > 0 && keys.size() > 0) // i.e. door(s)
                {
                    if(keys.containsAll(locat.getHorizontalDoors()))
                    {
                        isValid = true;
                    }
                    else
                    {
                        isValid = false;
                        /**
                        * LOGGING: Invalid because there's a door South or North and you don't have the keys
                        */
                        logger.info("Invalid because there's a door South or North and you don't have the keys.");
                    }
                }
            }
            else // Otherwise: n = n, it is West or East
            {
                if(previous[1] == (updated[1]-1)) // i.e. East
                {
                    locat = getLocation(updated[0], updated[1]);
                }
                else // Otherwise West
                {
                    locat = getLocation(previous[0], previous[1]);
                }

                if(locat.verticalBarrier() == -1) // i.e. wall
                {
                    isValid = false;
                    /**
                    * LOGGING: Invalid because there's a wall East or West
                    */
                    logger.info("Invalid because there's a wall East or West.");
                }
                else if(locat.verticalBarrier() == 0) // i.e. no barrier
                {
                    isValid = true;
                }
                else if(locat.verticalBarrier() > 0 && keys.size() > 0) // i.e. door(s)
                {
                    if(keys.containsAll(locat.getVerticalDoors()))
                    {
                        isValid = true;
                    }
                    else
                    {
                        isValid = false;
                        /**
                        * LOGGING: Invalid because there's a door South or North and you don't have the keys
                        */
                        logger.info("Invalid because there's a door South or North and you don't have the keys.");
                    }
                }
            }
        }
        else
        {
            isValid = false;
            /**
            * LOGGING: Invalid because, move out of maze bounds.
            */
            logger.info("Invalid because, move out of maze bounds.");
        }

        return isValid;
    } // checks whether the move is valid or not
}

