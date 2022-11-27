package edu.curtin.maze.move;
import edu.curtin.maze.*;
import java.util.List;

public abstract class Move
{
    private String direction;
    
    public Move(String direction)
    {
        this.direction = direction;
    }
    
    public char getInputChar()      
    { 
        return direction.charAt(0); 
    }
    
    public String getPrompt() 
    { 
        return "(" + direction.charAt(0) + ")" + direction.substring(1); 
    }
    
    protected abstract boolean canMove(Maze maze, Player p);
    protected abstract Location newLocation(Location loc);
    
    public void perform(Player player, Maze maze, List<String> messages)
    {
        if(canMove(maze, player))
        {
            player.setLocation(newLocation(player.getLocation()));
            messages.add("Moved " + direction);
        }
        else
        {
            messages.add("Blocked from moving " + direction);
        }
    }
}
