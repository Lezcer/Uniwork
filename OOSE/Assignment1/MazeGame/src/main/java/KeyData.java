package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.StoreData;

/**
 * Contains Concrete Strategy Class implementing StoreData
 * @author Salah
 */
public class KeyData implements StoreData
{
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(KeyData.class.getName());

    public void addInfo(Maze maze, String[] data)
    {
        int i, j, key;

        int[] dimensions = maze.getSize();
        // Dimensions of the Maze

        if(data.length == 4)
        {
            try
            {
                i = Integer.parseInt(data[1]);
                j = Integer.parseInt(data[2]);
                key = Integer.parseInt(data[3]);
                /* data[1] and data[2] contain the index*/
                /* data[3] contains the key type*/

                if(key < 1 || key > 6)
                {
                    /**
                    * LOGGING: The given key is not within bounds of 1 and 6, hence it will be discarded
                    */
                    logger.warning("The given key is not within bounds of 1 and 6, hence it will be discarded");
                }
                else if(i < dimensions[0] && j < dimensions[1])
                {
                    Location location = maze.getLocation(i, j);
                    location.setKey(key);
                }
                else
                {
                    /**
                    * LOGGING: The given location is outside the Maze dimensions, hence key will be discarded
                    */
                    logger.warning("The given location is outside the Maze dimensions, hence key will be discarded");
                }
            }
            catch(NumberFormatException e)
            {
                /**
                * LOGGING: Parsing error for index, Key will be discarded
                */
                logger.severe("Parsing error for index, Key will be discarded");
            }
        }
        else
        {
            /**
             * LOGGING: Invalid argument for Key on a Location.
             *          Format "K <i> <j> <Key type <1-6>"
             */
            logger.severe("Invalid argument for Key on a Location.");
        }
    }
}