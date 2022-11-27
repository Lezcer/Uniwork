package edu.curtin.maze.read;
import edu.curtin.maze.Maze;
import edu.curtin.maze.square.MessageSquare;

/**
 * Reads/parses an "M msg..." line, specifying that (i,j) contains the given message.
 */
public class ReadMessage implements LineReader
{
    @Override 
    public String label() 
    { 
        return "M"; 
    }
    
    @Override
    public void read(ValueReader vr, Maze maze) throws MazeReadException, ValueOutOfBoundsException
    {
        maze.put(new MessageSquare(vr.readSquare(maze), vr.readLine().trim()));
    }
}
