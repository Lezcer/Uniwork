package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class StartData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(StartData.class.getName());

    public void addInfo(Maze maze, String[] data)
    {
        int i, j;

        int[] dimensions = maze.getSize();
        // Dimensions of the Maze

        if(data.length == 3)
        {
            try
            {
                i = Integer.parseInt(data[1]);
                j = Integer.parseInt(data[2]);
                /* data[1] and data[2] contain the index*/

                if(i > dimensions[0] || j > dimensions[1])
                {
                    /**
                    * LOGGING: The starting location is outside the Maze dimensions, This new starting location will be discarded
                    */
                    logger.warning("The starting location is outside the Maze dimensions, This new starting location will be discarded.");
                }
                else
                {
                    maze.setStart(i, j);
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
             * LOGGING: Invalid argument for Starting location for MazeGame.
             *          Format "S <i> <j>"
             */
            logger.severe("Invalid argument for Starting location for MazeGame.");
        }
    }
}