package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.square.KeySquare;

/**
 * Reads/parses an "K i j c" line, specifying that (i,j) contains a key of colour c.
 */
public class ReadKey implements LineReader
{
    @Override 
    public String label() 
    { 
        return "K"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        maze.put(new KeySquare(vr.readSquare(maze), vr.readKey()));
    }
}
