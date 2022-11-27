package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class WallData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(WallData.class.getName());

    public void addInfo(Maze maze, String[] data)
    {
        int i, j, placement;
        Location barrier;

        int[] dimensions = maze.getSize();
        // Dimensions of the Maze

        if(data.length == 3)
        {
            try
            {
                i = Integer.parseInt(data[1]);
                j = Integer.parseInt(data[2]);
                String command = data[0]; 
                /* data[1] and data[2] contain the index*/
                /* data[0] has to be either "WV" or "WH"*/

                if(i > dimensions[0] || j > dimensions[1])
                {
                    /**
                    * LOGGING: The given location is outside the Maze dimensions, This new wall will be discarded
                    */
                    logger.warning("The given location is outside the Maze dimensions, This new wall will be discarded");
                }
                else
                {
                    switch(command.charAt(1))
                    {
                        case 'V':
                            placement = 0;
                            barrier = maze.getLocation(i, j);
                            barrier.setWall(placement);
                            /* If placement is 0 then it is vertical*/
                            break;
                        case 'H':
                            placement = 1;
                            barrier = maze.getLocation(i, j);
                            barrier.setWall(placement);
                            /* Otherwise (in this case placement will be 1, then the wall is horizontal)*/
                        break;
                    }
                }
            }
            catch(NumberFormatException e)
            {
                /**
                * LOGGING: Parsing error.
                */
                logger.severe("Parsing error.");
            }
        }
        else
        {
            /**
             * LOGGING: Invalid argument for wall location.
             *          Format "<WV or WH> <i> <j>"
             */
            logger.severe("Invalid argument for wall location.");
        }
    }
}