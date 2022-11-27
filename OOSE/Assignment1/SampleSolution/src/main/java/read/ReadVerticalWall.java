package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.square.Square;
import edu.curtin.maze.square.VerticalWallSquare;

/**
 * Reads/parses an "WV i j c" line, specifying a vertical wall west of (i,j).
 */
public class ReadVerticalWall implements LineReader
{
    @Override 
    public String label() 
    { 
        return "WV"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        Square sq = vr.readSquare(maze);
        if(sq.westStructure())
        {
            throw new MazeNonFatalException("Conflicting vertical wall at " + sq.getLocation());
        }    
        maze.put(new VerticalWallSquare(sq));
    }
}
