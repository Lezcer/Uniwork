package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.Key;
import edu.curtin.maze.square.Square;
import edu.curtin.maze.square.VerticalDoorSquare;

/**
 * Reads/parses an "DV i j c" line, specifying a vertical door west of (i,j), of colour c.
 */
public class ReadVerticalDoor implements LineReader
{
    @Override 
    public String label() 
    { 
        return "DV"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        Square sq = vr.readSquare(maze);
        Key key = vr.readKey();
        if(sq.westStructure())
        {
            throw new MazeNonFatalException("Conflicting vertical door at " + sq.getLocation());
        }    
        maze.put(new VerticalDoorSquare(sq, key));
    }
}
