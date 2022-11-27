package edu.curtin.maze.read;
import edu.curtin.maze.Location;
import edu.curtin.maze.Maze;

/**
 * Reads/parses an "S i j" line, specifying that (i,j) is the starting position in the maze.
 */
public class ReadStart implements LineReader
{
    @Override 
    public String label() 
    { 
        return "S"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        Location loc = new Location(vr.readRow(maze), vr.readCol(maze));
        if(maze.hasStart())
        {
            throw new MazeNonFatalException("Duplicate start locations");
        }
        maze.setStart(loc);
    }
}
