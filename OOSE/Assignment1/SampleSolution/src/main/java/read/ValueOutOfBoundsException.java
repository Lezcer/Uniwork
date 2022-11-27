package edu.curtin.maze.read;

/**
 * Represents a non-fatal warning/error where a value (a row, column or colour) was found to be 
 * outside its required minimum/maximum.
 */
public class ValueOutOfBoundsException extends MazeNonFatalException
{
    public ValueOutOfBoundsException(String msg) 
    { 
        super(msg); 
    }
    
    public ValueOutOfBoundsException(String msg, Throwable cause) 
    { 
        super(msg, cause); 
    }
}
