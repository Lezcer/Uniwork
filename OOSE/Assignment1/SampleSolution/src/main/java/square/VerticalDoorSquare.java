package edu.curtin.maze.square;
import edu.curtin.maze.Player;
import edu.curtin.maze.Key;

/**
 * Decorates a square to indicate there's a vertical door immediately to the west.
 */
public class VerticalDoorSquare extends SquareDecoration
{
    private Key keyRequired;

    public VerticalDoorSquare(Square next, Key keyRequired)
    {
        super(next);
        this.keyRequired = keyRequired;
    }

    @Override
    public String getWestString()
    {
        return keyRequired.colourString("▒");
    }
    
    @Override
    public boolean westStructure() { return true; }
    
    @Override
    public boolean westMovable(Player p)
    {
        return p.hasKey(keyRequired);
    }   
}
