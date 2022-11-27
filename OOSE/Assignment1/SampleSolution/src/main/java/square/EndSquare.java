package edu.curtin.maze.square;
import edu.curtin.maze.Player;
import java.util.List;

/**
 * Decorates a square to indicate that it's an "end" position for the maze.
 */
public class EndSquare extends SquareDecoration
{
    public EndSquare(Square next)
    {
        super(next);
    }

    @Override
    public void moveTo(Player p, List<String> messages)
    {
        next.moveTo(p, messages);
        p.win();
        messages.add("You won!");
    }
    
    @Override
    public void addCellElements(List<String> elements)
    {
        elements.add("E");
        next.addCellElements(elements);
    }
}
