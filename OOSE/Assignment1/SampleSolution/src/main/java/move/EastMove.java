package edu.curtin.maze.move;
import edu.curtin.maze.*;

public class EastMove extends Move
{
    public EastMove()
    {
        super("east");
    }
    
    @Override
    protected boolean canMove(Maze maze, Player p)
    {
        Location loc = p.getLocation();
        return maze.get(loc.getRow(), loc.getCol() + 1).westMovable(p);
    }
    
    @Override
    protected Location newLocation(Location loc)
    {
        return new Location(loc.getRow(), loc.getCol() + 1);
    }
}
