package edu.curtin.maze.move;
import edu.curtin.maze.*;

public class WestMove extends Move
{
    public WestMove()
    {
        super("west");
    }
    
    @Override
    protected boolean canMove(Maze maze, Player p)
    {
        return maze.get(p.getLocation()).westMovable(p);
    }
    
    @Override
    protected Location newLocation(Location loc)
    {
        return new Location(loc.getRow(), loc.getCol() - 1);
    }
}
