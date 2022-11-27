package edu.curtin.maze.read;
import edu.curtin.maze.Maze;

/**
 * Represents a reader/parser of a particular kind of line in the maze file.
 */
public interface LineReader
{
    /** Returns the String token expected at the start of the line, identifing the line type. */
    String label();
    
    /** Read/parse the rest of the line following the label. */
    void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException;
}
