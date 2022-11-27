package edu.curtin.maze.move;
import edu.curtin.maze.*;

public class NorthMove extends Move
{
    public NorthMove()
    {
        super("north");
    }
    
    @Override
    protected boolean canMove(Maze maze, Player p)
    {
        return maze.get(p.getLocation()).northMovable(p);
    }
    
    @Override
    protected Location newLocation(Location loc)
    {
        return new Location(loc.getRow() - 1, loc.getCol());
    }
}
