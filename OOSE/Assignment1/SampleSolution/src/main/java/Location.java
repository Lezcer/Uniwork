package edu.curtin.maze;

/** 
 * Represents a grid square location in the maze.
 */
public class Location
{
    private final int row;
    private final int col;
    
    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() { return row; }
    public int getCol() { return col; }
    
    /**
     * Compare this location with row and column integers (to avoid having to create another 
     * Location object, if none exists yet).
     */
    public boolean equals(int row, int col)
    {
        return this.row == row && this.col == col;
    }
    
    /**
     * Compare this location with another object, presumably (though not necessarily) another
     * Location object.
     */
    @Override
    public boolean equals(Object other)
    {
        return (other instanceof Location) && 
            ((Location)other).row == row &&
            ((Location)other).col == col;
    }
    
    @Override
    public int hashCode()
    {
        // We could use Objects.hashCode(row, col), but this is a lower-level approach for 
        // illustration. Multiplying by a prime (31) helps minimise hash collisions, though there 
        // are more extensive explanations available.
        return 31 * row + col;
    }
    
    @Override
    public String toString()
    {
        return "(" + row + "," + col + ")";
    }
}
