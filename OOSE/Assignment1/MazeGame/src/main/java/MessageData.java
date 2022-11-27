package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class MessageData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(MessageData.class.getName());

    public void addInfo(Maze maze, String[] data)
    {
        int i, j;

        int[] dimensions = maze.getSize();
        // Dimensions of the Maze

        if(data.length == 4)
        {
            try
            {
                i = Integer.parseInt(data[1]);
                j = Integer.parseInt(data[2]);
                /* data[1] and data[2] contain the index*/

                if(i < dimensions[0] && j < dimensions[1])
                {
                    Location location = maze.getLocation(i, j);
                    location.setMessage(data[3]);
                }
                else
                {
                    /**
                    * LOGGING: The given location is outside the Maze dimensions, hence message will be discarded
                    */
                    logger.warning("The given location is outside the Maze dimensions, hence message will be discarded");
                }
            }
            catch(NumberFormatException e)
            {
                /**
                * LOGGING: Parsing error for index, Message will be discarded
                */
                logger.severe("Parsing error for index, Message will be discarded");
            }
        }
        else
        {
            /**
             * LOGGING: Invalid argument for Message on a Location.
             *          Format "M <i> <j> MESSAGE"
             */
            logger.severe("Invalid argument for Message on a Location.");
        }
    }
}