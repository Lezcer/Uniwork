package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class DoorData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(DoorData.class.getName());

    public void addInfo(Maze maze, String[] data)
    {
        int i, j, door, placement;
        Location barrier;

        int[] dimensions = maze.getSize();
        // Dimensions of the Maze

        if(data.length == 4)
        {
            try
            {
                i = Integer.parseInt(data[1]);
                j = Integer.parseInt(data[2]);
                door = Integer.parseInt(data[3]);
                String command = data[0]; 
                /* data[1] and data[2] contain the index*/
                /* data[3] contains the door type*/
                /* data[0] has to be either "WV" or "WH"*/

                if(door < 1 || door > 6)
                {
                    /**
                    * LOGGING: The given door is not within bounds of 1 and 6, hence it will be discarded
                    */
                    logger.warning("The given door is not within bounds of 1 and 6, hence it will be discarded");
                }
                else if(i > dimensions[0] || j > dimensions[1])
                {
                    /**
                    * LOGGING: The given location is outside the Maze dimensions, This new door will be discarded
                    */
                    logger.warning("The given location is outside the Maze dimensions, This new door will be discarded");
                }
                else
                {
                    switch(command.charAt(1))
                    {
                        case 'V':
                            placement = 0;
                            barrier = maze.getLocation(i, j);
                            barrier.setDoor(placement, door);
                            /* If placement is 0 then it is vertical*/
                            break;
                        case 'H':
                            placement = 1;
                            barrier = maze.getLocation(i, j);
                            barrier.setDoor(placement, door);
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
                //logger.severe("Parsing error.");
            }
        }
        else
        {
            /**
             * LOGGING: Invalid argument for door location.
             *          Format "<DV or DH> <i> <j>"
             */
            logger.severe("Invalid argument for door location.");
        }
    }
}