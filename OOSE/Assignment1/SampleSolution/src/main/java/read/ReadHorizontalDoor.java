package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.Key;
import edu.curtin.maze.square.Square;
import edu.curtin.maze.square.HorizontalDoorSquare;

/**
 * Reads/parses an "DH i j c" line, specifying a horizontal door north of (i,j), of colour c.
 */
public class ReadHorizontalDoor implements LineReader
{
    @Override 
    public String label() 
    { 
        return "DH"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, MazeNonFatalException
    {
        Square sq = vr.readSquare(maze);
        Key key = vr.readKey();
        if(sq.northStructure())
        {
            throw new MazeNonFatalException("Conflicting horizontal door at " + sq.getLocation());
        }    
        maze.put(new HorizontalDoorSquare(sq, key));
    }
}
