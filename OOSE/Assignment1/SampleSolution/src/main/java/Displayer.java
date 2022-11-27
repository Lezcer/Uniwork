package edu.curtin.maze;
import edu.curtin.maze.square.Square;
import java.util.*;

/**
 * Displays the maze on the terminal, relying at various points on display strings returned by 
 * Square and Key objects.
 */
public class Displayer
{
    private static final String CLEAR_SCREEN = "\033[2J\033[H";
    private static final String PLAYER_SYMBOL = "P";
    private static final char[] CORNERS = {
        ' ', '─', '─', '─', 
        '│', '┌', '┐', '┬',
        '│', '└', '┘', '┴', 
        '│', '├', '┤', '┼'
    };

    private Maze maze;
    private Player player;

    public Displayer(Maze maze, Player player)
    {
        this.maze = maze;
        this.player = player;
    }

    /**
     * Determine which symbol to use at the corner/intersection points in the maze. These points
     * are diagonally in-between four maze squares. We decide based on whether there's a 
     * 'structure' (wall/door) to the north, south, east and/or west of the given point.
     */
    private char corner(boolean north, boolean south, boolean east, boolean west)
    {
        return CORNERS[(north ? 8 : 0) + (south ? 4 : 0) + (east ? 2 : 0) + (west ? 1 : 0)];
    }
    
    /** 
     * Draw the maze, including the player's current position.
     */
    public void display()
    {
        int nRows = maze.getNRows();
        int nCols = maze.getNCols();
        List<String> cell = new ArrayList<>();
        System.out.print(CLEAR_SCREEN);
        
        displayKeys(player);
        
        for(int row = 0; row < nRows + 1; row++)
        {
            // Show the horizontal boundary (a combination of horizontal walls, doors and spaces) 
            // north of the current row.
            for(int col = 0; col < nCols + 1; col++)
            {
                Square sq = maze.get(row, col);
                System.out.print(corner(maze.get(row - 1, col).westStructure(), sq.westStructure(),
                                        maze.get(row, col - 1).northStructure(), sq.northStructure()));
                System.out.print(sq.getNorthString());                    
            }
            System.out.println();
            
            // Show the square contents interspersed with the vertical boundaries between cells.
            for(int col = 0; col < nCols + 1; col++)
            {
                Square sq = maze.get(row, col);
                System.out.print(sq.getWestString());
                cell.clear();
                sq.addCellElements(cell);
                if(player.getLocation().equals(row, col))
                {
                    cell.add(0, PLAYER_SYMBOL);
                }
                
                // The 'cell' list contains all the things we should show inside square (row,col),
                // each element occupying a single on-screen character (though it might be a string
                // that also has ANSI escape codes).                
                // We can only show three characters though. If there are less than three elements,
                // add spaces to pad it out.
                switch(cell.size())
                {
                    case 0:  System.out.print("   "); break;
                    case 1:  System.out.printf(" %s ", cell.get(0)); break;
                    case 2:  System.out.printf("%s%s ", cell.get(0), cell.get(1)); break;
                    default: System.out.printf("%s%s%s", cell.get(0), cell.get(1), cell.get(2)); break;
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Show the player's inventory of keys.
     */
    private void displayKeys(Player player)
    {        
        Map<Key,Integer> keyCount = player.getKeys();
        if(keyCount.isEmpty())
        {
            System.out.println("No keys acquired yet\n");
        }
        else
        {
            System.out.print("Keys: ");        
            for(Key key : keyCount.keySet())
            {
                System.out.print(key);
                int n = keyCount.get(key);
                if(n > 1)
                {
                    System.out.print("×" + n);
                }
                System.out.print("  ");
            }
            System.out.println('\n');
        }
    }
}
