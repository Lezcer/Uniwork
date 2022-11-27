package edu.curtin.maze.square;
import edu.curtin.maze.Location;
import edu.curtin.maze.Player;
import java.util.List;

/** 
 * Base square decoration; simply forwards each call to the next element.
 */
public abstract class SquareDecoration implements Square
{
    protected Square next;
    
    public SquareDecoration(Square next)
    {
        this.next = next;
    }

    @Override
    public Location getLocation() { return next.getLocation(); }
    
    @Override
    public void addCellElements(List<String> elements) { next.addCellElements(elements); }
    
    @Override
    public String getWestString() { return next.getWestString(); }
    
    @Override
    public String getNorthString() { return next.getNorthString(); }
    
    @Override 
    public boolean westStructure() { return next.westStructure(); }
    
    @Override
    public boolean northStructure() { return next.northStructure(); }
    
    @Override
    public boolean westMovable(Player p) { return next.westMovable(p); }
    
    @Override
    public boolean northMovable(Player p) { return next.northMovable(p); }
    
    @Override
    public void moveTo(Player p, List<String> messages) { next.moveTo(p, messages); }
    
}
