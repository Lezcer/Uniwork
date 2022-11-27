package edu.curtin.maze.read;

/**
 * Represents warning conditions that should NOT (necessarily) result in the maze game aborting.
 */
public class MazeNonFatalException extends Exception
{
    public MazeNonFatalException(String msg) 
    { 
        super(msg); 
    }
    
    public MazeNonFatalException(String msg, Throwable cause) 
    { 
        super(msg, cause); 
    }
}
