package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.square.Square;
import edu.curtin.maze.square.HorizontalWallSquare;

/**
 * Reads/parses an "WH i j c" line, specifying a horizontal wall north of (i,j).
 */
public class ReadHorizontalWall implements LineReader
{
    @Override 
    public String label() 
    { 
        return "WH"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        Square sq = vr.readSquare(maze);
        if(sq.northStructure())
        {
            throw new MazeNonFatalException("Conflicting horizontal door at " + sq.getLocation());
        }    
        maze.put(new HorizontalWallSquare(sq));
    }
}
