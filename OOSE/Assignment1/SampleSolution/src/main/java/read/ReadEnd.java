package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.square.EndSquare;

/**
 * Reads and parses an "E i j" line, specifying that (i,j) is an "end" square.
 */
public class ReadEnd implements LineReader
{
    @Override 
    public String label() 
    { 
        return "E"; 
    }

    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, ValueOutOfBoundsException
    {
        maze.put(new EndSquare(vr.readSquare(maze)));
        maze.setEndFlag();
    }
}
