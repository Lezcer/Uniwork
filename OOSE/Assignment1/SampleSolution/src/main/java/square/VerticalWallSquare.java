package edu.curtin.maze.square;
import edu.curtin.maze.Player;

/**
 * Decorates a square to indicate there's a vertical wall immediately to the west.
 */
public class VerticalWallSquare extends SquareDecoration
{
    public VerticalWallSquare(Square next)
    {
        super(next);
    }

    @Override
    public String getWestString() { return "│"; }
    
    @Override
    public boolean westStructure() { return true; }

    @Override
    public boolean westMovable(Player p) { return false; }
}
