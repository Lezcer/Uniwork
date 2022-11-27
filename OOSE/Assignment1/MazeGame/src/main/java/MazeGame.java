package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import edu.curtin.assignment.Maze;

/**
 * Contains main() for the MazeGame
 * Handles:
 * @author Salah
 */
public class MazeGame
{
    // Logging
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(MazeGame.class.getName());
     

    
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        String filename = FileIO.promptUser("Enter the name of the input file: ");
        // Gets the Input File name for the Maze Game

        Maze maze;

        try
        {
            maze = FileIO.readFile(filename);
            // File.IO.readFile() Throws an IOException

            int[] current = maze.getStart(); // Set starting position
            int[] end     = maze.getEnd();   // Set ending position
            
            // Get any keys at the starting location
            ArrayList<Integer> keys = new ArrayList<Integer>();
            Location currentLocation = maze.getLocation(current[0], current[1]);
            currentLocation.getKeys(keys);
            int totalKeys = keys.size();
            
            // Get any messages at the starting location
            String message = currentLocation.getMessage();

            // Display maze, keys acquired and messages
            maze.displayMaze(current, end);
            if(!keys.isEmpty())
                System.out.println("You have picked up keys!");
            System.out.println(message);

            int[] temp = new int[2];
            while(current[0] != end[0] || current[1] != end[1])
            {
                // Prompt user to make a move
                temp = FileIO.makeMove(current);

                // Check to see if it is valid
                if(maze.validMove(current, temp, keys))
                {
                    // If it is valid: Set current as temp, get any keys and messages acquired at new location and display maze, keys and messages
                    current = temp;
                    
                    maze.displayMaze(current, end);

                    currentLocation = maze.getLocation(current[0], current[1]);

                    message = message + currentLocation.getMessage();
                    System.out.print(message);

                    currentLocation.getKeys(keys);
                    if(totalKeys < keys.size())
                    {
                        System.out.println("You have picked up key(s)!");
                        totalKeys = keys.size();
                    }

                }
                else
                {
                    // Otherwise: Display "Invalid Move!" and re-prompt the user to make a move
                    System.out.println("Invalid move!");
                }
                
            }
            System.out.println("Game over!");
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found: " + e);
            /**
            * LOGGING: FATAL Warning. Entered File doesn't exist.
            */
            logger.severe("Entered File doesn't exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error while reading file. " + e);
            /**
            * LOGGING: FATAL Warning. Error while reading file.
            */
            logger.severe("Error while reading file.");
        }
    }    
}
