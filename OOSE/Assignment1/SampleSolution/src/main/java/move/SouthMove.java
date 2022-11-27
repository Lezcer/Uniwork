package edu.curtin.maze.move;
import edu.curtin.maze.*;

public class SouthMove extends Move
{
    public SouthMove()
    {
        super("south");
    }
    
    @Override
    protected boolean canMove(Maze maze, Player p)
    {
        Location loc = p.getLocation();
        return maze.get(loc.getRow() + 1, loc.getCol()).northMovable(p);
    }
    
    @Override
    protected Location newLocation(Location loc)
    {
        return new Location(loc.getRow() + 1, loc.getCol());
    }
}
