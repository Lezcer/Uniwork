package edu.curtin.assignment;

import java.io.*;
import java.util.*;

/**
 * This interface is used to implement the Strategy Pattern when storing data about the maze game into the Maze and Location objects.
 * Data such as: The Maze's start location and end location(s), Barriers a location might have to it's north or west, any keys that it
 * could contain or messages. 
 * @author Salah
 */
public interface StoreData 
{
    public void addInfo(Maze maze, String[] data);
    /*The Method that will be implemented accross classes that inherit from StoreData*/
}
