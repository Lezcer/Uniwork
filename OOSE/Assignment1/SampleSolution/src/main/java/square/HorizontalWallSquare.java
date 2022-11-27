package edu.curtin.maze.square;
import edu.curtin.maze.Player;

/**
 * Decorates a square to indicate there's a horizontal wall immediately to the north.
 */
public class HorizontalWallSquare extends SquareDecoration
{
    public HorizontalWallSquare(Square next)
    {
        super(next);
    }

    @Override
    public String getNorthString() { return "───"; }
    
    @Override
    public boolean northStructure() { return true; }

    @Override
    public boolean northMovable(Player p) { return false; }
}
