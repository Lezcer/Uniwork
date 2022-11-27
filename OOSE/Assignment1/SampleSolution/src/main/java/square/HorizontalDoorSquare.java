package edu.curtin.maze.square;
import edu.curtin.maze.Player;
import edu.curtin.maze.Key;

/**
 * Decorates a square to indicate there's a horizontal door immediately to the north.
 */
public class HorizontalDoorSquare extends SquareDecoration
{
    private Key keyRequired;

    public HorizontalDoorSquare(Square next, Key keyRequired)
    {
        super(next);
        this.keyRequired = keyRequired;
    }

    @Override
    public String getNorthString()
    {
        return keyRequired.colourString("▒▒▒");
    }

    @Override
    public boolean northStructure() { return true; }
    
    @Override
    public boolean northMovable(Player p) 
    { 
        return p.hasKey(keyRequired); 
    }
}
