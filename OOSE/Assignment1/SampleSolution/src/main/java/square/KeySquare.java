package edu.curtin.maze.square;
import edu.curtin.maze.Player;
import edu.curtin.maze.Key;
import java.util.List;

/**
 * Decorates a square to add a key, to be picked up when the player reaches the square.
 */
public class KeySquare extends SquareDecoration
{
    private Key key;

    public KeySquare(Square next, Key key)
    {
        super(next);
        this.key = key;
    }

    @Override
    public void moveTo(Player p, List<String> messages)
    {
        next.moveTo(p, messages);
        if(key != null)
        {
            p.putKey(key);
            messages.add("Picked up key: " + key);
            key = null;
        }
    }
    
    @Override
    public void addCellElements(List<String> elements)
    {
        if(key != null)
        {
            elements.add(key.toString());
        }
        next.addCellElements(elements);
    }
}
