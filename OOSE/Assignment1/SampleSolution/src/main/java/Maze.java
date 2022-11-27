package edu.curtin.maze;
import edu.curtin.maze.square.Square;
import edu.curtin.maze.square.BaseSquare;
import edu.curtin.maze.square.HorizontalWallSquare;
import edu.curtin.maze.square.VerticalWallSquare;
import java.util.logging.Logger;

/**
 * Represents the maze itself; basically: the grid of squares, plus the Player's starting location.
 */
public class Maze
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Maze.class.getName());
    
    private int nRows;
    private int nCols;
    private Square[][] squares = null;
    private Location start = null;
    private boolean end = false;
    
    public Maze(int nRows, int nCols)
    {
        this.nRows = nRows;
        this.nCols = nCols;
    }
    
    /**
     * Performs lazy-initialisation. Doing complex logic in the constructor is bad practice (due 
     * to the possibility of failure, leading to partially-constructed objects).
     *
     * Instead, this method is called whenever we want to access the 'squares' 2D array. We simply 
     * check whether it's been initialised yet, and if not, we initialise it to a blank/default 
     * version of the maze.
     *
     * Subsequent calls to 'put()' are necessary to populate the maze with internal walls, doors, 
     * keys, messages and end locations.
     */
    private void init()
    {
        if(squares == null)
        {
            logger.info("Initialising empty maze");
            
            // The 2D array contains the maze, plus a "margin" of non-playable squares around the
            // outside (notionally outside the perimeter wall), to avoid the need to check boundary
            // conditions later on.
            // (This requires "+ 1" and "- 1" to convert between grid square coordinates to array 
            // indices. The 'nRows' and 'nCols' fields keep track of the maze size, *not* the array
            // size.)
            
            squares = new Square[nRows + 2][nCols + 2];
            for(int i = 0; i < nRows + 2; i++)
            {
                for(int j = 0; j < nCols + 2; j++)
                {
                    squares[i][j] = new BaseSquare(new Location(i - 1, j - 1));
                }
            }
            
            // Add automatic perimeter walls on the east & west.
            for(int i = 1; i < nRows + 1; i++)
            {                
                squares[i][1] = new VerticalWallSquare(squares[i][1]);
                squares[i][nCols + 1] = new VerticalWallSquare(squares[i][nCols + 1]);
            }
            
            // Add automatic perimeter walls on the north & south;
            for(int j = 1; j < nCols + 1; j++)
            {
                squares[1][j] = new HorizontalWallSquare(squares[1][j]);
                squares[nRows + 1][j] = new HorizontalWallSquare(squares[nRows + 1][j]);
            }
        }
    }
    
    public int getNRows() { return nRows; }
    public int getNCols() { return nCols; }
    
    /** 
     * Retrieves the player's starting location. If no starting location has been specified, a 
     * default is returned instead.
     */
    public Location getStart() 
    { 
        return (start != null) ? start : new Location(0, 0);
    }
    
    /** Sets the player's starting location. */
    public void setStart(Location start)
    {
        this.start = start;
    }
    
    /** 
     * Returns true iff a starting location has been specified. You cannot tell this from 
     * 'getStart()', because that will substitute a default if no starting location has been given.
     */
    public boolean hasStart()
    {
        return start != null;
    }
    
    /** 
     * Returns true iff the 'end flag' has been set, indicating that one or more end locations 
     * have been given in the maze.
     */    
    public boolean hasEnd()
    {
        return end;
    }
    
    /**
     * Set the 'end flag', to show that an end point exists in the maze. The Maze object itself 
     * does not verify this.
     */
    public void setEndFlag()
    {
        end = true;
    }
    
    /** 
     * Retrieves the square at a given grid location, specified in the form of a Location object.
     */
    public Square get(Location loc)
    {
        return get(loc.getRow(), loc.getCol());
    }
    
    /** 
     * Retrieves the square at a given grid location, specified in the form of row and column 
     * integers.
     */
    public Square get(int row, int col)
    {
        init();
        if(row < -1 || col < -1 || row > nRows + 1 || col > nCols + 1)
        {
            throw new IllegalArgumentException("row or column out of bounds");
        }
        
        return squares[row + 1][col + 1];
    }
    
    /** 
     * Overwrites a given grid square. The location of the grid square is obtained from the Square
     * object itself; hence the caller doesn't need to provide it.
     */
    public void put(Square square)
    {
        init();
        Location loc = square.getLocation();
        squares[loc.getRow() + 1][loc.getCol() + 1] = square;
    }
}
