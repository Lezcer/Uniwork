package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.Key;
import edu.curtin.maze.square.Square;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Wrapper around Scanner to perform extra validation and Maze-specific object retrieval 
 * (Squares and Keys).
 */
public class ValueReader
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(ValueReader.class.getName());
    private Scanner sc;
    
    public ValueReader(String s)
    {
        sc = new Scanner(s);
    }
        
    /**
     * Reads an integer that must be between a given minimum and maximum (inclusive). Throws 
     * ValueOutOfBoundsException if it finds a value outside the given bounds.
     */     
    public int readIntBetween(int min, int max) throws MazeReadException, ValueOutOfBoundsException
    {
        try
        {
            int n = sc.nextInt();
            logger.finer(() -> "Reading value between " + min + "-" + max + "; got " + n);
            
            if(n < min) { throw new ValueOutOfBoundsException("Expected integer >=" + min + ", got " + n); }
            if(n > max) { throw new ValueOutOfBoundsException("Expected integer <=" + max + ", got " + n); }
            return n;
        }
        catch(InputMismatchException e)
        {
            throw new MazeReadException("Expected integer, got '" + sc.next() + "'", e);
        }
        catch(NoSuchElementException e)
        {
            throw new MazeReadException("Expected integer, got end-of-line", e);
        }
    }
    
    /**
     * Reads an integer that must be at least a certain minimum value, and throws 
     * ValueOutOfBoundsException for values less than the minimum.
     */     
    public int readIntAtLeast(int min) throws MazeReadException, ValueOutOfBoundsException
    {
        return readIntBetween(min, Integer.MAX_VALUE);
    }
    
    /**
     * Reads a row number, an integer expected to be within the bounds of the maze rows.
     */
    public int readRow(Maze maze) throws MazeReadException, ValueOutOfBoundsException
    {
        return readIntBetween(0, maze.getNRows() - 1);
    }
    
    /**
     * Reads a column number, an integer expected to be within the bounds of the maze columns.
     */
    public int readCol(Maze maze) throws MazeReadException, ValueOutOfBoundsException
    {
        return readIntBetween(0, maze.getNCols() - 1);
    }
    
    /** 
     * Reads two integers representing a grid square location, and returns the Square object at
     * that location.
     */
    public Square readSquare(Maze maze) throws MazeReadException, ValueOutOfBoundsException
    {
        logger.finer("Retrieving Square object");
        return maze.get(readRow(maze), readCol(maze));
    }
    
    /**
     * Reads an integer representing a key colour, and returns a corresponding Key object.
     */
    public Key readKey() throws MazeReadException, ValueOutOfBoundsException
    {
        logger.finer("Reading Key object");
        return new Key(readIntBetween(1, 6));
    }
    
    /**
     * Returns the next 'word' (separated by whitepace).
     */
    public String readWord() throws MazeReadException
    {
        try
        {
            return sc.next();
        }
        catch(NoSuchElementException e)
        {
            throw new MazeReadException("Expected string, got end-of-line", e);
        }
    }
    
    /**
     * Returns the rest of the current line of text.
     */
    public String readLine() throws MazeReadException
    {
        try
        {
            return sc.nextLine();
        }
        catch(NoSuchElementException e)
        {
            throw new MazeReadException("Expected string, got end-of-line", e);
        }
    }
    
    /** 
     * Ensures that nothing is left on the current line. This should be called after all expected 
     * values have been read and handled. If there's anything left, this method will throw an 
     * exception.
     */
    public void verifyEmpty() throws MazeReadException
    {
        if(sc.hasNext())
        {
            throw new MazeReadException("Unexpected value: '" + sc.next() + "'");
        }
    }
    
    /** 
     * Advances to the next non-empty line.
     */
    public boolean nextLine(BufferedReader reader) throws MazeReadException
    {
        logger.finer("Next line");
        try
        {
            String line = reader.readLine();
            while(line != null)
            {        
                sc = new Scanner(line);
                if(sc.hasNext())
                {
                    // Use the line only if non-empty.
                    return true;
                }
                line = reader.readLine();
            }
            // Reached the end of the file.
            return false;
        }
        catch(IOException e)
        {
            throw new MazeReadException("IO error", e);
        }
    }
}
