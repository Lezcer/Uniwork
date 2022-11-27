package edu.curtin.maze.read;

/**
 * Represents fatal error conditions -- IO or syntax errors -- where the maze file cannot 
 * successfully be read.
 */
public class MazeReadException extends Exception
{
    public MazeReadException(String msg) 
    { 
        super(msg); 
    }
    
    public MazeReadException(String msg, Throwable cause) 
    { 
        super(msg, cause); 
    }
}
