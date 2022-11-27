package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class EndData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(EndData.class.getName());

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
                    * LOGGING: The ending location is outside the Maze dimensions, This new ending location will be discarded
                    */
                    logger.warning("The ending location is outside the Maze dimensions, This new ending location will be discarded");
                }
                else
                {
                    maze.setEnd(i,j);
                }
            }
            catch(NumberFormatException e)
            {
                /**
                * LOGGING: Parsing error.
                */
                logger.severe("The ending location is outside the Maze dimensions, This new ending location will be discarded");
            }
        }
        else
        {
            /**
             * LOGGING: Invalid argument for Ending location for MazeGame.
             *          Format "E <i> <j>"
             */
            logger.severe("Invalid argument for Ending location for MazeGame.");
        }
    }
}