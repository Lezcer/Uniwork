package edu.curtin.maze.square;
import edu.curtin.maze.Location;
import edu.curtin.maze.Player;
import java.util.List;

/**
 * Represents a basic empty grid square (the base implementation in our application of the
 * decorator pattern).
 */
public class BaseSquare implements Square
{
    private Location loc;
    
    public BaseSquare(Location loc)
    {
        this.loc = loc;
    }
    
    @Override
    public Location getLocation() { return loc; }
    
    @Override
    public void addCellElements(List<String> elements) {}
    
    @Override
    public String getNorthString() { return "   "; }
    
    @Override
    public String getWestString() { return " "; }
    
    @Override 
    public boolean westStructure() { return false; }
    
    @Override
    public boolean northStructure() { return false; }
    
    @Override
    public boolean westMovable(Player p) { return true; }
    
    @Override
    public boolean northMovable(Player p) { return true; }
    
    @Override
    public void moveTo(Player p, List<String> messages) {} // Do nothing
}
