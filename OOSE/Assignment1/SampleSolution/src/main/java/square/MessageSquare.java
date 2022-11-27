package edu.curtin.maze.square;
import edu.curtin.maze.Player;
import java.util.List;

/**
 * Decorates a square to add a text message, to be displayed when the player reaches the square.
 */
public class MessageSquare extends SquareDecoration
{
    private String msg;

    public MessageSquare(Square next, String msg)
    {
        super(next);
        this.msg = msg;
    }

    @Override
    public void moveTo(Player p, List<String> messages)
    {
        next.moveTo(p, messages);
        messages.add("Message: '" + msg + "'");
    }    
}
