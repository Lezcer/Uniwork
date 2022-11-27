package edu.curtin.maze;

/** 
 * Represents a type of key, obtainable by the player in order to open a door. These objects are
 * stored in:
 * (a) relevant KeySquare objects, to represent a key being present in that square, 
 * (b) in HorizontalDoor and VerticalDoor objects, to indicate what kind of key is needed to open
 *     the door, and 
 * (c) in the Player object, to indicate which keys the player has picked up so far.
 */
public class Key
{
    private static final String SYMBOL = "╕";
    private int colour;

    public Key(int colour)
    {
        if(colour < 1 || colour > 6)
        {
            // We set 'colour' to a valid (if arbitrary) value to ensure that the newly-constructed
            // key object is valid. (The object isn't necessarily destroyed just because the 
            // constructor fails, and security problems can sometimes result from partially-
            // constructed objects.)
            colour = 1;
            throw new IllegalArgumentException("Invalid key colour: " + colour);
        }
        this.colour = colour;
    }
    
    /** 
     * Add ANSI colour code to a given string, so that, when displayed on the terminal, it will 
     * have the same colour as this key type.
     */
    public String colourString(String s)
    {
        return String.format("\033[3%d;1m%s\033[m", colour, s);
    }
    
    @Override
    public boolean equals(Object other)
    {
        return (other instanceof Key) && ((Key)other).colour == colour;
    }
    
    @Override
    public int hashCode()
    {
        return colour;
    }
    
    @Override
    public String toString()
    {
        return colourString(SYMBOL);
    }
}
