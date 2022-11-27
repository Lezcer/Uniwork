package edu.curtin.maze.square;
import edu.curtin.maze.Location;
import edu.curtin.maze.Player;
import java.util.List;

/**
 * Represents decoratable grid square.
 */
public interface Square
{
    /** The square's row/column. */
    Location getLocation();
    
    /** 
     * For display purposes: adds strings representing 'elements' to be displayed within the grid 
     * square. Each element is expected to occupy one character on-screen, but may include ANSI 
     * escape codes to affect the colour (hence making it a String, not a char).
     */
    void addCellElements(List<String> elements);
    
    /**
     * For display purposes: specifies what should be displayed to the immediate west. This should 
     * occupy one character on-screen.
     */
    String getWestString();
    
    /** 
     * For display purposes: specifies what should be displayed to the immediate north. This should
     * occupy three characters on-screen.
     */
    String getNorthString();
    
    /**
     * Specifies whether there is a solid structure to the west (as opposed to empty space). This 
     * is used to determine what corner/intersection characters should be drawn, and during file
     * reading to check whether there are any overlapping walls/doors.
     */
    boolean westStructure();
    
    /** Specifies whether there is a solid structure to the north. */
    boolean northStructure();
    
    /**
     * Specifies whether the player can move from this grid square to the one immediately to the
     * west. (This isn't the same as 'westStructure()', because it might be true or false 
     * depending on the player's state.)
     */
    boolean westMovable(Player p);
    
    /**
     * Specifies whether the player can move from this grid square to the one immediately to the 
     * north.
     */
    boolean northMovable(Player p);
    
    /**
     * To be called when the player arrives at the current grid square, to performs any necessary 
     * actions, and to collect any resulting messages.
     */
    void moveTo(Player p, List<String> messages);
}
